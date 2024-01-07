package com.csia_galeta;

import com.csia_galeta.people.Driver;
import java.util.List;

public class Qualification {

    private int[] randIndexes;

    public int[] getRandNums() {
        return randIndexes;
    }

    public Qualification(List<Driver> drivers) {
        randIndexes = generateRandomIndexes(drivers.size());
    }

    private int[] generateRandomIndexes(int size) {
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = i;
        }

        for (int i = size - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); //casting Math . random generates a random number between 0 and 1
            int tmp = indexes[i];
            indexes[i] = indexes[j];
            indexes[j] = tmp;
        }
        return indexes;
    }
}