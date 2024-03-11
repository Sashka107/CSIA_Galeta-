package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.Qualification;
import com.csia_galeta.QualificationController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Class CreateEditCompetitionController
 * Отвечает за поведение окна создания нового или редактирование соревнования в статусе PLANNED.
 * В зависимости от того, где и откуда этот контроллер был вызван будет продолжено редактирование
 * или создание нового
 *
 * @author Alexander G.
 */
public class CreateEditCompetitionController {
    public DatePicker datePicked; // переменная-ссылка на окно выбора даты
    public Label judgesLabel; // Переменная-ссылка на поле для отображения текста имен судий
    @FXML
    private TextField competitionName; // Переменная-ссылка на поле для ввода названия соревнования

    @FXML
    private TextField countOfCF; // Переменная-ссылка на поле для ввода количества раундов квалификации
    @FXML
    private Button addJudges; // Переменная-ссылка на кнопку для добавления судий
    @FXML
    private Button addDrivers; // Переменная-ссылка на кнопку для добавления участников

    @FXML
    private Button startQualification; // Переменная-ссылка на кнопку для начала квалификации

    @FXML
    private Label mainText; // Переменная-ссылка на поле для отображения заголовка окна

    @FXML
    private ListView<Driver> listView; // Переменная-ссылка на список для отображения участников

    /**
     * Метод, который запускается при нажатии на кнопку "Добавить водителя"
     * Открывает окно для добавления водителя и закрывает текущее
     */
    @FXML
    protected void openAddDrivers(){
        saveCompetition(true);
        SceneOpener.openSceneAndReturnController("Drivers.fxml",
                "Add New Driver",
                addDrivers.getScene().getWindow());
    }

    /**
     * Метод для выполнения проверок на корректность введенных данных о соревновании,
     * когда это требуется
     *
     * @return true если все введено корректно, false - если нет
     * @param notCheckWarnings - переменная, которая дает не выполнять некоторые проверки
     */
    protected boolean saveCompetition(boolean notCheckWarnings){
        boolean hasWarning = false; // была ли хоть одна ошибка

        if (checkCountOfQ(countOfCF.getText())){ // проверяем количество раундов
            // пытаемся добавить количество раундов в текущее соревнование
            CompetitionSingleton.addCountOfRounds(Integer.parseInt(countOfCF.getText()));
        } else {
            hasWarning = true; // да, была ошибка
            // высвечиваем предупреждение
            showWarning("Please check whether entered data is a number from 1 to 5.", notCheckWarnings);
        }

        // аналогично как выше
        if (checkCompetitionName(competitionName.getText())){
            CompetitionSingleton.addToTmpCompetitionName(competitionName.getText());
        } else {
            hasWarning = true;
            showWarning("Please check whether entered data is a is a valid name and less or equal to 55 characters.", notCheckWarnings);
        }

        // аналогично как выше
        if (checkDate(datePicked.getValue())){
            CompetitionSingleton.getTmpCompetition().setCompetitionDate(datePicked.getValue());
        } else {
            hasWarning = true;
            showWarning("Date is incorrect", notCheckWarnings);
        }

        // если нет ни одной ошибки
        if (!hasWarning){
            System.out.println("Saving Competition...");
            return true;
        }

        return false; // если есть хоть одна ошибка
    }

