package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.CompetitionStates;
import com.csia_galeta.people.Pair;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Competition {


    // Attributes of the class:
    private String competitionName;
    private String competitionState;

    private String competitionDate;
    private byte amountOfQualifyingRounds;
    private ArrayList<Driver> listOfDrivers = new ArrayList<>();

    private ArrayList<Judge> listOfJudges = new ArrayList<>();

    private Qualification qualification;
    private List<Pair> listOfPairs = new ArrayList<>();


    public void setCompetitionName(String competitionName){ // Set competition name
    this.competitionName = competitionName;
    }

    public String getCompetitionName (){ // Get competition name
        return competitionName;
    }

    public  void setAmountOfQualifyingRounds (byte amountOfQualifyingRounds){ // Set an amount of qualifying rounds
        this.amountOfQualifyingRounds = amountOfQualifyingRounds;
    }

    public String getCompetitionDate() {
        return competitionDate;
    }

    public LocalDate getCompetitionDateLocalDate() {
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.UK);

        if(competitionDate != null)
            return LocalDate.parse(competitionDate, oldFormat);
        else
            return null;
    }

    public void setCompetitionDate(LocalDate competitionDate) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy", new Locale("en"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.UK);
        this.competitionDate = competitionDate.format(formatter);
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public ArrayList<Driver> getListOfDrivers() {
        return listOfDrivers;
    }

    public ArrayList<Judge> getListOfJudges() {
        return listOfJudges;
    }

    public List<Pair> getListOfPairs() {
        return listOfPairs;
    }

    public void setListOfPairs(List<Pair> listOfPairs) {
        this.listOfPairs = listOfPairs;
    }

    public String getListOfJudgesString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Judge j : listOfJudges) {
            stringBuilder.append(j.toString()).append(", ");
        }

        if(stringBuilder.length() > 3)
            stringBuilder.replace(stringBuilder.length()-2, stringBuilder.length()-1, ".");

        return stringBuilder.toString();
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
