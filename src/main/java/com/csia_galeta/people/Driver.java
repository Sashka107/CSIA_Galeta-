package com.csia_galeta.people;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {

    private String driverName;
    private String driverSurname;
    private short number;
    private String team;

    public  void setNameD (String name){ // Setting the name
        Pattern pattern = Pattern.compile("^.{1,55}$");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()==true){
            this.driverName = name;
        } else {
            System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        }
    }
    public String getNameD (){ // Getting the name
        return driverName;
    }

    public  void setSurnameD (String surname){ // Setting the surname
        Pattern pattern = Pattern.compile("^.{1,55}$");
        Matcher matcher = pattern.matcher(surname);
        if (matcher.find()==true){
            this.driverSurname = surname;
        } else {
            System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        }
    }
    public String getSurnameD (){ // Getting the surname
        return driverSurname;
    }

    public  void setNumber (short number){ // Setting the number of the competitor
        Pattern pattern = Pattern.compile("^0*(?:32[0-6][0-7][0-5]|[0-2]?[0-9]{1,4})$");
        Matcher matcher = pattern.matcher(number+"");
        if (matcher.find()==true){
            this.number = number;
        }else{
            System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        }
    }

    public short getNumber (){ // Getting the number of the competitor
        return number;
    }

    public  void setTeam (String team){ // Setting the driver's team name
        Pattern pattern = Pattern.compile("^.{1,55}$");
        Matcher matcher = pattern.matcher(team);
        if (matcher.find()==true){
            this.team = team;
        } else {
            System.out.println("Please check whether entered data is a is a valid team name and less or equal to 55 characters.");
        }
    }
    public String getTeam (){ // Getting the driver's team name
        return team;
    }

    @Override
    public String toString() {
        return "Drivers{" +
                "driverName='" + driverName + '\'' +
                ", driverSurname='" + driverSurname + '\'' +
                ", number=" + number +
                ", team='" + team + '\'' +
                '}';
    }
}
