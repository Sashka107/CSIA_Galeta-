package com.csia_galeta.ser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class DataSaverAndReader
 * Этот утилитарный класс содержит методы для сохранения
 * и считывания информации из файла JSON
 *
 * @author Alexander G.
 */
public class DataSaverAndReader {

    // константа путь к папке с сохранениями
    private static final String pathToSave = "saves/";

    // константа объекта библиотеки Gson для работы с JSON
    private static final Gson gsonSaver = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Этот статический метод сохраняет объект в файл в формате JSON
     *
     * @param fileName путь к файлу сохранений
     * @param data объект для сохранения
     */
    public static void saveJsonStringToFile(String fileName, Object data){

        // получаем путь к файлу из строки
        Path dir = Path.of(pathToSave);

        // если нет папки для сохранения
        if(!Files.exists(dir)){

            // пробуем создать папку
            try {
                Files.createDirectory(dir);
            } catch (IOException e) { // если не получилось
                System.out.println("Folder canc`t create");
                e.printStackTrace();
            }
        }

        // пробуем записать на файл
        try {
            Files.write(Path.of(pathToSave + fileName), gsonSaver.toJson(data).getBytes());
        } catch (IOException e) { // если не получилось
            System.out.println("Can`t save data");
            e.printStackTrace();
        }
    }

    /**
     * Этот статический метод считывает объект из файла JSON
     *
     * @throws FileNotFoundException выбрасывает ошибку в случае если файла не существует
     * @param clazz какого класса считываемый объект
     * @param pathToFile путь к файлу сохранений
     */
    public static<T> T readDataFromJson(Class<T> clazz, String pathToFile) throws FileNotFoundException {

        // создаем объект для считывания с файла
        JsonReader reader = new JsonReader(new FileReader(pathToSave + pathToFile));

        // возвращаем объект полученный с помощью библиотеки Gson
        return gsonSaver.fromJson(reader, clazz);
    }
}