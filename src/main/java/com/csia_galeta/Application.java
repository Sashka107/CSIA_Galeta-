package com.csia_galeta;

import com.csia_galeta.ser.SceneOpener;
import javafx.stage.Stage;

/**
 * Class Application
 * Этот класс содержит методы для открытия и запуска программы,
 * используя JavaFX библиотеку для написания программы
 *
 * @author Alexander G.
 */
public class Application extends javafx.application.Application {

    /**
     * Переопределяем метод старт из базового класса библиотеку JavaFX,
     * который устанавливает начальную сцену/окно приложения
     *
     * @param stage стадия приложения
     */
    @Override
    public void start(Stage stage) {
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml",
                "Main View");
    }

    /**
     * Главная функция запуска программы в Java
     */
    public static void main(String[] args) {
        launch(); // запускаем функцию из класса родителя JavaFX библиотеки
    }
}