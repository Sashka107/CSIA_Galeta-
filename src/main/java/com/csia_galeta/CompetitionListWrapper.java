package com.csia_galeta;

import com.csia_galeta.ser.DataSaverAndReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/*
 Class CompetitionListWrapper
 This wrapper class helps to work with multiple competitions at once
 and adds necessary functionality for convenience.
 */
public class CompetitionListWrapper {

    private List<Competition> allCompetitions; // List of all competitions throughout all time.


    /*
     Constructor that loads information about competitions from a JSON file

     @param pathToSave - the path to the file with JSON format saves.
     */
    public CompetitionListWrapper(String pathToSave){

        // Creating a variable of the competition wrapper class.
        CompetitionListWrapper allCompetitionsFromFile;
        try { // Trying to read information from a file using the DataSaverAndReader class.
            allCompetitionsFromFile = DataSaverAndReader.readDataFromJson(CompetitionListWrapper.class, pathToSave);
        } catch (FileNotFoundException e) { // If unsuccessful, throw an error.
            throw new RuntimeException(e);
        }

        if(allCompetitionsFromFile != null) // If the competitions did exist in the file, then:
            // Set all competitions from the wrapper into the list of all competitions of the current object.
            this.allCompetitions = allCompetitionsFromFile.getCompetitions();
        else
            // If not, then create an empty list of competitions.
            allCompetitions = new ArrayList<>();
    }

    /*
     Method for adding a competition to the overall list of all competitions

     @param c the competition to add
     */
    public void addCompetition(Competition c){

        if(allCompetitions == null) // If the list does not exist:
            allCompetitions = new ArrayList<>(); // Initiate a new one.

        // If the list does not contain an identical competition, then:
        if(!allCompetitions.contains(c))
            allCompetitions.add(c); // Add the competition to the list.
        else{
            // If it does contain one, then replace the duplicate competition to avoid duplicates.
            for(int i = 0; i < allCompetitions.size(); i++){
                if(allCompetitions.get(i).equals(c))
                    allCompetitions.set(i, c);
            }
        }

    }

    /*
     Getter for the list of all competitions

     @return the list of competitions
     */
    public List<Competition> getCompetitions(){
        return allCompetitions;
    }

    /*
     Method for gathering and returning information about the object in string format

     @return a string with the necessary information
     */
    @Override
    public String toString() {
        return "CompetitionListWrapper{" +
                "allCompetitions=" + allCompetitions +
                '}';
    }
}