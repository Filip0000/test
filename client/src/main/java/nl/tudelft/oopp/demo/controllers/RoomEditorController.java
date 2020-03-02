package nl.tudelft.oopp.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.communication.ServerPosting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RoomEditorController {
    @FXML
    private Text roomName;
    @FXML
    private Text name_prev;
    @FXML
    private Text capacity_prev;
    @FXML
    private Text type_prev;

    @FXML
    private TextField nameInput;
    @FXML
    private TextField capacityInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private TextField typeInput;

    private int roomID;

    public void initializeScene(int id) throws JSONException {
        String room = ServerCommunication.getRoomInfo(id);
        JSONObject obj = new JSONObject(room);
        String name = obj.getString("name");
        System.out.println(name);
        this.roomID = id;

        roomName.setText("Room Name: "+ name);
        name_prev.setText("\t Previous:" + name);
        capacity_prev.setText("\t Previous:" + String.valueOf(obj.getInt("capacity")));
        type_prev.setText("\t Previous:" + String.valueOf(obj.getInt("type")));
    }

    public int getRoomID(){
        return roomID;
    }

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

    /**
     * Converts updated values into an JSONObject, with roomName, roomCapacity, roomDescription and roomType.
     * @throws Exception
     */
    public void updatedRoomJSON(javafx.event.ActionEvent event) throws Exception {
        JSONObject objNew = new JSONObject();
        String[] prev_name_temp = name_prev.getText().split(":");
        String prev_name = prev_name_temp[1];
        String[] prev_type_temp = type_prev.getText().split(":");
        String prev_type = prev_type_temp[1];
        String[] prev_capacity_temp = capacity_prev.getText().split(":");
        String prev_capacity = prev_capacity_temp[1];

        objNew.put("roomID", ServerCommunication.getRoomId(prev_name));

        if(nameInput.getText().length() > 0)
            objNew.put("roomName", nameInput.getText());
        else
            objNew.put("roomName", prev_name);

        if(capacityInput.getText().length() > 0)
            objNew.put("roomCapacity", Integer.parseInt(capacityInput.getText()));
        else
            objNew.put("roomCapacity", Integer.parseInt(prev_capacity));

        objNew.put("roomDescription", descriptionInput.getText());          //add prev_description to the controller

        if(typeInput.getText().length() > 0)
            objNew.put("roomType",Integer.parseInt(typeInput.getText()));
        else
            objNew.put("roomType",Integer.parseInt(prev_type));

        objNew.put("buildingID", ServerCommunication.getBuildingIdFromRoomID(ServerCommunication.getRoomId(prev_name)));

        ServerPosting.editRoom(objNew);
        ServerCommunication.succesfulEdit(event);
    }

    /**
     * Converts updated values into an JSONObject, with roomName, roomCapacity, roomDescription and roomType.
     * @throws Exception
     */
    public void deleteRoom(javafx.event.ActionEvent event) throws Exception {
        String[] prev_name_temp = name_prev.getText().split(":");
        String prev_name = prev_name_temp[1];
        ServerPosting.deleteRoom(ServerCommunication.getRoomId(prev_name));
        ServerCommunication.succesfulEdit(event);
    }
}
