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
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BuildingEditController {

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
    private ListView<String> buildinglistviewer;

    /**
     * Creates an infinite scrollable List with buildings
     */
    public void setListView() throws Exception {
        buildinglistviewer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Thread for GUI specific that makes updating of the list better
            if (newValue != null) {
                Platform.runLater(() -> {
                    int selectedID = parseInt(newValue.split("ID: ")[1]);
                    try {
                        loadBuildingEditor(selectedID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
    @FXML
    private Parent logout;

    public void loadBuildingEditor(int selectedID) throws IOException, JSONException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BuildingEditor.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        BuildingEditorController editorController = loader.getController();
        editorController.initializeScene(selectedID);

        Stage appStage = (Stage) logout.getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();

    }


    /**
     * Initialises the listView
     */
    @FXML
    void initialize() throws Exception {
        ObservableList<String> items = FXCollections.observableArrayList(getAllBuildings());
        buildinglistviewer.setItems(items);
        setListView();

    }

    /**
     *
     * @return
     * @throws Exception
     */
    public ArrayList<String> getAllBuildings() throws Exception {
        ArrayList<String> resultlist = new ArrayList<>();

        String buildingList = ServerCommunication.getBuildingList();
        JSONArray jsonArray = new JSONArray(buildingList);

        for(int j=0; j<jsonArray.length(); j++) {
            resultlist.add(formatBuildingAdmin(jsonArray.getJSONObject(j)));
        }

        return resultlist;
    }

    /**
     *
     * @param jsonObject
     * @return
     * @throws JSONException
     */
    public static String formatBuildingAdmin(JSONObject jsonObject) throws JSONException {
        StringBuilder result = new StringBuilder();
        String buildingName = jsonObject.getString("buildingName");
        String buildingAddress = jsonObject.getString("buildingAddress");
        int buildingBikes = jsonObject.getInt("buildingBikes");
        int buildingID = jsonObject.getInt("id");

        result.append("Name of Building: ");
        result.append(buildingName);
        result.append("\nAddress of building: ");
        result.append(buildingAddress);
        result.append("\nBikes in the building: ");
        result.append(buildingBikes);
        result.append("\nID: ");
        result.append(buildingID);
        return result.toString();
    }
}
