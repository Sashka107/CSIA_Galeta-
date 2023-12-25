package com.csia_galeta.people;

import com.csia_galeta.CompetitionSingleton;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    public int pairNum;

    public Driver winner;

    public Driver p1;
    public Driver p2;

    public Pair(Driver p1, Driver p2, int pairNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.pairNum = pairNum;
    }

    public Pair(String unparsedPair, String separator, int pairNum) {
        String[] data = unparsedPair.split(separator);

        List<Driver> drivers = CompetitionSingleton.getCurrentCompetition().getListOfDrivers();

        p1 = CompetitionSingleton.getDriverByNumber(Short.parseShort(data[0]));
        p2 = CompetitionSingleton.getDriverByNumber(Short.parseShort(data[1]));
        this.pairNum = pairNum;
    }

    public static List<Pair> createPairs(List<Driver> drivers){
        List<String> res = new ArrayList<>();
        for(int i = 0, j = drivers.size()-1; i < drivers.size()/2; i++,j--){
            res.add(drivers.get(i).getNumber() + ":" + drivers.get(j).getNumber());
        }

        while (res.size() != 1){
            res = reCalc(res);
        }

        System.out.println(res);
        List<Pair> pairsRes = new ArrayList<>();
        int n = 1;
        for(String str : res.get(0).split(" ")){
            pairsRes.add(new Pair(str, ":", n));
            n++;
        }

        return pairsRes;
    }

    private static List<String> reCalc(List<String> strPairs){
        List<String> res = new ArrayList<>();
        for(int i = 0, j = strPairs.size()-1; i < strPairs.size()/2; i++,j--){
            res.add(strPairs.get(i) + " " + strPairs.get(j));
        }
        return res;
    }

    public int getPairNum() {
        return pairNum;
    }

    public void setPairNum(int pairNum) {
        this.pairNum = pairNum;
    }

    public Driver getWinner() {
        return winner;
    }

    public void setWinner(Driver winner) {
        this.winner = winner;
    }

    public Driver getP1() {
        return p1;
    }

    public void setP1(Driver p1) {
        this.p1 = p1;
    }

    public Driver getP2() {
        return p2;
    }

    public void setP2(Driver p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return  "pair #" + pairNum +
                "\t " + p1.getNameD() + " #" + p1.getNumber() +
                "\t " + p2.getNameD() + " #" + p2.getNumber();
    }
}