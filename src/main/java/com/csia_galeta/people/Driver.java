package com.csia_galeta.people;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static final Driver EMPTY_DRIVER = new Driver("Empty", (short) -1);

    private String driverName;
    private String driverSurname;
    private short number;
    private String team;

    public Driver(String driverName, short number) {
        this.driverName = driverName;
        this.number = number;
    }

    public Driver() {
    }

    private List<Integer> qualificationScore = new ArrayList<>();

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

    public List<Integer> getQualificationScore() {
        return qualificationScore;
    }

    public void addDriverScoreForQRound(int score){
        qualificationScore.add(score);
    }

    public int getLastCompletedQRound(){
        return qualificationScore.size();
    }

    public int getMaxQScore(){
        int max = qualificationScore.get(0);
        for(int i = 1; i < qualificationScore.size(); i++){
            if(max < qualificationScore.get(i))
                max = qualificationScore.get(i);
        }
        return max;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int score : qualificationScore) {
            sb.append("/").append(score);
        }

        if(!sb.isEmpty())
            sb.delete(0, 1);

        char firstLetterName = Character.toUpperCase(driverName.charAt(0));

        if(qualificationScore.isEmpty()) {
            return driverName.replace(driverName.charAt(0), firstLetterName)
                    + " " + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team;
            // Alex G 777 OOO
        }
        else {
            return driverName.replace(driverName.charAt(0), firstLetterName)
                    + " " + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team
                    + " scores: " + sb; // Alex G 777 OOO scores: 15/30/45/100
        }
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
