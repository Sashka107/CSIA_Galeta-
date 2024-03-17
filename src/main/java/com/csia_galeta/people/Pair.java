package com.csia_galeta.people;

import com.csia_galeta.CompetitionSingleton;

import java.util.ArrayList;
import java.util.List;

/*
 Class Pair
 This class describes the behavior and contents of a pair for the proper
 and full functionality in this program.
 A pair consists of two objects of the Driver class, one of which will become the winner - the third object Driver.
 */
public class Pair {
    public int pairNum; // Variable containing pair's number.

    public Driver winner; // Variable containing pair's winner.

    public Driver p1; // Variable containing first driver from the pair.

    public Driver p2; // Variable containing second driver from the pair.

    /*
     Empty constructor for creating a pair object.
     */
    public Pair() {
    }

    /*
     Constructor for creating a pair object with specified participants and pair number.
     Assigns values to the necessary variables.

     @param p1 the driver for the first participant of the pair
     @param p2 the driver for the second participant of the pair
     @param pairNum the pair number
     */
    public Pair(Driver p1, Driver p2, int pairNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.pairNum = pairNum;
    }

    /*
     Constructor for creating a pair object with specified participants in a string-separated
     format and pair number.
     Converts and assigns values to the necessary variables.

     @param unparsedPair the drivers' numbers in the format: d1,d2
     @param separator the separator used in the unparsedPair param
     @param pairNum the pair number
     */
    public Pair(String unparsedPair, String separator, int pairNum) {
        // Splitting the string into an array of driver numbers.
        String[] data = unparsedPair.split(separator);

        /*
         By converting them into numbers, we obtain driver objects from the current
         competition and save them in variables.
         */
        p1 = CompetitionSingleton.getDriverByNumber(Short.parseShort(data[0]));
        p2 = CompetitionSingleton.getDriverByNumber(Short.parseShort(data[1]));
        this.pairNum = pairNum;
    }

    /*
     Static method of the Pair class
     forms pairs according to a special algorithm developed
     for pairing participants depending on the number of participants.

     @param drivers the list of drivers to form pairs from.
     @return the list of formed pairs.
     */
    public static List<Pair> createPairs(List<Driver> drivers){

        // Outputting all participants for checking in the pair formation.
        drivers.forEach(System.out::println);

        // Forming strings from the participants in the following format: "d1Num:d2Num"....
        List<String> res = new ArrayList<>();
        for(int i = 0, j = drivers.size()-1; i < drivers.size()/2; i++,j--){
            res.add(drivers.get(i).getNumber() + ":" + drivers.get(j).getNumber());
        }

        // Perform narrowing of pairs to the form: "d1Num:d2Num d3Num:d4Num"
        // Do this until we get a long string in this format from all participants.
        while (res.size() != 1){
            res = reCalc(res);
        }

        // Print out what came out on the console.
        System.out.println(res);

        //  Forming a list of pair objects based on the string obtained above.
        List<Pair> pairsRes = new ArrayList<>();
        int n = 1;
        for(String str : res.get(0).split(" ")){ // obtaining strings with data about the pair from the string.
            pairsRes.add(new Pair(str, ":", n)); // Creating an object of pair.
            n++;
        }

        return pairsRes;
    }

    /*
     The method adds a driver to the pair.
     Depending on which driver has already been added, the other one will be added.
     */
    public void addDriver(Driver d){
        if(p1 == null)
            p1 = d;
        else
            p2 = d;
    }

    /*
     Static method of class Pair
     Assembles a new list of strings for pair formation.

     @return a narrowed down list of pairs in the form: "d1Num:d2Num d3Num:d4Num"...
     @param strPairs - a list of strings in the form: "d1Num:d2Num"....
     */
    private static List<String> reCalc(List<String> strPairs){
        List<String> res = new ArrayList<>();
        for(int i = 0, j = strPairs.size()-1; i < strPairs.size()/2; i++,j--){
            res.add(strPairs.get(i) + " " + strPairs.get(j));
        }
        return res;
    }

    /*
     Getter for pair num

     @return pair num
     */
    public int getPairNum() {
        return pairNum;
    }

    /*
     Setter for pair num

     @param pairNum number of pair
     */
    public void setPairNum(int pairNum) {
        this.pairNum = pairNum;
    }

    /*
     Getter for pair winner

     @return pair winner
     */
    public Driver getWinner() {
        return winner;
    }

    /*
     Setter for pair winner

     @param winner - winner of pair
     */
    public void setWinner(Driver winner) {
        this.winner = winner;
    }

    /*
     Getter for pair first player

     @return pair first player
     */
    public Driver getP1() {
        return p1;
    }

    /*
     Setter for pair p1

     @param p1 - p1 for pair
     */
    public void setP1(Driver p1) {
        this.p1 = p1;
    }

    /*
     Getter for pair second player

     @return pair second player
     */
    public Driver getP2() {
        return p2;
    }

    /*
     Setter for pair p2

     @param p2 - p2 for pair
     */
    public void setP2(Driver p2) {
        this.p2 = p2;
    }

    /*
     Method gathers information about the pair and returns it as a string.

     @return all necessary information about the pair as a string.
     */
    @Override
    public String toString() {

        // returns a string with a winner.
        if(winner != null){
            return  "pair #" + pairNum +
                    "\t " + p1.getNameD() + " #" + p1.getNumber() +
                    "\t " + p2.getNameD() + " #" + p2.getNumber() +
                    "\t winner:" + winner.getNameD() + " #" + winner.getNumber();
        }

        // Without a winner.
        return  "pair #" + pairNum +
                "\t " + p1.getNameD() + " #" + p1.getNumber() +
                "\t " + p2.getNameD() + " #" + p2.getNumber();
    }
}