package com.csia_galeta.people;

public class Judge {

    private String judgeName;
    private String judgeSurname;

    public  void setName (String name){ // Setting the name
        this.judgeName = name;
    }

    public String getName (){ // Getting the name
        return judgeName;
    }

    public  void setSurname (String surname){ // Setting the surname
        this.judgeSurname = surname;
    }

    public String getSurname (){ // Getting the surname
        return judgeSurname;
    }

    @Override
    public String toString() {
        return "Judges{" +
                "judgeName='" + judgeName + '\'' +
                ", judgeSurname='" + judgeSurname + '\'' +
                '}';
    }
}
