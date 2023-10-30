package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;

import java.util.ArrayList;

public class Competition {


    // Attributes of the class:
    private String competitionName;
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

    public byte getAmountOfQualifyingRounds (){ // Get an amount of qualifying rounds
        return amountOfQualifyingRounds;
    }

    public void addDriverToList (Driver driver){ // Adding a new driver into the list of drivers
        listOfDrivers.add(driver);
    }

    public void addJudgeToList (Judge judge){ // Adding a new judge into the list of judges
        listOfJudges.add(judge);
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
}
