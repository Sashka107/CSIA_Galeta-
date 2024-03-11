package com.csia_galeta.people;

import com.csia_galeta.CompetitionSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Pair
 * Этот класс описывает поведение и что содержит в себе пара для правильной
 * и полноценной работоспособности в этой программе
 * Пара состоит из двух объектов класса Driver, в последствии,
 * один из которых станет победителем - третий объект Driver
 *
 * @see Driver
 * @author Alexander G.
 */
public class Pair {
    public int pairNum; // переменная хранящая номер пары

    public Driver winner; // переменная хранящая победителя пары

    public Driver p1; // переменная хранящая первого водителя из пары

    public Driver p2; // переменная хранящая второго водителя из пары

    /**
     * Пустой конструктор для создания объекта пары
     */
    public Pair() {
    }

    /**
     * Конструктор для создания объекта пары с указанием участников и номера пары
     * Присваивает значения в нужные переменные
     *
     * @param p1 водитель для первого участника пары
     * @param p2 водитель для второго участника пары
     * @param pairNum номер пары
     */
    public Pair(Driver p1, Driver p2, int pairNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.pairNum = pairNum;
    }

    /**
     * Конструктор для создания объекта пары с указанием участников в string-separated
     * формате и номера пары.
     * Преобразовывает и присваивает значения в нужные переменные
     *
     * @param unparsedPair номера водителей в таком формате: d1,d2
     * @param separator разделитель, который использовался в unparsedPair param
     * @param pairNum номер пары
     */
    public Pair(String unparsedPair, String separator, int pairNum) {
        // разделяем строку на массив номеров водителей
        String[] data = unparsedPair.split(separator);

        // преобразовывая в числа получаем объекты водителей из текущего соревнования
        // и сохраняем их в переменные
        p1 = CompetitionSingleton.getDriverByNumber(Short.parseShort(data[0]));
        p2 = CompetitionSingleton.getDriverByNumber(Short.parseShort(data[1]));
        this.pairNum = pairNum;
    }

    /**
     * Static метод класса Pair
     * формирует пары, кто и с кем ездит по специальному алгоритму разработанному
     * для формирования пар в зависимости от количества участников
     *
     * @return список сформированных пар
     * @param drivers список водителей для формирования пар
     */
    public static List<Pair> createPairs(List<Driver> drivers){

        // выводим для проверки всех участников в формировании пар
        drivers.forEach(System.out::println);

        // формируем из участников строки вида: "d1Num:d2Num"....
        List<String> res = new ArrayList<>();
        for(int i = 0, j = drivers.size()-1; i < drivers.size()/2; i++,j--){
            res.add(drivers.get(i).getNumber() + ":" + drivers.get(j).getNumber());
        }

        // выполняем сужения пар до вида: "d1Num:d2Num d3Num:d4Num"
        // делаем єто до тех пор пока не получим длинную строку в таком виде из всех участников
        while (res.size() != 1){
            res = reCalc(res);
        }

        // выводим что вышло на консоль
        System.out.println(res);

        // формируем список объектов пар на основе строки полученной выше
        List<Pair> pairsRes = new ArrayList<>();
        int n = 1;
        for(String str : res.get(0).split(" ")){ // получаем строки с данными о паре из строки
            pairsRes.add(new Pair(str, ":", n)); // создаем объект пары
            n++;
        }

        return pairsRes;
    }

    /**
     * Метод добавляет водителя в пару
     * В зависимости от того какой водитель уже был добавлен - будет добавлен другой
     */
    public void addDriver(Driver d){
        if(p1 == null)
            p1 = d;
        else
            p2 = d;
    }

    /**
     * Static метод класса Pair
     * Собирает новый список строк для формирования пар
     *
     * @return суженный в два раза список пар вида: "d1Num:d2Num d3Num:d4Num"...
     * @param strPairs список строк вида: "d1Num:d2Num"....
     */
    private static List<String> reCalc(List<String> strPairs){
        List<String> res = new ArrayList<>();
        for(int i = 0, j = strPairs.size()-1; i < strPairs.size()/2; i++,j--){
            res.add(strPairs.get(i) + " " + strPairs.get(j));
        }
        return res;
    }

    /**
     * Getter for pair num
     *
     * @return pair num
     */
    public int getPairNum() {
        return pairNum;
    }

    /**
     * Setter for pair num
     *
     * @param pairNum number of pair
     */
    public void setPairNum(int pairNum) {
        this.pairNum = pairNum;
    }

    /**
     * Getter for pair winner
     *
     * @return pair winner
     */
    public Driver getWinner() {
        return winner;
    }

    /**
     * Setter for pair winner
     *
     * @param winner winner of pair
     */
    public void setWinner(Driver winner) {
        this.winner = winner;
    }

    /**
     * Getter for pair first player
     *
     * @return pair first player
     */
    public Driver getP1() {
        return p1;
    }

    /**
     * Setter for pair p1
     *
     * @param p1 p1 for pair
     */
    public void setP1(Driver p1) {
        this.p1 = p1;
    }

    /**
     * Getter for pair second player
     *
     * @return pair second player
     */
    public Driver getP2() {
        return p2;
    }

    /**
     * Setter for pair p2
     *
     * @param p2 p2 for pair
     */
    public void setP2(Driver p2) {
        this.p2 = p2;
    }

    /**
     * Метод собирает информациюю о паре и возвращает её в формате строки
     *
     * @return вся нужная информация о паре в виде строки
     */
    @Override
    public String toString() {

        // Возвращает строку с победителем
        if(winner != null){
            return  "pair #" + pairNum +
                    "\t " + p1.getNameD() + " #" + p1.getNumber() +
                    "\t " + p2.getNameD() + " #" + p2.getNumber() +
                    "\t winner:" + winner.getNameD() + " #" + winner.getNumber();
        }

        // без победителя
        return  "pair #" + pairNum +
                "\t " + p1.getNameD() + " #" + p1.getNumber() +
                "\t " + p2.getNameD() + " #" + p2.getNumber();
    }
}