package com.csia_galeta.ser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataSaverAndReader {

    //Saving and reading

    private static final String pathToSave = "saves/";
    private static final Gson gsonSaver = new GsonBuilder().setPrettyPrinting().create();

    public static void saveStringToFile(String fileName, String data){

        Path dir = Path.of(pathToSave);
        if(!Files.exists(dir)){
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                System.out.println("Folder can`t create");
                e.printStackTrace();
            }
        }

        try {
            Files.write(Path.of(pathToSave + fileName), data.getBytes());
        } catch (IOException e) {
            System.out.println("Can`t save data");
            e.printStackTrace();
        }
    }

    public static void saveJsonStringToFile(String fileName, Object data){

        Path dir = Path.of(pathToSave);
        if(!Files.exists(dir)){
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                System.out.println("Folder can`t create");
                e.printStackTrace();
            }
        }

        try {
            Files.write(Path.of(pathToSave + fileName), gsonSaver.toJson(data).getBytes());
        } catch (IOException e) {
            System.out.println("Can`t save data");
            e.printStackTrace();
        }
    }

    public static<T> T readDataFromJson(Class<T> clazz, String pathToFile) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(pathToSave + pathToFile));
        return gsonSaver.fromJson(reader, clazz);
    }
}