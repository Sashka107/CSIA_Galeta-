package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.Qualification;
import com.csia_galeta.QualificationController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

/*
 Class CreateEditCompetitionController
 Responsible for the behavior of the window for creating a new competition or editing an existing one in the PLANNED status.
 Depending on where and from where this controller was called, editing will be continued or a new one will be created.
 */

public class CreateEditCompetitionController {
    public DatePicker datePicked; // Variable - reference to the date selection window.
    public Label judgesLabel; // Variable - reference to the field for displaying the text of judges' names.
    @FXML
    private TextField competitionName; // Variable - reference to the field for entering the competition name.
    @FXML
    private TextField countOfCF; // Variable - reference to the field for entering the number of qualification rounds.
    @FXML
    private Button addJudges; // Variable - reference to the button for adding judges.
    @FXML
    private Button addDrivers; // Variable - reference to the button for adding participants.
    @FXML
    private Button startQualification; // Variable - reference to the button for starting the qualification.
    @FXML
    private Label mainText; // Variable - reference to the field for displaying the window title.
    @FXML
    private ListView<Driver> listView; // Variable - reference to the list for displaying participants.

    /*
     Method triggered when the "Add Driver" button is clicked.
     Opens the window for adding a driver and closes the current one.
     */
    @FXML
    protected void openAddDrivers(){
        saveCompetition(true);
        SceneOpener.openSceneAndReturnController("Drivers.fxml",
                "Add New Driver",
                addDrivers.getScene().getWindow());
    }

    /*
     Method for performing checks on the correctness of the entered competition data,
     when necessary.

     @param notCheckWarnings - variable that indicates whether to skip some checks
     @return true if all entered data is correct, false otherwise
     */
    protected boolean saveCompetition(boolean notCheckWarnings){
        boolean hasWarning = false; // Was there at least one error.

        if (checkCountOfQ(countOfCF.getText())){ // Check the number of rounds.
            // Try to add the number of rounds to the current competition.
            CompetitionSingleton.addCountOfRounds(Integer.parseInt(countOfCF.getText()));
        } else {
            hasWarning = true; // Yes, there was an error.
            // Show an error.
            showWarning("Please check whether entered data is a number from 1 to 5.", notCheckWarnings);
        }

        // Similarly as above.
        if (checkCompetitionName(competitionName.getText())){
            CompetitionSingleton.addToTmpCompetitionName(competitionName.getText());
        } else {
            hasWarning = true;
            showWarning("Please check whether entered data is a is a valid name and less or equal to 55 characters.", notCheckWarnings);
        }

        // Similarly as above.
        if (checkDate(datePicked.getValue())){
            CompetitionSingleton.getTmpCompetition().setCompetitionDate(datePicked.getValue());
        } else {
            hasWarning = true;
            showWarning("Date is incorrect", notCheckWarnings);
        }

        // If no errors at all.
        if (!hasWarning){
            System.out.println("Saving Competition...");
            return true;
        }

        return false; // If there is at least one error.
    }

