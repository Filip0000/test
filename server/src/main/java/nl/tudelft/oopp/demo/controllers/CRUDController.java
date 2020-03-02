package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.repositories.BuildingRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/crud")
public class CRUDController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(value="/insertbuilding", method = RequestMethod.POST)
    @ResponseBody
    public void insertBuilding(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String address = body.get("address");
        String numberOfBikes = body.get("numberOfBikes");
        buildingRepository.insertBuilding(name, address, Integer.parseInt(numberOfBikes));
    }



    /**
     * Handles building updates
     */
    @RequestMapping(value = "/editBuilding", method = RequestMethod.POST)
    @ResponseBody
    public void editBuilding(@RequestBody String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        long buildingID = obj.getLong("buildingID");
        String buildingName = obj.getString("buildingName");
        String buildingAddress = obj.getString("buildingAddress");
        int numberOfBikes = obj.getInt("numberOfBikes");
        buildingRepository.updateBuilding(buildingID,buildingName,buildingAddress,numberOfBikes);
    }

    @RequestMapping(value="/insertroom", method = RequestMethod.POST)
    @ResponseBody
    public void insertRoom(@RequestBody String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        String roomName = obj.getString("roomName");
        int roomCapacity = obj.getInt("roomCapacity");
        String roomDescription = obj.getString("roomDescription");
        int roomType = obj.getInt("roomType");
        int buildingID = obj.getInt("roomBuildingID");
        roomRepository.insertRoom(roomName, roomDescription, roomCapacity, roomType, buildingID);
    }

    /**
     * Handles building updates
     */
    @RequestMapping(value = "/editRoom", method = RequestMethod.POST)
    @ResponseBody
    public void editRoom(@RequestBody String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        long roomID = obj.getLong("roomID");
        String roomName = obj.getString("roomName");
        int roomCapacity = obj.getInt("roomCapacity");
        String roomDescription = obj.getString("roomDescription");
        int roomType = obj.getInt("roomType");
        int buildingID = obj.getInt("buildingID");
        System.out.println(" " + roomID + " " + roomName + " " + roomCapacity + " " + roomDescription+ " " + roomType + " " + buildingID);
        roomRepository.updateRoom(roomID, roomName, roomDescription, roomCapacity, roomType, buildingID);
    }

    @RequestMapping(value = "/deleteRoom/{roomID}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable int roomID) {
        roomRepository.deleteRoom(roomID);
    }

    @RequestMapping(value = "/deleteBuilding/{buildingId}", method = RequestMethod.DELETE)
    public void deleteBuilding(@PathVariable int buildingId) {
        buildingRepository.deleteBikeReservation((long)buildingId);
    }
}
