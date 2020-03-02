package nl.tudelft.oopp.demo.controllers;

import org.json.JSONObject;
import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.repositories.BuildingRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/building")
public class BuildingController {
    /**
     * GET Endpoint for all buildings
     */

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository roomRepository;


    /** Endpoint for all buildings
     * Comparator needs to be implemented
     * @return list of all buildings
     */
    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("building")
    @ResponseBody
    public List<Building> getBuildings() {
        List<Building> buildingList = buildingRepository.findAll();
        Comparator<Building> cmp = (Building o1, Building o2) -> o1.getBuildingName().compareTo(o2.getBuildingName());
        buildingList.sort(cmp);
        return buildingList;
    }

    /** Endpoint for specific building
     * @return a building object
     * @throws IllegalAccessException if building is not present
     */
    @RequestMapping(value="/{buildingId}", method = RequestMethod.GET)
    @ResponseBody
    public Building getBuilding(@PathVariable final long buildingId) throws IllegalAccessException {
        Optional<Building> b = buildingRepository.findById(buildingId);
        if (b.isEmpty()) {
            throw new IllegalAccessException("Building is not found");
        }
        else return b.get();
    }

    /**
     * Return all rooms from a specific building
     * @param buildingId the building you want the rooms in
     * @return a list of rooms
     * @throws IllegalAccessException if the building is not present
     */
    @RequestMapping(value="/{buildingId}/rooms", method = RequestMethod.GET)
    @ResponseBody
    public List<Room> getRoomsFromBuilding(@PathVariable final long buildingId) throws IllegalAccessException {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (building.isEmpty()) {
            throw new IllegalAccessException("Building is not found");
        }
        List<Room> result = new ArrayList<>();
        for (Room room: roomRepository.findAll()) {
            if (buildingId == room.getBuildingId()) {
                result.add(room);
            }
        }
        return result;
    }

    /**
     * Return a specific room from a specific building
     * @param buildingId the building you want the room in
     * @param roomId the room you want
     * @return a room
     * @throws IllegalAccessException if the building or room is not present
     */
    @RequestMapping(value="/{buildingId}/rooms/{roomId} ", method = RequestMethod.GET)
    @ResponseBody
    public Room getRoomFromBuilding(@PathVariable final long buildingId, @PathVariable final long roomId) throws IllegalAccessException {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (building.isEmpty()) {
            throw new IllegalAccessException("Building is not found");
        }
        List<Room> result = new ArrayList<>();
        for (Room room: roomRepository.findAll()) {
            if (buildingId == room.getBuildingId() && room.getId() == roomId) {
                return room;
            }
        }
        throw new IllegalAccessException("Room is not found");
    }

    /**
     * Return all rooms
     * @return a list of rooms
     */
    @RequestMapping(value="/rooms", method = RequestMethod.GET)
    @ResponseBody
    public List<Room> getRooms() {
        List<Room> rooms = roomRepository.findAll();
        Comparator<Room> cmp = (Room o1, Room o2) -> o1.getName().compareTo(o2.getName());
        rooms.sort(cmp);
        return rooms;
    }

    /**
     * Return a specific room
     * @param roomId the ID of the room you want
     * @return a room
     * @throws IllegalAccessException if the room is not present
     */
    @RequestMapping(value="/rooms/{roomId}", method = RequestMethod.GET)
    @ResponseBody
    public Room getRoom(@PathVariable final long roomId) throws IllegalAccessException {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new IllegalAccessException("Building is not found");
        }
        return room.get();
    }

    @RequestMapping(value = "/delete/{buildingId}", method = RequestMethod.DELETE)
    public void deleteBuilding(@PathVariable int buildingId) {
        buildingRepository.deleteBikeReservation(buildingId);
    }

    /**
     * Handles building updates
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public void editBuilding(@RequestBody String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        long buildingID = obj.getLong("buildingID");
        String buildingName = obj.getString("buildingName");
        String buildingAddress = obj.getString("buildingAddress");
        int numberOfBikes = obj.getInt("numberOfBikes");
        buildingRepository.updateBuilding(buildingID,buildingName,buildingAddress,numberOfBikes);
    }

}
