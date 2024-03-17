package com.csia_galeta.ser;

import com.csia_galeta.people.Driver;
import java.util.List;

/*
 Class ChooseCompetitionTable
 This utility class contains a single static method
 for selecting the appropriate size of the drivers grid.
 */
public class ChooseCompetitionTable {

    // Array of constants - possible sizes of the grid in competitions.
    private static final int[] grades = {2, 4, 8, 16, 32, 64, 128};

    /*
     The method selects the appropriate grid for the list of drivers and if necessary -
     removes excess participants from the end of the list.

     @param drivers - the list of all participants.
     */
    public static void prepareDriversForPairRuns(List<Driver> drivers) {
        int tmpDifference = 0;

        // Searching for a grid that will be smaller than the size of the participants list.
        for (int i = grades.length-1; i >= 0; i--){
            if(drivers.size() >= grades[i]){
                tmpDifference = drivers.size() - grades[i];
                break;
            }
        }

        System.out.println("Delete last " + tmpDifference + " drivers."); //delete last

        // Removing from the end as many drivers as the difference between the size and the grid.
        for (int i = 0; i < tmpDifference; i++) {
            drivers.remove(drivers.size() - 1);
        }
    }

}