package nl.tudelft.oopp.demo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerPosting;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.SortedSet;

import static nl.tudelft.oopp.demo.communication.ServerCommunication.getBuildingIdFromRoomID;

public class ReservationController {

    private static int buildingId;
    private static int roomId;
    private static int userID = -1;
    private static int accountLevel = -1;

    public JSONObject reservation;
    private String timeslot;
    private String date;


    /**
     * Returns back to loginpage.
     * @param event event
     * @throws IOException if link does not exist
     */
    public void backToView(ActionEvent event) throws IOException {
        FXMLLoader loader;
        if (accountLevel == 1) {
            loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        }
        else {
            loader = new FXMLLoader(getClass().getResource("/BuildingList.fxml"));
        }
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene=new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }



    public static void setRoomId(int id) {
        roomId = id;
    }
    public static void setUserID(int id) {
        userID = id;
    }
    public static int getUserID() {
        return userID;
    }
    public static void setAccountLevel(int level) {
        accountLevel = level;
    }
    public static int getAccountLevel() {
        return accountLevel;
    }

    ObservableList<String> accountTypeList = FXCollections
            .observableArrayList("6-9", "9-12","12-15", "15-18");


    @FXML
    private ChoiceBox timeslotBox;

    @FXML
    private DatePicker datePicker;

    /**
     * initializes the choice box with the default timeslot
     */
    @FXML
    private void initialize() throws Exception {
        buildingId = getBuildingIdFromRoomID(roomId);
        timeslot = "6-9";
        timeslotBox.setValue("6-9");
        timeslotBox.setItems(accountTypeList);
        getTimeslot();
        getDate();
        date = Date.from(Instant.now()).toString();
    }

    public void getDate() {
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate dater = datePicker.getValue();
                if (dater != null) {
                    date = java.sql.Date.valueOf(dater.toString()).toString();
                    System.err.println("Selected date: " + date);
                }
            }
        });
    }

    /**
     * Returns the timeslot chosen from menu
     * @throws Exception if timeslotBox is null?
     */
    public void getTimeslot() throws Exception {
        timeslotBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (timeslot != null) {
                timeslot = newValue.toString();
            }
        });
    }

    /**
     * Submitting a form
     * handles the submit button
     * @param event on pressing submit
     * @return a jsonObject
     * @throws Exception if a room name or building name is not correct
     */
    public void submit(ActionEvent event) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userID);
        jsonObject.put("room_id", roomId);
        jsonObject.put("building_id", buildingId);
        jsonObject.put("time_from", timeslot.split("-")[0]);
        jsonObject.put("time_to", timeslot.split("-")[1]);
        jsonObject.put("date", date);
        ServerPosting.addReservation(jsonObject);

        backToView(event);
        reservation = jsonObject;
        System.out.println(jsonObject.toString());
    }
}
