package com.example.csia_galeta;

public class Competition {


    // Attributes of the class:
    public String competitionName;
    public byte amountOfQualifyingRounds;
    public String dateDay;
    public String dateMonth;
    public String dateYear;
    public String Date;


    // Methods for competition Name:

    public void setCompetitionName(String competitionName){
    this.competitionName = competitionName;
    }

    public String getCompetitionName (){
        return competitionName;
    }


    // Methods for an amount of qualifying rounds:

    public  void setAmountOfQualifyingRounds (byte amountOfQualifyingRounds){
        this.amountOfQualifyingRounds = amountOfQualifyingRounds;
    }

    public byte getAmountOfQualifyingRounds (){
        return amountOfQualifyingRounds;
    }


    // Date of the competition

}
