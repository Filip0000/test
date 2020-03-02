package nl.tudelft.oopp.demo.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.ServerCommunication;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuidingPageController {


    /**
     * displayBuildingss displays a nicely structured list of buildings in BuildingListViewController
     * @param event when clicked on buildings
     * @throws IOException if the resource is not available
     */
    public void displayBuildingList(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BuildingList.fxml"));
        Parent buildingPageParent = (Parent) loader.load();
        Scene buildingPageScene = new Scene(buildingPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    /**
     * Logout button returns back to loginscreen
     * @param event when clicked on logout
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
     * Go to reservation page
     * @param event
     * @throws IOException
     */
    public void makeReservation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reservation.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene=new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

}