    /*
     Method triggered when the "Start Competition" button is clicked.
     Starts the qualification if there are no errors in the competition.
     */
    @FXML
    protected void startCompetition(){
        // If there were errors when filling in the competition fields.
        if(!saveCompetition(false))
            return; // Stop the competition launch.

        // If less than 4 drivers.
        if(CompetitionSingleton.getTmpCompetition().getListOfDrivers().size() <= 3){
            showWarning("Can`t save competition with 1 player. Minimum Players: 4", false);
            return; // Stop the competition launch.
        }

        // Perform necessary saving, change status, open qualification window.
        CompetitionSingleton.getTmpCompetition().setCompetitionStateQualificationInProgress();
        CompetitionSingleton.addQualificationToTmpCompetition(new Qualification(CompetitionSingleton.getTmpCompetition().getListOfDrivers()));
        CompetitionSingleton.saveTmpCompetition();
        QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                "Qualification",
                startQualification.getScene().getWindow());
        controller.loadWindow();
    }

    /*
     Method saves the competition when the "Save Competition" button is clicked.
     Will save only if everything was previously filled in correctly.
     */
    @FXML
    private void saveCompetitionOnClick(){
        // If there were errors when filling in the competition fields.
        if(!saveCompetition(false))
            return; // Stop the competition launch.

        // If less than 4 drivers.
        if(CompetitionSingleton.getTmpCompetition().getListOfDrivers().size() <= 3){
            showWarning("Can`t save competition with 1 player. Minimum Players: 4", false);
            return; // Stop the competition launch.
        }

        // Perform necessary saving, change status, open the main window.
        CompetitionSingleton.getTmpCompetition().setCompetitionStatePlanned();
        CompetitionSingleton.saveTmpCompetition();
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml",
                "Main View",
                startQualification.getScene().getWindow());
    }

    /*
     Method for displaying a warning if necessary.

     @param notShowWarn - when a warning should not be displayed
     @param message - the message to display
     */
    private void showWarning(String message, boolean notShowWarn){
        if(notShowWarn)
            return;

        Alert warningsAlert = new Alert(Alert.AlertType.WARNING);
        warningsAlert.setHeaderText("Incorrect data");
        warningsAlert.setContentText(message);
        warningsAlert.show();
    }

    /*
     Method for loading data of the current competition being created into the editing window.
     Used when opening the window after adding judges or drivers.
     */
    public void loadTmpCompetition(){
        competitionName.setText(CompetitionSingleton.getTmpCompetition().getCompetitionName());
        countOfCF.setText(CompetitionSingleton.getTmpCompetition().getAmountOfQualifyingRounds() + "");
        listView.getItems().addAll(CompetitionSingleton.getTmpCompetition().getListOfDrivers());
        datePicked.setValue(CompetitionSingleton.getTmpCompetition().getCompetitionDateLocalDate());
        judgesLabel.setText(CompetitionSingleton.getTmpCompetition().getListOfJudgesString());
    }

    /*
     Method works if the window needs to be opened in "edit competition" mode,
     not to create a new one.
     */
    public void loadToEditCompetition(){
        CompetitionSingleton.setTmpCompetition(CompetitionSingleton.getCurrentCompetition());
        loadTmpCompetition();
        mainText.setText("Edit Competition");
        competitionName.setEditable(false);
        datePicked.setValue(CompetitionSingleton.getTmpCompetition().getCompetitionDateLocalDate());
        judgesLabel.setText(CompetitionSingleton.getTmpCompetition().getListOfJudgesString());
    }

    /*
     Method for checking the correctness of the selected date.

     @param date - the date to check
     @return true if the date is selected, false otherwise
     */
    private boolean checkDate(LocalDate date){
        return date != null;
    }

    /*
     Method for checking the correctness of the number of qualification rounds.

     @param amountToBeChecked - the entered number to be checked
     @return true if the amount is correct, false otherwise
     */
    private boolean checkCountOfQ (String amountToBeChecked){
        // If it contains not only numbers or is empty.
        if(amountToBeChecked == null || !amountToBeChecked.matches("[0-9]+"))
            return false;// Check not passed.

        // Perform transformations.
        int correctAmountOFQ = Integer.parseInt(amountToBeChecked);

        // If the number is in the range from 1 to 5 - it passed the check.
        return correctAmountOFQ > 0 && correctAmountOFQ <= 5;
    }

    /*
     Method for checking the correctness of the competition name.

     @param nameToBeChecked - the entered name to be checked
     @return true if the name is correct, false otherwise
     */
    private boolean checkCompetitionName (String nameToBeChecked){
        if(nameToBeChecked == null)
            return false;

        // If the name contains from 1 to 55 characters, it is correct.
        return nameToBeChecked.matches("^.{1,55}$");
    }

    /*
     Method triggered when the "Add Judge" button is clicked.
     Opens the window for adding a judge and closes the current one.
     */
    @FXML
    public void addJudges(){
        saveCompetition(true);
        SceneOpener.openSceneAndReturnController("AddJudgeFrame.fxml",
                "Add New Judge",
                addJudges.getScene().getWindow());
    }
}