package com.csia_galeta.ser;

import com.csia_galeta.people.Driver;
import java.util.List;

public class ChooseCompetitionTable {
    private static final int[] grades = {2, 4, 8, 16, 32, 64, 128};

    public static void prepareDriversForPairRuns(List<Driver> drivers) {
        int chosenGrade = 0;
        int tmpDifference = 0;

        for (int i = grades.length-1; i >= 0; i--){
            if(drivers.size() >= grades[i]){
                chosenGrade = grades[i];
                tmpDifference = drivers.size() - grades[i];
                break;
            }
        }

        System.out.println("Delete last " + tmpDifference + " drivers."); //delete last four
        for (int i = 0; i < tmpDifference; i++) {
            drivers.remove(drivers.size() - 1);
        }

        /*int difference = 129;



        for (int grade : grades) {
            tmpDifference = Math.abs(grade - drivers.size());
            if (difference > tmpDifference) {
                difference = tmpDifference;
                chosenGrade = grade;
            }
        }

        System.out.println(chosenGrade);
        System.out.println(difference);

        */
    }


}