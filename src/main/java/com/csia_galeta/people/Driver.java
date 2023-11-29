package com.csia_galeta.people;

import com.csia_galeta.CompetitionSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {

    private String driverName;
    private String driverSurname;
    private short number;
    private String team;

    private Map<Integer, Integer> qualificationScore = new HashMap<>();

    public boolean setNameD(String name) { // Setting the name
        if (name.matches("^.{1,55}$")) {
            this.driverName = name;
            return true;
        }
        System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        return false;
    }

    public String getNameD() { // Getting the name
        return driverName;
    }

    public boolean setSurnameD(String surname) { // Setting the surname
        if (surname.matches("^.{1,55}$")) {
            this.driverSurname = surname;
            return true;
        }
        System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        return false;
    }

    public String getSurnameD() { // Getting the surname
        return driverSurname;
    }

    public boolean setNumber(String number) { // Setting the number of the competitor
        if (number.matches("^0*(?:32[0-6][0-7][0-5]|[0-2]?[0-9]{1,4})$")) {
            this.number = Short.parseShort(number);
            return true;
        }
        System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        return false;
    }

    public short getNumber() { // Getting the number of the competitor
        return number;
    }

    public boolean setTeam(String team) { // Setting the driver's team name
        if (team.matches("^.{1,55}$")) {
            this.team = team;
            return true;
        }
        System.out.println("Please check whether entered data is a is a valid team name and less or equal to 55 characters.");
        return false;
    }

    public String getTeam() { // Getting the driver's team name
        return team;
    }

    public Map<Integer, Integer> getQualificationScore() {
        return qualificationScore;
    }

    public void addDriverScoreForQRound(int roundIndex, int score){
        if(!qualificationScore.containsKey(roundIndex))
            qualificationScore.put(roundIndex, score);
        else
            qualificationScore.replace(roundIndex, score);
    }

    @Override
    public String toString() {
        return "Drivers{" +
                "driverName='" + driverName + '\'' +
                ", driverSurname='" + driverSurname + '\'' +
                ", number=" + number +
                ", team='" + team + '\'' +
                '}';
    }

    public String toStringQualificationView() {
        return "Drivers{" +
                "driverName='" + driverName + '\'' +
                ", driverSurname='" + driverSurname + '\'' +
                ", number=" + number +
                ", team='" + team + '\'' +
                '}';
    }
}
