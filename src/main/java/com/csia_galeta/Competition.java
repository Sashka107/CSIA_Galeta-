package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.CompetitionStates;

import java.util.ArrayList;
import java.util.Objects;

public class Competition {


    // Attributes of the class:
    private String competitionName;
    private String competitionState;
    private byte amountOfQualifyingRounds;
    private ArrayList<Driver> listOfDrivers = new ArrayList<>();
    private ArrayList<Judge> listOfJudges = new ArrayList<>();



    public void setCompetitionName(String competitionName){ // Set competition name
    this.competitionName = competitionName;
    }

    public String getCompetitionName (){ // Get competition name
        return competitionName;
    }

    public  void setAmountOfQualifyingRounds (byte amountOfQualifyingRounds){ // Set an amount of qualifying rounds
        this.amountOfQualifyingRounds = amountOfQualifyingRounds;

    }

    public ArrayList<Driver> getListOfDrivers() {
        return listOfDrivers;
    }

    public ArrayList<Judge> getListOfJudges() {
        return listOfJudges;
    }

    public byte getAmountOfQualifyingRounds (){ // Get an amount of qualifying rounds
        return amountOfQualifyingRounds;
    }

    public void addDriverToList (Driver driver){ // Adding a new driver into the list of drivers
        listOfDrivers.add(driver);
    }

    public void addJudgeToList (Judge judge){ // Adding a new judge into the list of judges
        listOfJudges.add(judge);
    }

    public String getCompetitionState() {
        return competitionState;
    }

    public void setCompetitionStatePlanned() {
        this.competitionState = CompetitionStates.PLANNED;
    }

    public void setCompetitionStateQualificationInProgress() {
        this.competitionState = CompetitionStates.QUALIFICATION_IN_PROGRESS;
    }

    public void setCompetitionStateQualificationDone() {
        this.competitionState = CompetitionStates.QUALIFICATION_DONE;
    }

    public void setCompetitionStateRunInPairsInProgress() {
        this.competitionState = CompetitionStates.RUN_IN_PAIRS_IN_PROGRESS;
    }

    public void setCompetitionStateFullDone() {
        this.competitionState = CompetitionStates.COMPETITION_DONE;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "competitionName='" + competitionName + '\'' +
                ", amountOfQualifyingRounds=" + amountOfQualifyingRounds +
                ", listOfDrivers=" + listOfDrivers +
                ", listOfJudges=" + listOfJudges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition that)) return false;
        return Objects.equals(competitionName, that.competitionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitionName, amountOfQualifyingRounds, listOfDrivers, listOfJudges);
    }
}
