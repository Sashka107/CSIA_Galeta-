package com.csia_galeta.people;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Driver
 * Этот класс описывает поведение и что содержит в себе водитель для правильной
 * и полноценной работоспособности в этой программе
 *
 * @author Alexander G.
 */
public class Driver {

    private String driverName = "Empty"; // переменная хранящая имя
    private String driverSurname = "Empty"; // переменная хранящая фамилию
    private short number = -1; // переменная хранящая номер
    private String team = "Empty"; // переменная хранящая команду

    private List<Integer> qualificationScore = new ArrayList<>(); // переменная хранящая очки квалификации

    /**
     * Метод setter для установки имени
     *
     * @return true - если имя успешно прошло проверку и установлено, false - если нет
     * @param name - имя, которое должно быть установлено
     */
    public boolean setNameD(String name) {
        // если имя соответствует требованиям от 1 до 55 символов
        if (name.matches("^.{1,55}$")) {
            this.driverName = name;
            return true;
        }

        // если не соответствует
        System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        return false;
    }

    /**
     * Getter for driver`s name
     *
     * @return name of the driver
     */
    public String getNameD() {
        return driverName;
    }

    /**
     * Метод setter для установки фамилии
     *
     * @return true - если фамилия успешно прошло проверку и установлено, false - если нет
     * @param surname - фамилия, которая должна быть установлена
     */
    public boolean setSurnameD(String surname) {
        // если фамилия соответствует требованиям от 1 до 55 символов
        if (surname.matches("^.{1,55}$")) {
            this.driverSurname = surname;
            return true;
        }

        // если не соответствует
        System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        return false;
    }

    /**
     * Getter for driver`s lastname
     *
     * @return lastname of the driver
     */
    public String getSurnameD() {
        return driverSurname;
    }

    /**
     * Метод setter для установки номера
     *
     * @return true - если номер успешно прошел проверку и установлен, false - если нет
     * @param number - номер, который должен быть установлен
     */
    public boolean setNumber(String number) {

        // если номер соответствует требованиям
        if (number.matches("^0*(?:32[0-6][0-7][0-5]|[0-2]?[0-9]{1,4})$")) {
            this.number = Short.parseShort(number);
            return true;
        }

        // если не соответствует
        System.out.println("Please check whether entered data is a is a valid number and is less than a 10000.");
        return false;
    }

    /**
     * Getter for driver`s number
     *
     * @return number of the driver
     */
    public short getNumber() {
        return number;
    }

    /**
     * Метод setter для установки команды
     *
     * @return true - если команда успешно прошло проверку и установлено, false - если нет
     * @param team - команда, которая должна быть установлена
     */
    public boolean setTeam(String team) {

        // если название команды соответствует требованиям от 1 до 55 символов
        if (team.matches("^.{1,55}$")) {
            this.team = team;
            return true;
        }

        // если нет
        System.out.println("Please check whether entered data is a is a valid team name and less or equal to 55 characters.");
        return false;
    }

    /**
     * Getter for driver`s team
     *
     * @return team of the driver
     */
    public String getTeam() {
        return team;
    }

    /**
     * Getter for driver`s qualification scores list
     *
     * @return qualification scores list of the driver
     */
    public List<Integer> getQualificationScore() {
        return qualificationScore;
    }

    /**
     * Метод для добавления очка к списку очков за квалификацию
     *
     * @param score - очко, которое должно быть добавлено к списку
     */
    public void addDriverScoreForQRound(int score){
        qualificationScore.add(score);
    }

    /**
     * Getter for last completed qualification round
     *
     * @return size of the list qualification
     */
    public int getLastCompletedQRound(){
        return qualificationScore.size();
    }

    /**
     * Метод, который ищет максимальную оценку из всех раундов квалификации
     *
     * @return максимальную оценку за раунд из квалицикационных заездов
     */
    public int getMaxQScore(){
        int max = qualificationScore.get(0);
        for(int i = 1; i < qualificationScore.size(); i++){
            if(max < qualificationScore.get(i))
                max = qualificationScore.get(i);
        }
        return max;
    }

    /**
     * Метод собирает информациюю о водителе и возвращает её в формате строки
     *
     * @return вся нужная информация о водителе в виде строки
     */
    @Override
    public String toString() {

        // преобразовываем очки из списка в вид: xx/xx/xx... где х - число
        StringBuilder sb = new StringBuilder();
        for (int score : qualificationScore) {
            sb.append("/").append(score);
        }

        // обрезаем первый символ '/'
        if(!sb.isEmpty())
            sb.delete(0, 1);

        // получаем первый символ имени и делаем его заглавым
        char firstLetterName = Character.toUpperCase(driverName.charAt(0));

        // если очки за квалификацию пусты, то отображаем водителя так: "Name L. number team"
        if(qualificationScore.isEmpty()) {
            return driverName.replace(driverName.charAt(0), firstLetterName)
                    + " " + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team;
        }
        // если очки за квалификацию есть, то отображаем водителя так: "Name L. number team scores: xx/xx/xx..."
        else {
            return driverName.replace(driverName.charAt(0), firstLetterName)
                    + " " + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team
                    + " scores: " + sb;
        }
    }

    /**
     * Метод собирает информациюю о водителе и возвращает её в формате строки
     *
     * @return вся нужная информация о водителе в виде строки для парного заезда
     */
    public String toStringPairEdit() {

        // получаем первый символ имени и делаем его заглавым
        char firstLetterName = Character.toUpperCase(driverName.charAt(0));

        // возвращает в формате: "Name L. number team"
        return driverName.replace(driverName.charAt(0), firstLetterName) + " "
                + Character.toUpperCase(driverSurname.charAt(0)) + " " + number + " " + team;

    }

    /**
     * Метод собирает информациюю о водителе и возвращает её в формате строки
     *
     * @return вся нужная информация о водителе в виде строки для отображения на окне победителя
     */
    public String toStringWinner(){
        // возвращает в формате: "Name L. | number"
        return driverName + " " + Character.toUpperCase(driverName.charAt(0)) + " | " + number;
    }

    /**
     * Метод собирает информациюю о водителе и возвращает её в формате строки
     *
     * @return вся нужная информация о водителе в виде строки для упрощенного отображения
     */
    public String toStringSimple(){
        // возвращает в формате: "Name L."
        return driverName + " " + Character.toUpperCase(driverName.charAt(0));
    }
}
