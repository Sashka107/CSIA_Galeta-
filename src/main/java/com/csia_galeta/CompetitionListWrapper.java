package com.csia_galeta;

import com.csia_galeta.ser.DataSaverAndReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionListWrapper {

    private List<Competition> allCompetitions;

    public CompetitionListWrapper(String pathToSave){
        CompetitionListWrapper allCompetitionsFromFile;
        try {
            allCompetitionsFromFile = DataSaverAndReader.readDataFromJson(CompetitionListWrapper.class, pathToSave);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.allCompetitions = allCompetitionsFromFile.getCompetitions();
    }

    public void addCompetition(Competition c){
        if(allCompetitions == null)
            allCompetitions = new ArrayList<>();

        allCompetitions.add(c);
    }

    public List<Competition> getCompetitions(){
        return allCompetitions;
    }

    @Override
    public String toString() {
        return "CompetitionListWrapper{" +
                "allCompetitions=" + allCompetitions +
                '}';
    }
}