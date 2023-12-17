package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.BinarySearch;
import com.csia_galeta.ser.DataSaverAndReader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CompetitionSingleton {

    private static CompetitionListWrapper competitionListWrapper = null;
    private static Competition tmpCompetition = new Competition();

    private static Competition currentCompetition = new Competition();

    private static Consumer<Driver> OnDriverAdded;

    private CompetitionSingleton(){}

    static {
        getInstance();
    }

    public static CompetitionListWrapper getInstance(){
        if(competitionListWrapper == null)
            competitionListWrapper = new CompetitionListWrapper("save.json");

        return competitionListWrapper;
    }

    public static Driver getDriverByNumber(short num){
        //Selection sort
        List<Driver> listOfDriversForNumSearch = new ArrayList<>(currentCompetition.getListOfDrivers());
        bubbleSort(listOfDriversForNumSearch);
         return BinarySearch.binarySearch(listOfDriversForNumSearch, num);
        /*for(Driver d : currentCompetition.getListOfDrivers()){
            if(d.getNumber() == num) // Binary Search (Separate class - binary search)
                return d;
        }*/
        /*return null;*/
    }

    public static void driverAddHandler(Consumer<Driver> func){
        OnDriverAdded = func;
    }

    public static void saveTmpCompetition(){
        competitionListWrapper.addCompetition(tmpCompetition);
        DataSaverAndReader.saveJsonStringToFile("save.json", competitionListWrapper);
        currentCompetition = tmpCompetition;
        tmpCompetition = null;
        tmpCompetition = new Competition();
    }

    public static void saveCurrentCompetition(){
        competitionListWrapper.addCompetition(currentCompetition);
        DataSaverAndReader.saveJsonStringToFile("save.json", competitionListWrapper);
    }

    public static void addDriverToTmpCompetition(Driver d){
        tmpCompetition.addDriverToList(d);
        if(OnDriverAdded != null)
            OnDriverAdded.accept(d);
    }

    public static void addJudgeToTmpCompetition(Judge j){
        tmpCompetition.addJudgeToList(j);
    }

    public static void addQualificationToTmpCompetition(Qualification q){
        tmpCompetition.setQualification(q);
    }

    public static void addCountOfRounds(int count){
        tmpCompetition.setAmountOfQualifyingRounds((byte) count);
    }

    public static Competition getTmpCompetition() {
        return tmpCompetition;
    }

    public static Competition getCurrentCompetition() {
        return currentCompetition;
    }

    public static void addToTmpCompetitionName(String name){
        tmpCompetition.setCompetitionName(name);
    }

    public static void setCurrentCompetition(Competition currentCompetition) {
        CompetitionSingleton.currentCompetition = currentCompetition;
    }

    public static void setTmpCompetition(Competition tmpCompetition) {
        CompetitionSingleton.tmpCompetition = tmpCompetition;
    }

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