package com.csia_galeta;

import com.csia_galeta.people.Driver;
import java.util.List;

/*
 Class Qualification
 This class describes properties for competition qualification,
 as well as functionality necessary for operation in the program
 */
public class Qualification {

    private int[] randIndexes; // Randomly generated indices for shuffling participants.

    /*
     Getter for random indices

     @return random indices
     */
    public int[] getRandNums() {
        return randIndexes;
    }

    /*
     Constructor for creating qualification

     @param drivers the list of drivers
     */
    public Qualification(List<Driver> drivers) {
        randIndexes = generateRandomIndexes(drivers.size());
    }

    /*
     Method for generating random indices for the specified array

     @param size how many indices should be generated
     @return an array of random indices from 0 to size (exclusive)
     */
    private int[] generateRandomIndexes(int size) {
        int[] indexes = new int[size];

        // Fill the indices in normal order.
        for (int i = 0; i < size; i++) {
            indexes[i] = i;
        }

        // Shuffle the indices.
        for (int i = size - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); //casting Math.random generates a random number between 0 and 1.
            int tmp = indexes[i];
            indexes[i] = indexes[j];
            indexes[j] = tmp;
        }

        // Return the indices.
        return indexes;
    }
}