package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.DataSaverAndReader;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompetitionSingleton {

    private static CompetitionListWrapper competitionListWrapper = null;
    private static Competition tmpCompetition = new Competition();

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

    public static void driverAddHandler(Consumer<Driver> func){
        OnDriverAdded = func;
    }

    public static void saveTmpCompetition(){
        competitionListWrapper.addCompetition(tmpCompetition);
        DataSaverAndReader.saveJsonStringToFile("save.json", competitionListWrapper);
        tmpCompetition = null;
        tmpCompetition = new Competition();

    }

    public static void addDriverToTmpCompetition(Driver d){
        tmpCompetition.addDriverToList(d);
        if(OnDriverAdded != null)
            OnDriverAdded.accept(d);
    }

    public static void addJudgeToTmpCompetition(Judge j){
        tmpCompetition.addJudgeToList(j);
    }

    public static void addCountOfRounds(int count){
        tmpCompetition.setAmountOfQualifyingRounds((byte) count);
    }

    public static void addToTmpCompetitionName(String name){
        tmpCompetition.setCompetitionName(name);
    }
}