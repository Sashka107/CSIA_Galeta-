package com.csia_galeta.ser;

import com.csia_galeta.people.Driver;
import java.util.List;

public class ChooseCompetitionTable {
    private static final int[] grades = {2, 4, 8, 16, 32, 64, 128};

    public static void prepareDriversForPairRuns(List<Driver> drivers) {
        int difference = 129;
        int tmpDifference;
        int chosenGrade = 0;

        for (int grade : grades) {
            tmpDifference = Math.abs(grade - drivers.size());
            if (difference > tmpDifference) {
                difference = tmpDifference;
                chosenGrade = grade;
            }
        }

        System.out.println(chosenGrade);
        System.out.println(difference);

        if (drivers.size() + difference == chosenGrade) {
            System.out.println("First " + difference + " drivers are moved to runs in pairs."); //delete first four
            if (difference > 0) {
                drivers.add(Driver.EMPTY_DRIVER);
            }
        } else if (drivers.size() - difference == chosenGrade) {
            System.out.println("Delete last " + difference + " drivers."); //delete last four
            for (int i = 0; i < difference; i++) {
                drivers.remove(drivers.size() - 1);
            }
        }
    }


}