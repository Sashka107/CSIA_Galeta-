package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.BinarySearch;
import com.csia_galeta.ser.DataSaverAndReader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class CompetitionSingleton
 * Этот класс реализован по патерну программирования Singleton для того,
 * чтобы хранить всю информацию нужную для работы программы в одном экземпляре как хранилище,
 * пока программа работает и запущена
 *
 * @author Alexander G.
 */
public class CompetitionSingleton {

    // обьект для хранения обертки со всеми соревнованиями
    private static CompetitionListWrapper competitionListWrapper = null;

    // объект для временного сохранения соревнования - например: при создании и редактировании
    private static Competition tmpCompetition = new Competition();

    // объект для хранения текущего соревнования, которое сейчас идет
    private static Competition currentCompetition = new Competition();

    /**
     * Скрытый от доступа конструктор, чтобы нельзя было создать объекты
     */
    private CompetitionSingleton(){}

    static { // статический блок инициализации для получения данных в Singleton
        getInstance();
    }

    /**
     * Метод для получения данных в Singleton из JSON файла
     *
     * @return объект-обертку с списком всех соревнований
     */
    public static CompetitionListWrapper getInstance(){

        // если обертка соревнований была пуста
        if(competitionListWrapper == null)
            // считываем из файла информацию
            competitionListWrapper = new CompetitionListWrapper("save.json");

        // если не пуста возвращаем обертку
        return competitionListWrapper;
    }

    /**
     * Getter для получения водителя его номеру
     *
     * @return водителя с нужным номером
     * @param num номер по которому должен быть выполнен поиск
     */
    public static Driver getDriverByNumber(short num){
        List<Driver> listOfDriversForNumSearch = new ArrayList<>(currentCompetition.getListOfDrivers());
        bubbleSort(listOfDriversForNumSearch); // сортируем список
        return BinarySearch.binarySearch(listOfDriversForNumSearch, num); // ищем с помощью бинарного поиска
    }

    /**
     * Метод для сохранения промежуточного соревнования
     * Сохраняет всю обертку с соревнованиями на файл, тем самым перезаписывая старую версию
     */
    public static void saveTmpCompetition(){
        competitionListWrapper.addCompetition(tmpCompetition); // добавляем соревнование

        // сохраняем обертку с соревнованиями
        DataSaverAndReader.saveJsonStringToFile("save.json", competitionListWrapper);

        // в текущее соревнование записываем временное
        currentCompetition = tmpCompetition;

        // обнуляем временное соревнование и пересоздаем его объект
        tmpCompetition = null;
        tmpCompetition = new Competition();
    }

    /**
     * Метод для сохранения текущего соревнования
     * Сохраняет всю обертку с соревнованиями на файл, тем самым перезаписывая старую версию
     */
    public static void saveCurrentCompetition(){
        competitionListWrapper.addCompetition(currentCompetition); // добавляем соревнование

        // сохраняем обертку с соревнованиями
        DataSaverAndReader.saveJsonStringToFile("save.json", competitionListWrapper);
    }

    /**
     * Метод добавляет водителя к временному соревнованию
     *
     * @param d водитель для добавления
     */
    public static void addDriverToTmpCompetition(Driver d){
        tmpCompetition.addDriverToList(d);
    }

    /**
     * Метод добавляет судью к временному соревнованию
     *
     * @param j судья для добавления
     */
    public static void addJudgeToTmpCompetition(Judge j){
        tmpCompetition.addJudgeToList(j);
    }

    /**
     * Метод добавляет квалификацию к временному соревнованию
     *
     * @param q объект квалификации для добавления
     */
    public static void addQualificationToTmpCompetition(Qualification q){
        tmpCompetition.setQualification(q);
    }

    /**
     * Метод добавляет количество квалификационных раундов к временному соревнованию
     *
     * @param count количество для добавления
     */
    public static void addCountOfRounds(int count){
        tmpCompetition.setAmountOfQualifyingRounds((byte) count);
    }

    /**
     * Getter для получения временного соревнования
     *
     * @return объект временного соревнования
     */
    public static Competition getTmpCompetition() {
        return tmpCompetition;
    }

    /**
     * Getter для получения текущего соревнования
     *
     * @return объект текущего соревнования
     */
    public static Competition getCurrentCompetition() {
        return currentCompetition;
    }

    /**
     * Метод добавляет название к временному соревнованию
     *
     * @param name название соревнования для добавления
     */
    public static void addToTmpCompetitionName(String name){
        tmpCompetition.setCompetitionName(name);
    }

    /**
     * Setter для установки текущего соревнования
     *
     * @param currentCompetition соревнование для установки
     */
    public static void setCurrentCompetition(Competition currentCompetition) {
        CompetitionSingleton.currentCompetition = currentCompetition;
    }

    /**
     * Setter для установки временного соревнования
     *
     * @param tmpCompetition соревнование для установки
     */
    public static void setTmpCompetition(Competition tmpCompetition) {
        CompetitionSingleton.tmpCompetition = tmpCompetition;
    }

    /**
     * Метод для сортировки с помощью алгоритма BubbleSort по номеру водителя
     *
     * @param drivers список, который будет отсортирован
     */
    public static void bubbleSort(List<Driver> drivers) {
        int i, j;
        Driver tmp;
        for (i = 0; i < drivers.size() - 1; i++) {
            for (j = 0; j < drivers.size() - i - 1; j++) {
                if (drivers.get(j).getNumber() > drivers.get(j+1).getNumber()) {
                    tmp = drivers.get(j);
                    drivers.set(j,drivers.get(j+1));
                    drivers.set(j+1, tmp);
                }
            }
        }
    }

}