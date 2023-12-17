package com.csia_galeta;

import com.csia_galeta.people.Driver;

import java.util.ArrayList;
import java.util.List;

public class Qualification {

    //private boolean finishedQualification;

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

    /*private List<Driver> shuffleList(List<Driver> list) {
        List<Driver> driversNew = new ArrayList<>(List.copyOf(list));
        int size = driversNew.size();
        for (int i = size - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Driver temp = driversNew.get(i);
            driversNew.set(i, driversNew.get(j));
            driversNew.set(j, temp);
        }
        return driversNew;
    }

    /*public static void main(String[] args) {
        int[] grades = {2, 4, 8, 16, 32, 64, 128};
        ArrayList<String> drivers = new ArrayList<>();

        drivers.add("1");
        drivers.add("2");
        drivers.add("3");
        drivers.add("4");
        drivers.add("5");
        drivers.add("6");
        drivers.add("7");
        drivers.add("8");
        drivers.add("9");
        drivers.add("10");
        drivers.add("11");
        drivers.add("12");
        drivers.add("13");
        drivers.add("14");
        drivers.add("15");

        int difference = 129;
        int tmpDifference;
        int chosenGrade = 0;
        for (int i = 0; i < grades.length; i++) {
            tmpDifference = Math.abs(grades[i] - drivers.size());
            if (difference > tmpDifference) {
                difference = tmpDifference;
                chosenGrade = grades[i];
            }
        }
        System.out.println(chosenGrade);
        System.out.println(difference);

        if (drivers.size() == chosenGrade) {
            makingPairs(drivers);
        } else if (drivers.size() + difference == chosenGrade){
            System.out.println("First " + difference + " drivers are moved to runs in pairs."); //delete first four
            for (int i = 0; i < difference; i++){
                drivers.remove(0);
            }
            makingPairs(drivers);
        } else if (drivers.size() - difference == chosenGrade){
            System.out.println("Delete last " + difference + " drivers."); //delete last four
            for (int i = 0; i < difference; i++){
                drivers.remove(drivers.size()-1);
            }
            makingPairs(drivers);
        }
    }

    public static void makingPairs (ArrayList<String> names){
        for (int i = 0; i < names.size()/2; i++) {
            System.out.println(names.get(i) + "with" + names.get(names.size()-1-i));
        }

    }*/

}