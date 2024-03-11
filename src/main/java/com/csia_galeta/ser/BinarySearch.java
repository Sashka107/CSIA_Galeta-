package com.csia_galeta.ser;

import com.csia_galeta.people.Driver;

import java.util.List;

/**
 * Class BinarySearch
 * Этот утилитарный класс содержит один статический метод
 * для бинарного поиска по списку водителей
 *
 * @author Alexander G.
 */
public class BinarySearch {

    /**
     * Метод ищет по алгоритму бинарного поиска водителя по номеру из списка
     *
     * @param drivers - отсортированный список водителей
     * @param number - номер по которому нужно найти водителя
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