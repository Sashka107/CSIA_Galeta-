package com.csia_galeta.people;

public class Driver {

    private String driverName;
    private String driverSurname;
    private byte number;
    private String team;

    public  void setNameD (String name){ // Setting the name
        this.driverName = name;
    }

    public String getNameD (){ // Getting the name
        return driverName;
    }

    public  void setSurnameD (String surname){ // Setting the surname
        this.driverSurname = surname;
    }

    public String getSurnameD (){ // Getting the surname
        return driverSurname;
    }

    public  void setNumber (byte number){ // Setting the number of the competitor
        this.number = number;
    }

    public byte getNumber (){ // Getting the number of the competitor
        return number;
    }

    public  void setTeam (String team){ // Setting the driver's team name
        this.team = team;
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
