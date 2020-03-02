package nl.tudelft.oopp.demo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainSceneController {

    /**
     * Redirects to the page showing buttons to buildings and reservation history
     * @param event
     * @throws IOException
     */
    public void displayBuildings(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BuildingList.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();

    }

    public void createAccount(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateAccountPage.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    public void goToAdmin(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;

    /**
     * Hashes password into an encoded MD5 String
     * @param input
     * @return
     */
    public static String getMd5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks with the userRepository if a user has valid login credentials
     * @param event
     * @throws IOException
     */
    public void checkIfUserIsCorrect(javafx.event.ActionEvent event) throws IOException, JSONException {

        StringBuilder result = new StringBuilder();
        String userList = ServerCommunication.getUserList();

        JSONArray jsonArray = new JSONArray(userList);
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String user_id = jsonObject.getString("netId");
            ReservationController.setUserID(jsonObject.getInt("id"));
            //ServerCommunication.addBuilding();
            //ServerCommunication.addRoom();

            if (user_id.equals(userField.getText())) {
                String password = jsonObject.getString("password");
                if (password.equals(getMd5(passwordField.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Welcome " + user_id);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                    int accountlevel = jsonObject.getInt("level");
                    ReservationController.setAccountLevel(accountlevel);
                    if (accountlevel == 1) {
                        goToAdmin(event);
                    } else displayBuildings(event);
                    break;


                }
            }

            if (i == jsonArray.length() - 1) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Login failed. Try again!");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
        }
    }
}