    /**
     * Метод, который срабатывает при нажатии на кнопку "Начать соревнование".
     * Запускает квалификацию если нет никаких ошибок в соревновании
     */
    @FXML
    protected void startCompetition(){
        // если были ошибки при заполнении полей соревнования
        if(!saveCompetition(false))
            return; // прекращаем запуск соревнования

        // если водителей меньше 4
        if(CompetitionSingleton.getTmpCompetition().getListOfDrivers().size() <= 3){
            showWarning("Can`t save competition with 1 player. Minimum Players: 4", false);
            return; // прекращаем запуск соревнования
        }

        // делаем необходимые сохранения, меняем статус, открываем окно квалификации
        CompetitionSingleton.getTmpCompetition().setCompetitionStateQualificationInProgress();
        CompetitionSingleton.addQualificationToTmpCompetition(new Qualification(CompetitionSingleton.getTmpCompetition().getListOfDrivers()));
        CompetitionSingleton.saveTmpCompetition();
        QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                "Qualification",
                startQualification.getScene().getWindow());
        controller.loadWindow();
    }

    /**
     * Метод сохраняет соревнование по нажатию на кнопку "Сохранить соревнование"
     * Сохранит только еесли все было ранее заполнено корректно
     */
    @FXML
    private void saveCompetitionOnClick(){
        // если были ошибки при заполнении полей соревнования
        if(!saveCompetition(false))
            return; // прекращаем запуск соревнования

        // если водителей меньше 4
        if(CompetitionSingleton.getTmpCompetition().getListOfDrivers().size() <= 3){
            showWarning("Can`t save competition with 1 player. Minimum Players: 4", false);
            return; // прекращаем запуск соревнования
        }

        // делаем необходимые сохранения, меняем статус, открываем главное окно
        CompetitionSingleton.getTmpCompetition().setCompetitionStatePlanned();
        CompetitionSingleton.saveTmpCompetition();
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml",
                "Main View",
                startQualification.getScene().getWindow());
    }

    /**
     * Метод для отображения предупреждения если это нужно
     *
     * @param notShowWarn - когда не нужно отображать предупреждение
     * @param message - сообщение, которое нужно отобразить
     */
    private void showWarning(String message, boolean notShowWarn){
        if(notShowWarn)
            return;

        // отображаем предупреждение
        Alert warningsAlert = new Alert(Alert.AlertType.WARNING);
        warningsAlert.setHeaderText("Incorrect data");
        warningsAlert.setContentText(message);
        warningsAlert.show();
    }

    /**
     * Метод для загрузки данных текущего создаваемого соревнования в окно редактивания
     * Используется при открытии окна после добавления судий или водителей
     */
    public void loadTmpCompetition(){
        competitionName.setText(CompetitionSingleton.getTmpCompetition().getCompetitionName());
        countOfCF.setText(CompetitionSingleton.getTmpCompetition().getAmountOfQualifyingRounds() + "");
        listView.getItems().addAll(CompetitionSingleton.getTmpCompetition().getListOfDrivers());
        datePicked.setValue(CompetitionSingleton.getTmpCompetition().getCompetitionDateLocalDate());
        judgesLabel.setText(CompetitionSingleton.getTmpCompetition().getListOfJudgesString());
    }

    /**
     * Метод отрабатывает в случае если окно открыть надо в режиме "редактировать соревнование",
     * а не создать новое
     */
    public void loadToEditCompetition(){
        CompetitionSingleton.setTmpCompetition(CompetitionSingleton.getCurrentCompetition());
        loadTmpCompetition();
        mainText.setText("Edit Competition");
        competitionName.setEditable(false);
        datePicked.setValue(CompetitionSingleton.getTmpCompetition().getCompetitionDateLocalDate());
        judgesLabel.setText(CompetitionSingleton.getTmpCompetition().getListOfJudgesString());
    }

    /**
     * Метод для проверки коррекности выбранной даты
     *
     * @return true если дата выбрана, false - если нет
     * @param date - дата для проверки
     */
    private boolean checkDate(LocalDate date){
        return date != null;
    }

    /**
     * Метод для проверки коррекности количества раундов квалификации
     *
     * @return true если количество коррекно, false - если нет
     * @param amountToBeChecked - введенное количество для проверки
     */
    private boolean checkCountOfQ (String amountToBeChecked){
        // если содержит не только числа или пустое
        if(amountToBeChecked == null || !amountToBeChecked.matches("[0-9]+"))
            return false; // проверку не пройдено

        // выполняем преобразования
        int correctAmountOFQ = Integer.parseInt(amountToBeChecked);

        // если число в диапазоне от 1 до 5 - значит прошло проверку
        return correctAmountOFQ > 0 && correctAmountOFQ <= 5;
    }

    /**
     * Метод для проверки коррекности названия соревнования
     *
     * @return true если название коррекно, false - если нет
     * @param nameToBeChecked - введенное имя для проверки
     */
    private boolean checkCompetitionName (String nameToBeChecked){
        if(nameToBeChecked == null)
            return false;

        // если имя содержит от 1 до 55 символов, значит корректно
        return nameToBeChecked.matches("^.{1,55}$");
    }

    /**
     * Метод, который запускается при нажатии на кнопку "Добавить судью"
     * Открывает окно для добавления судьи и закрывает текущее
     */
    @FXML
    public void addJudges(){
        saveCompetition(true);
        SceneOpener.openSceneAndReturnController("AddJudgeFrame.fxml",
                "Add New Judge",
                addJudges.getScene().getWindow());
    }

}