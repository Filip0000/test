package nl.tudelft.oopp.demo.controllers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class BuildingListViewController {

    @FXML
    private ListView<String> listviewer;


    /**
     * Creates an infinite scrollable List with buildings
     */
    public void setListView() throws Exception {
        listviewer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Thread for GUI specific that makes updating of the list better
            if (newValue != null && newValue.split(":")[0].contains("building")) {
                Platform.runLater(() -> {
                    listviewer.getItems().clear();
                    try {
                        String buildingName = newValue.
                                split("Address")[0].
                                split(": ")[1].
                                split("\n")[0];
                        ObservableList<String> items = FXCollections.observableArrayList(getRoomInfo(buildingName));
                        listviewer.setItems(items);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            if (newValue != null && newValue.split(": ")[0].contains("room")) {
                //get the roomname and send it somewhere for the reservation

                //send buildingid+ roomID to ReservationController
                //ReservationController.send(buildingId, roomID);
                if (ReservationController.getAccountLevel() >= 0) {
                    String roomName = newValue.
                            split("Address")[0].
                            split(": ")[1].
                            split("\n")[0];
                    try {
                        ReservationController.setRoomId(ServerCommunication.getRoomId(roomName));
                        loadReservation();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        });
    }

    @FXML
    private Parent logout;

    public void loadReservation() throws IOException, JSONException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reservation.fxml"));
        Parent reservationParent = (Parent) loader.load();

        Scene reservationScene = new Scene(reservationParent);

        Stage appStage = (Stage) logout.getScene().getWindow();
        appStage.setScene(reservationScene);
        appStage.show();

    }

    /**
     * Initialises the listview
     */
    @FXML
    void initialize() throws Exception {
        ObservableList<String> items = FXCollections.observableArrayList(getBuildingInfo());
        listviewer.setItems(items);
        setListView();
    }

    /**
     * Logout function to go back to login screen
     * @param event upon pressing logout
     * @throws IOException if the resource is not available
     */
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene=new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    /**
     * Go back to buildingList after searching rooms
     * @param event
     * @throws IOException
     */
    public void goBuildinglist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BuildingList.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene=new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    /**
     * Simple Converter from buildinglist to humanly readable String.
     * @return A nicely structured String.
     */
    public ArrayList<String> getBuildingInfo() throws JSONException {
        ArrayList<String> resultlist = new ArrayList<>();
        String buildingList = ServerCommunication.getBuildingList();

        JSONArray jsonArray = new JSONArray(buildingList);
        for(int i=0; i<jsonArray.length(); i++) {
            resultlist.add(ServerCommunication.formatBuilding(jsonArray.getJSONObject(i)));
        }
        return resultlist;
    }

    /**
     * toString for a room
     * @param buildingName name of building to get information from
     * @return
     * @throws Exception if building does not exist
     */
    public ArrayList<String> getRoomInfo(String buildingName) throws Exception {
        ArrayList<String> resultlist = new ArrayList<>();
        int buildingId = ServerCommunication.getBuildingId(buildingName);

        String roomList = ServerCommunication.getRoomList(buildingId);
        JSONArray jsonArray = new JSONArray(roomList);

        for(int j=0; j<jsonArray.length(); j++) {
                resultlist.add(ServerCommunication.formatRoom(jsonArray.getJSONObject(j)));
        }
        return resultlist;
    }

}
