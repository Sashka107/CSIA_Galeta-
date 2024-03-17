package com.csia_galeta.ser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 Class DataSaverAndReader
 This utility class contains methods for saving
 and reading information from a JSON file.
 */
public class DataSaverAndReader {

    // Constant for the path to the folder with saves.
    private static final String pathToSave = "saves/";

    // Constant for the Gson library object to work with JSON.
    private static final Gson gsonSaver = new GsonBuilder().setPrettyPrinting().create();

    /*
     This static method saves the object to a file in JSON format.

     @param fileName the path to the save file.
     @param data the object to be saved.
     */
    public static void saveJsonStringToFile(String fileName, Object data){

        // We obtain the file path from the string.
        Path dir = Path.of(pathToSave);

        // If there is no folder for saving.
        if(!Files.exists(dir)){

            // Trying to create a folder.
            try {
                Files.createDirectory(dir);
            } catch (IOException e) { // If unsuccessful.
                System.out.println("Folder canc`t create");
                e.printStackTrace();
            }
        }

        // Attempting to write to the file.
        try {
            Files.write(Path.of(pathToSave + fileName), gsonSaver.toJson(data).getBytes());
        } catch (IOException e) { // If unsuccessful.
            System.out.println("Can`t save data");
            e.printStackTrace();
        }
    }

    /*
     This static method reads an object from a JSON file.

     @throws FileNotFoundException throws an error if the file does not exist.
     @param clazz the class of the object to be read.
     @param pathToFile the path to the save file.
     */
    public static<T> T readDataFromJson(Class<T> clazz, String pathToFile) throws FileNotFoundException {

        // We create an object for reading from the file.
        JsonReader reader = new JsonReader(new FileReader(pathToSave + pathToFile));

        // We return the object obtained using the Gson library.
        return gsonSaver.fromJson(reader, clazz);
    }
}