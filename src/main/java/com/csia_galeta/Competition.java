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

/*
 Class Competition
 This class describes the properties and functionality of a competition
 for proper functioning within the program.
 */
public class Competition {

    private String competitionName; // Variable for storing the name of the competition.
    private String competitionState; // Variable for storing the state of the competition.
    private String competitionDate; // Variable for storing the date of the competition.
    private byte amountOfQualifyingRounds; // Variable for storing the number of qualification rounds.
    private List<Driver> listOfDrivers = new ArrayList<>(); // List for storing a list of all drivers.
    private List<Driver> qualificationOfDrivers = new ArrayList<>(); // List of drivers in the qualification.
    private ArrayList<Judge> listOfJudges = new ArrayList<>(); // List of judges.
    private Qualification qualification; // Qualification object.
    private List<Pair> listOfPairs = new ArrayList<>(); // List of pairs for runs in pairs.

    /*
     Setter for competition name

     @param competitionName name of the competition
     */
    public void setCompetitionName(String competitionName){
    this.competitionName = competitionName;
    }

    /*
     Getter for the list of drivers in the competition's qualification

     @return the list of drivers in the competition's qualification
     */
    public List<Driver> getQualificationOfDrivers() {
        return qualificationOfDrivers;
    }

    /*
     Setter for competition list of qualification drivers

     @param qualificationOfDrivers list of drivers for qualification
     */
    public void setQualificationOfDrivers(List<Driver> qualificationOfDrivers) {
        this.qualificationOfDrivers = new ArrayList<>(qualificationOfDrivers);
    }

    /*
     Getter for the competition name

     @return the name of the competition
     */
    public String getCompetitionName (){
        return competitionName;
    }

    /*
     Setter for amount of qualification round in competition

     @param amountOfQualifyingRounds amount of rounds
     */
    public  void setAmountOfQualifyingRounds (byte amountOfQualifyingRounds){
        this.amountOfQualifyingRounds = amountOfQualifyingRounds;
    }

    /*
     Getter for competition date

     @return date of competition
     */
    public String getCompetitionDate() {
        return competitionDate;
    }

    /*
     Getter for retrieving the date of the competition

     @return the date of the competition
     */
    public LocalDate getCompetitionDateLocalDate() {

        // Creating a DateTimeFormatter object for formatting dates according to a pattern.
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.UK);

        if(competitionDate != null) // If the date is not empty, attempt to parse it from a string.
            return LocalDate.parse(competitionDate, oldFormat);
        else
            return null;
    }

    /*
     Setter for competition date

     @param competitionDate date to set
     */
    public void setCompetitionDate(LocalDate competitionDate) {

        // Creating a DateTimeFormatter object for formatting dates according to a pattern.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.UK);
        this.competitionDate = competitionDate.format(formatter);
    }

    /*
     Setter for qualification in competition

     @param qualification object of qualification
     */
    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    /*
     Getter for the qualification of  the competition

     @return qualification of the competition
     */
    public Qualification getQualification() {
        return qualification;
    }

    /*
     Getter for the list of all drivers in the competition

     @return list of all drivers
     */
    public List<Driver> getListOfDrivers() {
        return listOfDrivers;
    }

    /*
     Getter for the list of judges in the competition

     @return list of judges
     */
    public ArrayList<Judge> getListOfJudges() {
        return listOfJudges;
    }

    /*
     Getter for the list of pairs of the competition

     @return list of pairs for the pair runs
     */
    public List<Pair> getListOfPairs() {
        return listOfPairs;
    }

    /*
     Setter for list of pairs in competition

     @param listOfPairs list of pairs
     */
    public void setListOfPairs(List<Pair> listOfPairs) {
        this.listOfPairs = listOfPairs;
    }

    /*
     Getter for the list of competition judges in a format suitable for display

     @return a string containing information about the judges
     */
    public String getListOfJudgesString() {

        // Creating a StringBuilder object to concatenate all judges into one string.
        StringBuilder stringBuilder = new StringBuilder();
        for (Judge j : listOfJudges) { // Concatenating into one string.
            stringBuilder.append(j.toString()).append(", ");
        }

        // If the StringBuilder object is not empty.
        if(stringBuilder.length() > 3)
            // Then replace the last comma with a period.
            stringBuilder.replace(stringBuilder.length()-2, stringBuilder.length()-1, ".");

        // Return the assembled information about the judges, converted into a string.
        return stringBuilder.toString();
    }

    /*
     Getter for the number of qualification rounds in the competition

     @return the number of qualification rounds
     */
    public byte getAmountOfQualifyingRounds (){
        return amountOfQualifyingRounds;
    }

    /*
     Method for adding a driver to the list of all drivers

     @param driver - the driver to add
     */
    public void addDriverToList (Driver driver){
        listOfDrivers.add(driver);
    }

    /*
     Method for adding a judge to the list of all judges

     @param judge - the judge to add
     */
    public void addJudgeToList (Judge judge){
        listOfJudges.add(judge);
    }

    /*
     Getter for the state of the competition

     @return the current state of the competition
     */
    public String getCompetitionState() {
        return competitionState;
    }

    // Setter for the PLANNED state in the competition.
    public void setCompetitionStatePlanned() {
        this.competitionState = CompetitionStates.PLANNED;
    }

    // Setter for the QUALIFICATION IN PROGRESS state in the competition.
    public void setCompetitionStateQualificationInProgress() {
        this.competitionState = CompetitionStates.QUALIFICATION_IN_PROGRESS;
    }

    // Setter for the FINAL ROUND state in the competition.
    public void setCompetitionStateFinalRound() {
        this.competitionState = CompetitionStates.FINAL_ROUND;
    }

     // Setter for state RUN IN PAIRS IN PROGRESS in competition.
    public void setCompetitionStateRunInPairsInProgress() {
        this.competitionState = CompetitionStates.RUN_IN_PAIRS_IN_PROGRESS;
    }

    // Setter for state COMPETITION IS FINISHED in competition.
    public void setCompetitionStateFullDone() {
        this.competitionState = CompetitionStates.COMPETITION_IS_FINISHED;
    }

    /*
     Setter for list of drivers in competition

     @param listOfDrivers list of drivers to set
     */
    public void setListOfDrivers(List<Driver> listOfDrivers) {
        this.listOfDrivers = listOfDrivers;
    }

    /*
     This method gathers and returns the most essential information
     about the competition in string format

     @return a string containing information about the competition
     */
    @Override
    public String toString() {
        return "Name: " + competitionName +
                " rounds: " + amountOfQualifyingRounds +
                " | drivers compete: " + listOfDrivers.size() +
                " | state: " + competitionState;
    }

    /*
     Override the method to enable comparison between different Competition objects

     @param o the object to compare
     @return true if the objects are completely identical, false if there are differences
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition that)) return false;
        return Objects.equals(competitionName, that.competitionName);
    }

    /*
     Override the method to enable obtaining hash codes of Competition objects

     @return a number that is the hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(competitionName, amountOfQualifyingRounds, listOfDrivers, listOfJudges);
    }
}