package com.csia_galeta;

import com.csia_galeta.ser.DataSaverAndReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CompetitionListWrapper
 * Этот класс-обертка помогает работать сразу с несколькими соревнованиями
 * и добавляет необходимый функционал для удобства
 *
 * @author Alexander G.
 */
public class CompetitionListWrapper {

    private List<Competition> allCompetitions; // список всех соревнований за все время


    /**
     * Конструктор, который загружает информацию о соревнованиях из файла JSON
     *
     * @param pathToSave путь к файлу с сохранениями в формате JSON
     */
    public CompetitionListWrapper(String pathToSave){

        // создаем переменную класса-обертки для соревнований
        CompetitionListWrapper allCompetitionsFromFile;
        try { // пробуем считать информацию из файла используя класс DataSaverAndReader
            allCompetitionsFromFile = DataSaverAndReader.readDataFromJson(CompetitionListWrapper.class, pathToSave);
        } catch (FileNotFoundException e) { // если не получилось кидаем ошибку
            throw new RuntimeException(e);
        }

        if(allCompetitionsFromFile != null) // если соревнования все-таки существовали в файле, то
            // устанавливаем все соревнования из обертки в список всех соревнований текущего объекта
            this.allCompetitions = allCompetitionsFromFile.getCompetitions();
        else
            // если нет - то создаем пустой лист соревнований
            allCompetitions = new ArrayList<>();
    }

    /**
     * Метод для добавления соревнования к общему списку всех соревнований
     *
     * @param c соревнование для добавления
     */
    public void addCompetition(Competition c){

        if(allCompetitions == null) // если список не существует
            allCompetitions = new ArrayList<>(); // инициализируем новый

        // если список не содержит идентичного соревнования, то
        if(!allCompetitions.contains(c))
            allCompetitions.add(c); // добавляем соревнование в список
        else{
            // если содержит, то делаем замену такого же соревнования, чтобы не было дубликатов
            for(int i = 0; i < allCompetitions.size(); i++){
                if(allCompetitions.get(i).equals(c))
                    allCompetitions.set(i, c);
            }
        }

    }

    /**
     * Getter для списка всех соревнований
     *
     * @return список соревнований
     */
    public List<Competition> getCompetitions(){
        return allCompetitions;
    }

    /**
     * Метод для сбора и возврата информации об объекте в формате строки
     *
     * @return строку с необходимой информацией
     */
    @Override
    public String toString() {
        return "CompetitionListWrapper{" +
                "allCompetitions=" + allCompetitions +
                '}';
    }
}