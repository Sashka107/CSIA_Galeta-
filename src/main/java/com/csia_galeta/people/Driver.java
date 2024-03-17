package com.csia_galeta.people;

import java.util.ArrayList;
import java.util.List;

/*
 Class Driver
 This class describes the behavior and contents of a driver for the proper
 and full functionality in this program.
 */
public class Driver {

    private String driverName = "Empty"; // Variable containing name.
    private String driverSurname = "Empty"; // Variable containing surname.
    private short number = -1; // пVariable containing number.
    private String team = "Empty"; // Variable containing team.
    private List<Integer> qualificationScore = new ArrayList<>(); // List containing qualification score.

    /*
     Setter method for setting the name.

     @param name - the name to be set.
     @return true - if the name successfully passed validation and set, false - otherwise.
     */
    public boolean setNameD(String name) {
        // If the name meets the requirements of 1 to 55 characters.
        if (name.matches("^.{1,55}$")) {
            this.driverName = name;
            return true;
        }

        // If does not:
        System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        return false;
    }

    /*
     Getter for driver`s name

     @return name of the driver
     */
    public String getNameD() {
        return driverName;
    }

    /*
     Setter method for setting the surname.

     @param surname - the surname to be set.
     @return true - if the surname successfully passed validation and set, false - otherwise.
     */
    public boolean setSurnameD(String surname) {
        // еIf the surname meets the requirements of 1 to 55 characters.
        if (surname.matches("^.{1,55}$")) {
            this.driverSurname = surname;
            return true;
        }

        // If does not:
        System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        return false;
    }

    /*
     Getter for driver`s lastname

     @return lastname of the driver
     */
    public String getSurnameD() {
        return driverSurname;
    }

    /*
     Setter method for setting the number.

     @param number - the number to be set.
     @return true - if the number successfully passed validation and set, false - otherwise.
     */
    public boolean setNumber(String number) {

        // If the number meets the requirements.
        if (number.matches("^0*(?:32[0-6][0-7][0-5]|[0-2]?[0-9]{1,4})$")) {
            this.number = Short.parseShort(number);
            return true;
        }

        // If does not:
        System.out.println("Please check whether entered data is a is a valid number and is less than a 10000.");
        return false;
    }

    /*
     Getter for driver`s number

     @return number of the driver
     */
    public short getNumber() {
        return number;
    }

    /*
     Setter method for setting the team.

     @param team - the team to be set.
     @return true - if the team successfully passed validation and set, false - otherwise.
     */
    public boolean setTeam(String team) {

        // If the team name meets the requirements of 1 to 55 characters.
        if (team.matches("^.{1,55}$")) {
            this.team = team;
            return true;
        }

        // If not:
        System.out.println("Please check whether entered data is a is a valid team name and less or equal to 55 characters.");
        return false;
    }

    /*
     Getter for driver`s team

     @return team of the driver
     */
    public String getTeam() {
        return team;
    }

    /*
     Getter for driver`s qualification scores list

     @return qualification scores list of the driver
     */
    public List<Integer> getQualificationScore() {
        return qualificationScore;
    }

    /*
     Method for adding a point to the qualification points list.

     @param score - the score to be added to the list.
     */
    public void addDriverScoreForQRound(int score){
        qualificationScore.add(score);
    }

    /*
     Getter for last completed qualification round

     @return size of the list qualification
     */
    public int getLastCompletedQRound(){
        return qualificationScore.size();
    }

    /*
     Method that finds the maximum score from all qualification rounds.

     @return the maximum score per round from the qualification races.
     */
    public int getMaxQScore(){
        int max = qualificationScore.get(0);
        for(int i = 1; i < qualificationScore.size(); i++){
            if(max < qualificationScore.get(i))
                max = qualificationScore.get(i);
        }
        return max;
    }

    /*
     Method gathers information about the driver and returns it as a string.

     @return all necessary information about the driver as a string.
     */
    @Override
    public String toString() {

        // Converting the scores from the list into the format: xx/xx/xx... where x is a number.
        StringBuilder sb = new StringBuilder();
        for (int score : qualificationScore) {
            sb.append("/").append(score);
        }

        // Cutting first character '/'.
        if(!sb.isEmpty())
            sb.delete(0, 1);

        // Obtaining the first character of the name and capitalise it.
        char firstLetterName = Character.toUpperCase(driverName.charAt(0));

        // If the qualification scores are empty, then display the driver as follows: "Name L. number team"
        if(qualificationScore.isEmpty()) {
            return driverName.replace(driverName.charAt(0), firstLetterName)
                    + " " + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team;
        }
        // If there are qualification scores, then display the driver as follows: "Name L. number team scores: xx/xx/xx..."
        else {
            return driverName.replace(driverName.charAt(0), firstLetterName)
                    + " " + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team
                    + " scores: " + sb;
        }
    }

    /*
     Method gathers information about the driver and returns it as a string in the format suitable for paired races.

     @return all necessary information about the driver as a string for paired races.
     */
    public String toStringPairEdit() {

        // Getting the first character of the name and capitalise it.
        char firstLetterName = Character.toUpperCase(driverName.charAt(0));

        // Returns in format: "Name L. number team"
        return driverName.replace(driverName.charAt(0), firstLetterName) + " "
                + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team;

    }

    /*
     Method gathers information about the driver and returns it as a string in the format suitable for displaying on the winner window.

     @return all necessary information about the driver as a string for displaying on the winner window.
     */
    public String toStringWinner(){
        // Returns in format: "Name L. | number".
        return driverName + " " + Character.toUpperCase(driverName.charAt(0)) + " | " + number;
    }

    /*
     Method gathers information about the driver and returns it as a string in a simplified format.

     @return all necessary information about the driver as a string for simplified display.
     */
    public String toStringSimple(){
        // Returns in format: "Name L.".
        return driverName + " " + Character.toUpperCase(driverName.charAt(0));
    }
}
