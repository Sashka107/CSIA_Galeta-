package com.csia_galeta;

import com.csia_galeta.people.Driver;
import java.util.List;

/**
 * Class Qualification
 * Этот класс описывает свойства для квалификации соревнования,
 * а также функционал необходимы для функционрования в программе
 *
 * @author Alexander G.
 */
public class Qualification {

    private int[] randIndexes; // рандомно сгенерированые индексы для перемешивания участников

    /**
     * Getter для рандомных индексов
     *
     * @return рандомные индексы
     */
    public int[] getRandNums() {
        return randIndexes;
    }

    /**
     * Конструктор для создания квалификации
     *
     * @param drivers список водителей
     */
    public Qualification(List<Driver> drivers) {
        randIndexes = generateRandomIndexes(drivers.size());
    }

    /**
     * Метод для генирации случайных индексов для указанного массива
     *
     * @return массив случайных индексов от 0 до size (не включительно)
     * @param size сколько должно быть индексов
     */
    private int[] generateRandomIndexes(int size) {
        int[] indexes = new int[size];

        // заполняем в обычном порядке индексы
        for (int i = 0; i < size; i++) {
            indexes[i] = i;
        }

        // перемешиваем индексы
        for (int i = size - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); //casting Math . random generates a random number between 0 and 1
            int tmp = indexes[i];
            indexes[i] = indexes[j];
            indexes[j] = tmp;
        }

        // возвращаем индексы
        return indexes;
    }
}