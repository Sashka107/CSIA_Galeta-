package com.csia_galeta.people;

/*
 Class Judge
 This class describes the behavior and contents of a judge for the proper
 and full functionality in this program.
 */
public class Judge {

    private String judgeName; // Variable containing name.
    private String judgeSurname; // Variable containing surname.

    /*
     Setter method for setting the name.

     @param name - the name to be set.
     @return true - if the name successfully passed validation and set, false - otherwise.
     */
    public boolean setName (String name){

        // If the name meets the requirements of 1 to 55 characters.
        if (name.matches("^.{1,55}$")) {
            this.judgeName = name;
            return true;
        }

        // If does not.
        System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        return false;
    }

    /*
     Getter for judge`s name

     @return name of the judge
     */
    public String getName (){
        return judgeName;
    }

    /*
     Setter method for setting the surname.

     @param surname - the surname to be set.
     @return true - if the surname successfully passed validation and set, false - otherwise.
     */
    public boolean setSurname (String surname){

        // If the surname meets the requirements of 1 to 55 characters.
        if (surname.matches("^.{1,55}$")) {
            this.judgeSurname = surname;
            return true;
        }

        // If does not.
        System.out.println("Please check whether entered data is a is a valid surname and less or equal to 55 characters.");
        return false;
    }

    /*
     Getter for judge`s lastname

     @return lastname of the judge
     */
    public String getSurname (){
        return judgeSurname;
    }

    /*
     Method gathers information about the judge and returns it as a string.

     @return all necessary information about the judge as a string.
     */
    @Override
    public String toString() {
        //We get the first letter of the name and capitalise it.
        char firstLetterName = Character.toUpperCase(judgeName.charAt(0));

        // Returning in the format: "Name L"
        return judgeName.replace(judgeName.charAt(0), firstLetterName) + " " + Character.toUpperCase(judgeSurname.charAt(0));
    }
}
