package nl.tudelft.oopp.demo.communication;

import nl.tudelft.oopp.demo.controllers.BuildingEditController;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class ServerPosting {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Creates a new building and adds it into the database
     */
    public static void addBuilding(JSONObject obj){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/crud/insertbuilding"))
                .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Status: " + response.statusCode());
        }

        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
    }


    public static void addRoom(JSONObject requestBody) throws JSONException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/crud/insertroom"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Status: " + response.statusCode());
        }

        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
    }

    /**
     * Sending a user to the database.
     * @param obj
     */
    public static void addUser(JSONObject obj) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/addUser"))
                .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Status: " + response.statusCode());
        }

        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
    }

    /**
     * In the requestBody there is a delete statement
     */
    public static void deleteBuilding(int buildingID) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/crud/deleteBuilding/" + buildingID))
                .DELETE()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Status: " + response.statusCode());
        }

        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
    }

    /**
     * Sends HTTP Post request to update building information.
     * @param obj - JSON Object with updated building information.
     */
    public static void editBuilding(JSONObject obj){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/crud/editBuilding"))
                .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Status: " + response.statusCode());
        }

        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
    }

    /**
     * In the requestBody there is a delete statement
     */
    public static void deleteRoom(int roomID) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/crud/deleteRoom/"+roomID))
                .DELETE()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
        }

        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
    }

    /**
     * Sends HTTP Post request to update building information.
     * @param obj - JSON Object with updated building information.
     */
    public static void editRoom(JSONObject obj){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/crud/editRoom/"))
                .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
                .header("Content-Type", "application/json")
                .build();

        //System.out.println("tuka: " + obj.toString());
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
        }

        if (response.statusCode() != 200) {
            System.out.println("Status1: " + response.statusCode());
        }
    }

    /**
     * Creates a new reservation and adds it into the database
     *
     */
    public static void addReservation(JSONObject obj){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/reservations/create"))
                .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("Status: " + response.statusCode());
        }

        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        System.out.println(response.body());
    }
}
