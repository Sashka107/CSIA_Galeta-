package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.CompetitionStates;
import com.csia_galeta.people.Pair;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Class Competition
 * Этот класс описывает свойства и функционал соревнования
 * для корректного функционаирования в программе
 *
 * @author Alexander G.
 */
public class Competition {

    private String competitionName; // переменная для хранения названия соревнования
    private String competitionState; // переменная для хранения состояния соревнования
    private String competitionDate; // переменная для хранения даты соревнования
    private byte amountOfQualifyingRounds; // переменная для хранения количества раундов квалицикации
    private List<Driver> listOfDrivers = new ArrayList<>(); // переменная для хранения списка всех водителей
    private List<Driver> qualificationOfDrivers = new ArrayList<>(); // список водителей в квалификации
    private ArrayList<Judge> listOfJudges = new ArrayList<>(); // список судий
    private Qualification qualification; // объект квалификации
    private List<Pair> listOfPairs = new ArrayList<>(); // список пар для парных заездов

    /**
     * Setter for competition name
     *
     * @param competitionName name of the competition
     */
    public void setCompetitionName(String competitionName){
    this.competitionName = competitionName;
    }

    /**
     * Getter для списка водителей квалификации соревнования
     *
     * @return список квалификации в соревновании
     */
    public List<Driver> getQualificationOfDrivers() {
        return qualificationOfDrivers;
    }

    /**
     * Setter for competition list of qualification drivers
     *
     * @param qualificationOfDrivers list of drivers for qualification
     */
    public void setQualificationOfDrivers(List<Driver> qualificationOfDrivers) {
        this.qualificationOfDrivers = new ArrayList<>(qualificationOfDrivers);
    }

    /**
     * Getter для названия соревнования
     *
     * @return название соревнования
     */
    public String getCompetitionName (){
        return competitionName;
    }

    /**
     * Setter for amount of qualification round in competition
     *
     * @param amountOfQualifyingRounds amount of rounds
     */
    public  void setAmountOfQualifyingRounds (byte amountOfQualifyingRounds){
        this.amountOfQualifyingRounds = amountOfQualifyingRounds;
    }

    /**
     * Getter for competition date
     *
     * @return date of competition
     */
    public String getCompetitionDate() {
        return competitionDate;
    }

    /**
     * Getter для получения даты соревнования
     *
     * @return дату соревнования
     */
    public LocalDate getCompetitionDateLocalDate() {

        // создаем объект DateTimeFormatter для формата даты по шаблону
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.UK);

