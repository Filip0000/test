package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.communication.ServerPosting;
import org.json.JSONObject;

import java.io.IOException;

public class RoomCreatorController {
    @FXML
    private TextField newName;
    @FXML
    private TextField newBuilding;
    @FXML
    private TextArea newDescription;
    @FXML
    private TextField newCapacity;
    @FXML
    private TextField newType;

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

    public void addRoom(ActionEvent event) throws Exception {
        JSONObject objNew = new JSONObject();
        if (newName.getText().isEmpty() || newDescription.getText().isEmpty() || newCapacity.getText().isEmpty() ||
        newType.getText().isEmpty() || newBuilding.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all forms correctly");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }
        objNew.put("roomName", newName.getText());
        objNew.put("roomDescription", newDescription.getText());
        objNew.put("roomCapacity", newCapacity.getText());
        objNew.put("roomType", newType.getText());
        objNew.put("roomBuildingID", ServerCommunication.getBuildingId(newBuilding.getText()));
        ServerPosting.addRoom(objNew);
        ServerCommunication.succesfulEdit(event);
    }
}