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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class RoomEditController {
    /**
     * Returns back to loginpage
     * @param event
     * @throws IOException
     */
    public void backToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene=new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene=new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }


    @FXML
    private ListView<String> roomlistviewer;

    /**
     * Creates an infinite scrollable List with rooms
     */
    public void setListView() throws Exception {
        roomlistviewer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Thread for GUI specific that makes updating of the list better
            if (newValue != null) {
                Platform.runLater(() -> {
                    for(String s: newValue.split("ID: ")) {
                        System.out.println(s);
                    }
                    int selectedID = parseInt(newValue.split("ID: ")[1]);
                    System.out.println(selectedID);
                    try {
                        loadRoomEditor(selectedID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
    @FXML
    private Parent logout;

    @FXML

    public void loadRoomEditor(int roomID) throws IOException, JSONException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomEditor.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);
        RoomEditorController editorController = loader.getController();
        editorController.initializeScene(roomID);
        Stage appStage = (Stage) logout.getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();

    }


    /**
     * Initialises the listView
     */
    @FXML
    void initialize() throws Exception {
        ObservableList<String> items = FXCollections.observableArrayList(getAllRooms());
        roomlistviewer.setItems(items);
        setListView();
    }


    public ArrayList<String> getAllRooms() throws Exception {
        ArrayList<String> resultlist = new ArrayList<>();

        String roomList = ServerCommunication.getRoomList();
        JSONArray jsonArray = new JSONArray(roomList);

        for(int j=0; j<jsonArray.length(); j++) {
            resultlist.add(formatRoomAdmin(jsonArray.getJSONObject(j)));
        }

        return resultlist;
    }


    public static String formatRoomAdmin(JSONObject jsonObject) throws JSONException {
        StringBuilder result = new StringBuilder();
        String roomName = jsonObject.getString("name");
        String roomDescription = jsonObject.getString("description");
        int roomCapacity = jsonObject.getInt("capacity");
        int roomID = jsonObject.getInt("id");

        result.append("Name of room: ");
        result.append(roomName);
        result.append("\nDescription of room: ");
        result.append(roomDescription);
        result.append("\nTotal capacity of room: ");
        result.append(roomCapacity);
        result.append("\nID: ");
        result.append(roomID);
        return result.toString();
    }
}
