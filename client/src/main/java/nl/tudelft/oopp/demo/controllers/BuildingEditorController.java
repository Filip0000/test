package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.communication.ServerPosting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class BuildingEditorController {

    private int buildingID;

    @FXML
    Text building_name;
    @FXML
    Text name_prev;
    @FXML
    Text capacity_prev;
    @FXML
    Text address_prev;


    /**
     * Returns back to loginpage
     *
     * @param event
     * @throws IOException
     */
    public void backToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    @FXML
    private TextField name_new;
    @FXML
    private TextField address_new;
    @FXML
    private TextField capacity_new;

    /**
     * Converts updated values into an JSONObject, with buildingID, buildingName,buildingAddress en numberOfBikes.
     *
     * @throws Exception
     */
    public void updatedBuildingJSON(javafx.event.ActionEvent event) throws Exception {
        JSONObject objNew = new JSONObject();
        String buildingName = name_new.getText();
        String buildingAddress = address_new.getText();
        String numberOfBikes = capacity_new.getText();
        if (name_new.getText().isEmpty()) {
            String[] prev_name_temp = name_prev.getText().split(":");
            buildingName = prev_name_temp[1];
        }
        if (address_new.getText().isEmpty()) {
            String[] prev_address_temp = address_prev.getText().split(":");
            buildingAddress = prev_address_temp[1];
        }
        if (capacity_new.getText().isEmpty()) {
            String[] prev_capacity_temp = capacity_prev.getText().split(":");
            numberOfBikes = prev_capacity_temp[1];
        }
        objNew.put("buildingID", buildingID);
        objNew.put("buildingName", buildingName);
        objNew.put("buildingAddress", buildingAddress);
        objNew.put("numberOfBikes", parseInt(numberOfBikes)); // add integer exception handling
        ServerPosting.editBuilding(objNew);
        ServerCommunication.succesfulEdit(event);
    }

    /**
     * Display current values of attributes of the selected building in the view.
     *
     * @param selectedID
     * @throws JSONException
     */
    public void initializeScene(int selectedID) throws JSONException {
        String building = ServerCommunication.getBuildingInfo(selectedID);
        JSONObject obj = new JSONObject(building);
        String name = obj.getString("buildingName");
        this.buildingID = selectedID;

        building_name.setText("Building Name: " + name);
        name_prev.setText(" Previous: " + name);
        address_prev.setText("Previous: " + obj.getString("buildingAddress"));
        capacity_prev.setText(" Previous: " + String.valueOf(obj.getInt("buildingBikes")));
    }

    /**
     * Converts new building values into an JSONObject, with buildingName,buildingAddress en numberOfBikes.
     *
     * @throws Exception
     */

    public void newBuildingJSON(javafx.event.ActionEvent event) throws Exception {
        if (name_new.getText().isEmpty() || address_new.getText().isEmpty() || capacity_new.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all forms correctly");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }
        JSONObject objNew = new JSONObject();
        objNew.put("name", name_new.getText());
        objNew.put("address", address_new.getText());
        objNew.put("numberOfBikes", parseInt(capacity_new.getText())); // add integer exception handling
        ServerPosting.addBuilding(objNew);
        ServerCommunication.succesfulEdit(event);
    }

    public void deleteBuilding(javafx.event.ActionEvent event) throws Exception {
        ServerPosting.deleteBuilding(buildingID);
        ServerCommunication.succesfulEdit(event);
    }
}
