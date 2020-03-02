package nl.tudelft.oopp.demo.communication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    public static void succesfulEdit(javafx.event.ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("The changes are succesful");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(ServerCommunication.class.getResource("/AdminPage.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    public static void succesfullCreation(javafx.event.ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Your account was created succesfully!");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(ServerCommunication.class.getResource("/mainScene.fxml"));
        Parent buildingPageParent = (Parent) loader.load();

        Scene buildingPageScene = new Scene(buildingPageParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(buildingPageScene);
        appStage.show();
    }

    /**
     * Retrieves a the list of buildings from the server.
     * @return the body of a get request to the server.
     */
    public static String getBuildingList() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/building")).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Retrieves a the list of users from the server.
     * @return the body of a get request to the server.
     */
    public static String getReservationList() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/reservations")).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Retrieves a the list of rooms from the server.
     * @return the body of a get request to the server.
     */
    public static String getRoomList(int buildingId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/building/" +buildingId + "/rooms")).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Retrieves a the list of rooms from the server.
     * @return the body of a get request to the server.
     */
    public static String getRoomInfo(int roomId) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/building/rooms/" + roomId)).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    public static String getBuildingInfo(int buildingID) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/building/" + buildingID)).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Retrieves a the list of rooms from the server.
     * @return the body of a get request to the server.
     */
    public static String getRoomList() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/building/rooms")).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }
    /**
     * Retrieves a the list of users from the server.
     * @return the body of a get request to the server.
     */
    public static String getUserList() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/loginpage")).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    public static String formatBuilding(JSONObject jsonObject) throws JSONException {
        StringBuilder result = new StringBuilder();
        int buildingBikes = jsonObject.getInt("buildingBikes");
        String buildingAddress = jsonObject.getString("buildingAddress");
        String buildingName = jsonObject.getString("buildingName");

        result.append("Name of building: ");
        result.append(buildingName);
        result.append("\nAddress of building: ");
        result.append(buildingAddress);
        result.append("\nAmount of bikes stored in building: ");
        result.append(buildingBikes);
        result.append("\n");
        return result.toString();
    }

    public static String formatRoom(JSONObject jsonObject) throws JSONException {
        StringBuilder result = new StringBuilder();
        String roomName = jsonObject.getString("name");
        String roomDescription = jsonObject.getString("description");
        int roomCapacity = jsonObject.getInt("capacity");

        result.append("Name of room: ");
        result.append(roomName);
        result.append("\nDescription of room: ");
        result.append(roomDescription);
        result.append("\nTotal capacity of room: ");
        result.append(roomCapacity);
        result.append("\n");
        return result.toString();
    }

    public static String formatReservation(JSONObject jsonObject) throws JSONException {
        StringBuilder result = new StringBuilder();
        int userID = jsonObject.getInt("userId");
        int roomID = jsonObject.getInt("roomId");
        int buildingID = jsonObject.getInt("buildingId");
        int timeFrom = jsonObject.getInt("timeFrom");
        int timeTo = jsonObject.getInt("timeTo");
        String date = jsonObject.getString("datum");
        result.append("ID of the user: ");
        result.append(userID);
        result.append("\nName of the room ");
        result.append(roomID);
        result.append("\nName of the building: ");
        result.append(buildingID);
        result.append("\nStart time: ");
        result.append(timeFrom);
        result.append("\nEnd time: ");
        result.append(timeTo);
        result.append("\nDate: ");
        result.append(date);
        return result.toString();
    }

    /**
     * Returning an Id of a building given a specific buildingName.
     * @param buildingName
     * @return
     * @throws Exception
     */
    public static int getBuildingId(String buildingName) throws Exception {
        String buildingList = ServerCommunication.getBuildingList();
        JSONArray buildingArray = new JSONArray(buildingList);
        for (int i = 0; i<buildingArray.length(); i++) {
            // Searches the building repository for a specific name until that building with the name is found
            if (buildingArray.getJSONObject(i).getString("buildingName").equals(buildingName)) {
                return buildingArray.getJSONObject(i).getInt("id");
            }
        }
        throw new Exception("building not found");
    }

    /**
     * Get Id of Room with a name
     * @param roomName name of room
     * @return an integer
     * @throws Exception if the room is not found
     */
    public static int getRoomId(String roomName) throws Exception {
        String roomList = ServerCommunication.getRoomList();
        JSONArray roomArray = new JSONArray(roomList);
        for (int i = 0; i < roomArray.length(); i++) {
            // Searches the building repository for a specific name until that building with the name is found
            if (roomArray.getJSONObject(i).getString("name").equals(roomName)) {
                return roomArray.getJSONObject(i).getInt("id");
            }
        }
        throw new Exception("building not found");
    }

    /**
     * Get Id of a building with a room name
     * @param roomID name of room
     * @return an integer
     * @throws Exception if the room is not found
     */
    public static int getBuildingIdFromRoomID(int roomID) throws Exception {
        String roomList = ServerCommunication.getRoomList();
        JSONArray roomArray = new JSONArray(roomList);
        //System.out.println("soba: "+roomArray);
        for (int i = 0; i < roomArray.length(); i++) {
            // Searches the building repository for a specific name until that building with the name is found
            if (roomArray.getJSONObject(i).getInt("id")==roomID) {
                return roomArray.getJSONObject(i).getInt("buildingId");
            }
        }
        throw new Exception("room not found");
    }


}
