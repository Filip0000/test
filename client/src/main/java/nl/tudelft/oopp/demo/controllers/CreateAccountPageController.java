package nl.tudelft.oopp.demo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.communication.ServerPosting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CreateAccountPageController {

    private String accountType = "student";

    @FXML
    private TextField accountId;
    @FXML
    private PasswordField passwordF1;
    @FXML
    private PasswordField passwordF2;


    ObservableList<String> accountTypeList = FXCollections.observableArrayList("student","employee");

    @FXML
    private ChoiceBox accountTypeBox;

    /**
     * initializes the choice box with the student item set as default value
     */
    @FXML
    private void initialize() throws Exception {
        accountTypeBox.setValue("student");
        accountTypeBox.setItems(accountTypeList);
        getAccountType();
    }

    /**
     * Reads the value of the accountTypeBox.
     * @throws Exception
     */
    public void getAccountType() throws Exception {
        accountTypeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                accountType = newValue.toString();
            }
        });
    }

    /**
     * Sends user back to login.
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

    /**
     * Handles the creating an account
     * @param event
     * @throws IOException
     */
    public void createAccount(ActionEvent event) throws IOException, JSONException {
        if (accountId.getText().isEmpty() || passwordF1.getText().isEmpty() || passwordF2.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all forms correctly");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }

        JSONObject user = new JSONObject();
        String Email = accountId.getText() + "@" + accountType + ".tudelft.nl";

        if (accountType.contains("stu")) {
            user.put("accountLevel", 2);
        }
        else user.put("accountLevel", 1);
        user.put("email", Email);
        user.put("netId", accountId.getText());
        if (passwordF1.getText().equals(passwordF2.getText())) {
            user.put("password", MainSceneController.getMd5(passwordF1.getText()));
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Please repeat your password correctly.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }
        System.out.println(user.toString());
        ServerPosting.addUser(user);
        ServerCommunication.succesfullCreation(event);
    }
}