        if(competitionDate != null) // если дата не пуста, то пытаемся преобразовать из строки
            return LocalDate.parse(competitionDate, oldFormat);
        else
            return null;
    }

    /**
     * Setter for competition date
     *
     * @param competitionDate date to set
     */
    public void setCompetitionDate(LocalDate competitionDate) {

        // создаем объект DateTimeFormatter для формата даты по шаблону
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.UK);
        this.competitionDate = competitionDate.format(formatter);
    }

    /**
     * Setter for qualification in competition
     *
     * @param qualification object of qualification
     */
    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    /**
     * Getter для квалификации соревнования
     *
     * @return квалификацию соревнования
     */
    public Qualification getQualification() {
        return qualification;
    }

    /**
     * Getter для списка всех водителей соревнования
     *
     * @return список всех водителей
     */
    public List<Driver> getListOfDrivers() {
        return listOfDrivers;
    }

    /**
     * Getter для списка судий соревнования
     *
     * @return список судий
     */
    public ArrayList<Judge> getListOfJudges() {
        return listOfJudges;
    }

    /**
     * Getter для списка пар соревнования
     *
     * @return список пар для парных заездов
     */
    public List<Pair> getListOfPairs() {
        return listOfPairs;
    }

    /**
     * Setter for list of pairs in competition
     *
     * @param listOfPairs list of pairs
     */
    public void setListOfPairs(List<Pair> listOfPairs) {
        this.listOfPairs = listOfPairs;
    }

    /**
     * Getter для списка судий соревнования в формате строки удобной для отображения
     *
     * @return строка с информацией про судий
     */
    public String getListOfJudgesString() {

        // создаем объект StringBuilder для того, чтобы собрать всех судий в одну строку
        StringBuilder stringBuilder = new StringBuilder();
        for (Judge j : listOfJudges) { // собираем в одну строку
            stringBuilder.append(j.toString()).append(", ");
        }

        // если объект StringBuilder не остался пустым
        if(stringBuilder.length() > 3)
            // то производим замену последней запятой на точку
            stringBuilder.replace(stringBuilder.length()-2, stringBuilder.length()-1, ".");

        // возвращаем собранную информацию про судий преобразовав её в строку
        return stringBuilder.toString();
    }

    /**
     * Getter для количества раундов квалификации соревнования
     *
     * @return количество раундов квалификации
     */
    public byte getAmountOfQualifyingRounds (){
        return amountOfQualifyingRounds;
    }

    /**
     * Метод для добавления водителя к списку всех водителей
     *
     * @param driver водитель для добавления
     */
    public void addDriverToList (Driver driver){
        listOfDrivers.add(driver);
    }

    /**
     * Метод для добавления судьи к списку всех судий
     *
     * @param judge судья для добавления
     */
    public void addJudgeToList (Judge judge){
        listOfJudges.add(judge);
    }

    /**
     * Getter для состояния соревнования
     *
     * @return текущее состояние соревнования
     */
    public String getCompetitionState() {
        return competitionState;
    }

    /**
     * Setter for state PLANNED in competition
     */
    public void setCompetitionStatePlanned() {
        this.competitionState = CompetitionStates.PLANNED;
    }

    /**
     * Setter for state QUALIFICATION IN PROGRESS in competition
     */
    public void setCompetitionStateQualificationInProgress() {
        this.competitionState = CompetitionStates.QUALIFICATION_IN_PROGRESS;
    }

    /**
     * Setter for state FINAL ROUND in competition
     */
    public void setCompetitionStateFinalRound() {
        this.competitionState = CompetitionStates.FINAL_ROUND;
    }

    /**
     * Setter for state RUN IN PAIRS IN PROGRESS in competition
     */
    public void setCompetitionStateRunInPairsInProgress() {
        this.competitionState = CompetitionStates.RUN_IN_PAIRS_IN_PROGRESS;
    }

    /**
     * Setter for state COMPETITION IS FINISHED in competition
     */
    public void setCompetitionStateFullDone() {
        this.competitionState = CompetitionStates.COMPETITION_IS_FINISHED;
    }

    /**
     * Setter for list of drivers in competition
     *
     * @param listOfDrivers list of drivers to set
     */
    public void setListOfDrivers(List<Driver> listOfDrivers) {
        this.listOfDrivers = listOfDrivers;
    }

    /**
     * Метод собирает и возвращает самую нужную информацию
     * о соревновании в формате строки
     *
     * @return строку с информацией о соревновании
     */
    @Override
    public String toString() {
        return "Name: " + competitionName +
                " rounds: " + amountOfQualifyingRounds +
                " | drivers compete: " + listOfDrivers.size() +
                " | state: " + competitionState;
    }

    /**
     * Переопределяем метод для того, чтобы иметь возможность
     * сравнивать разные объекты класса Competition
     *
     * @param o объект для сравнения
     * @return true - если объекты полностью идентичны и false - если есть разница
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition that)) return false;
        return Objects.equals(competitionName, that.competitionName);
    }

    /**
     * Переопределяем метод для того, чтобы иметь возможность
     * получать хеш-код объектов класса Competition
     *
     * @return число, которое является хеш-кодом обьекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(competitionName, amountOfQualifyingRounds, listOfDrivers, listOfJudges);
    }
}
