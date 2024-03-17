package com.csia_galeta.ser;

import com.csia_galeta.people.Driver;

import java.util.List;

/*
 Class BinarySearch
 This utility class contains a single static method
 for binary search through the list of drivers.
 */
public class BinarySearch {

    /*
     The method searches for a driver by number using the binary search algorithm in the list.

     @param drivers - the sorted list of drivers.
     @param number - the number to find the driver by.
     @return the driver object if found, otherwise null.
     */
    public static Driver binarySearch(List<Driver> drivers, short number) {

        int index = 0;

        int l = 0, r = drivers.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (drivers.get(m).getNumber() == number)
                index = m;

            // If x greater, ignore left half
            if (drivers.get(m).getNumber() < number)
                l = m + 1;

            // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        return drivers.get(index);
    }

}