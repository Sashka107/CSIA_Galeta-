package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.BinarySearch;
import com.csia_galeta.ser.DataSaverAndReader;

import java.util.ArrayList;
import java.util.List;

/*
 Class CompetitionSingleton
 This class is implemented using the Singleton design pattern to store all the information necessary for the program to work in a single instance as a storage,
 while the program is running and active
 */
public class CompetitionSingleton {

    // оObject for storing the wrapper with all competitions.
    private static CompetitionListWrapper competitionListWrapper = null;

    // Object for temporarily storing a competition - for example: during creation and editing.
    private static Competition tmpCompetition = new Competition();

    // Object for storing the current competition in progress.
    private static Competition currentCompetition = new Competition();


    // A hidden constructor inaccessible to prevent the creation of objects.

    private CompetitionSingleton(){}

    static { // Static initialisation block for retrieving data in Singleton.
        getInstance();
    }

    /*
     Method for retrieving data in Singleton from a JSON file

     @return a wrapper object with the list of all competitions
     */
    public static CompetitionListWrapper getInstance(){

        // If the competition wrapper was empty:
        if(competitionListWrapper == null)
            // Read information from the file.
            competitionListWrapper = new CompetitionListWrapper("save.json");

        // If not empty, return the wrapper.
        return competitionListWrapper;
    }

    /*
     Getter for retrieving a driver by their number

     @param num the number to search for
     @return the driver with the specified number
     */
    public static Driver getDriverByNumber(short num){
        List<Driver> listOfDriversForNumSearch = new ArrayList<>(currentCompetition.getListOfDrivers());
        bubbleSort(listOfDriversForNumSearch); // Sorting the list.
        return BinarySearch.binarySearch(listOfDriversForNumSearch, num); // Searching using binary search.
    }

    /*
     Method for saving an intermediate competition
     Saves the entire competition wrapper to a file, thereby overwriting the old version.
     */
    public static void saveTmpCompetition(){
        competitionListWrapper.addCompetition(tmpCompetition); // Adding the competition.

        // Saving the competition wrapper.
        DataSaverAndReader.saveJsonStringToFile("save.json", competitionListWrapper);

        // Assigning the temporary competition to the current competition.
        currentCompetition = tmpCompetition;

        // Resetting the temporary competition and recreating its object.
        tmpCompetition = null;
        tmpCompetition = new Competition();
    }

    /*
     Method for saving the current competition
     Saves the entire competition wrapper to a file, thereby overwriting the old version.
     */
    public static void saveCurrentCompetition(){
        competitionListWrapper.addCompetition(currentCompetition); // Adding a competition.

        // Saving the competition wrapper.
        DataSaverAndReader.saveJsonStringToFile("save.json", competitionListWrapper);
    }

    /*
     Method for adding a driver to the temporary competition

     @param d the driver to add
     */
    public static void addDriverToTmpCompetition(Driver d){
        tmpCompetition.addDriverToList(d);
    }

    /*
     Method for adding a judge to the temporary competition

     @param j the judge to add
     */
    public static void addJudgeToTmpCompetition(Judge j){
        tmpCompetition.addJudgeToList(j);
    }

    /*
     Method for adding qualification to the temporary competition

     @param q - the qualification object to add
     */
    public static void addQualificationToTmpCompetition(Qualification q){
        tmpCompetition.setQualification(q);
    }

    /*
     Method for adding the number of qualification rounds to the temporary competition

     @param count the number to add
     */
    public static void addCountOfRounds(int count){
        tmpCompetition.setAmountOfQualifyingRounds((byte) count);
    }

    /*
     Getter for retrieving the temporary competition

     @return the temporary competition object
     */
    public static Competition getTmpCompetition() {
        return tmpCompetition;
    }

    /*
     Getter for obtaining the current competition

     @return the current competition object
     */
    public static Competition getCurrentCompetition() {
        return currentCompetition;
    }

    /*
     Method adds a name to the temporary competition.

     @param name - the name of the competition to add
     */
    public static void addToTmpCompetitionName(String name){
        tmpCompetition.setCompetitionName(name);
    }

    /*
     Setter for setting the current competition

     @param currentCompetition - the competition to set
     */
    public static void setCurrentCompetition(Competition currentCompetition) {
        CompetitionSingleton.currentCompetition = currentCompetition;
    }

    /*
     Setter for setting the temporary competition

     @param tmpCompetition the competition to set
     */
    public static void setTmpCompetition(Competition tmpCompetition) {
        CompetitionSingleton.tmpCompetition = tmpCompetition;
    }

    /*
     Method for sorting using the BubbleSort algorithm by driver number

     @param drivers the list to be sorted
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