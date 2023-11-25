package com.csia_galeta.people;

public class Judge {

    private String judgeName;
    private String judgeSurname;

    public boolean setName (String name){ // Setting the name
        if (name.matches("^.{1,55}$")) {
            this.judgeName = name;
            return true;
        }
        System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        return false;
    }

    public String getName (){ // Getting the name
        return judgeName;
    }

    public boolean setSurname (String surname){ // Setting the surname
        if (surname.matches("^.{1,55}$")) {
            this.judgeSurname = surname;
            return true;
        }
        System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        return false;
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
