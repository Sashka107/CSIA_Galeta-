package com.csia_galeta.ser;

import com.csia_galeta.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Class SceneOpener
 * Этот утилитарный класс содержит методы для открытия окон
 * программы и обращения к их контроллерам
 *
 * @author Alexander G.
 */
public class SceneOpener {

    /**
     * Этот статический метод открывает сцену/окно и возвращает её контроллер
     * для дальнейшего управления
     *
     * @return контроллер сцены, которая будет открыта
     * @param fxmlName название окна, которое нужно открыть
     * @param sceneTitle название окна, которое нужно установить для открываемого окна
     * @param sceneToClose сцена, которую нужно закрыть - предыдущая сцена/окно
     */
    public static <T> T openSceneAndReturnController(String fxmlName, String sceneTitle, Window sceneToClose){

        // загружаем разметку открываемой сцены
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlName));

        // создаем, но не инициализируем объект сцены
        Scene scene;

        // пробуем инициализировать/загрузить сцену по названию файла разметки
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) { // если не получилось
            throw new RuntimeException(e);
        }

        // если все получилось, то выполняем открытие сцены, установку заголовка,
        // закрытие прошлой сцены и возврат контроллера
        Stage newStage = new Stage();
        newStage.setTitle(sceneTitle);
        newStage.setScene(scene);
        newStage.show();
        ((Stage)sceneToClose).close();
        return fxmlLoader.getController();
    }

    /**
     * Этот статический метод открывает сцену/окно и возвращает её контроллер
     * для дальнейшего управления. Не закрывает прошлую сцену/окно
     *
     * @return контроллер сцены, которая будет открыта
     * @param fxmlName название окна, которое нужно открыть
     * @param sceneTitle название окна, которое нужно установить для открываемого окна
     */
    public static <T> T openSceneAndReturnController(String fxmlName, String sceneTitle){

        // загружаем разметку открываемой сцены
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlName));

        // создаем, но не инициализируем объект сцены
        Scene scene;

        // пробуем инициализировать/загрузить сцену по названию файла разметки
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) { // если не получилось
            throw new RuntimeException(e);
        }

        // если все получилось, то выполняем открытие сцены, установку заголовка
        // и возврат контроллера, без закрытия прошлой сцены
        Stage newStage = new Stage();
        newStage.setTitle(sceneTitle);
        newStage.setScene(scene);
        newStage.show();
        return fxmlLoader.getController();
    }

}