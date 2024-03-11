package com.csia_galeta.ser;

import com.csia_galeta.people.Driver;
import java.util.List;

/**
 * Class ChooseCompetitionTable
 * Этот утилитарный класс содержит один статический метод
 * для подбора нужного размера сетки водителей
 *
 * @author Alexander G.
 */
public class ChooseCompetitionTable {

    // массив констант-размеров возможной сетки в соревнованиях
    private static final int[] grades = {2, 4, 8, 16, 32, 64, 128};

    /**
     * Метод подбирает нужную сетку для списка водителей и если есть -
     * удаляет лишних участников с конца списка
     *
     * @param drivers - список всех участников
     */
    public static void prepareDriversForPairRuns(List<Driver> drivers) {
        int tmpDifference = 0;

        // ищем сетку, которая будет меньше размера списка участников
        for (int i = grades.length-1; i >= 0; i--){
            if(drivers.size() >= grades[i]){
                tmpDifference = drivers.size() - grades[i];
                break;
            }
        }

        System.out.println("Delete last " + tmpDifference + " drivers."); //delete last

        // удаляем с конца столько водителей, сколько была разница от размера и сетки
        for (int i = 0; i < tmpDifference; i++) {
            drivers.remove(drivers.size() - 1);
        }
    }

}