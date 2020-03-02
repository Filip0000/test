package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.Room_reservation;
import nl.tudelft.oopp.demo.repositories.BuildingRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;


@Controller
@RequestMapping("/reservations")
public class ReservationController {
    /**
     * GET Endpoint for reservations
     */

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomReservationRepository roomReservationRepository;


    /** Endpoint for all reservations
     * Comparator needs to be implemented
     * @return list of all reservations
     */
    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("reservations")
    @ResponseBody
    public List<Room_reservation> getAllReservations() {
        List<Room_reservation> reservationList = roomReservationRepository.findAll();
//        Comparator<Building> cmp = (Building o1, Building o2) -> o1.getBuildingName().compareTo(o2.getBuildingName());
//        buildingList.sort(cmp);
        return reservationList;
    }

    /** Endpoint for all reservations from a user
     * @return a list of reservations from a user
     * @throws IllegalAccessException if no reservation is present
     */
    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Room_reservation> getMyReservations(@PathVariable final int userId) throws IllegalAccessException {
        List<Room_reservation> rr = roomReservationRepository.findByUserId(userId);
        if (rr.isEmpty()) {
            throw new IllegalAccessException("No reservations found");
        }
        else return rr;
    }

    /**
     * Adds a reservation to the database.
     * @param jsonString - jsonString of the key-values of reservation object
     */
    @RequestMapping(value="/create", method = RequestMethod.POST)
    @ResponseBody
    public void insertReservation(@RequestBody String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        long user_id = obj.getInt("user_id");
        long room_id = obj.getInt("room_id");
        long building_id = obj.getInt("building_id");
        String datum = obj.getString("date");
        int time_from = obj.getInt("time_from");
        int time_to = obj.getInt("time_to");
        System.out.println("user_id: " + user_id);
        System.out.println("room_id: " + room_id);
        System.out.println("building_id: " + building_id);
        System.out.println("date: " + datum);
        System.out.println("time_from: " + time_from);
        System.out.println("time_to: " + time_to);
        Date date = Date.valueOf(datum);
        //TODO uncomment line below when Date is working and uncomment SystemPrints.
        roomReservationRepository.insertReservation(user_id, room_id, building_id, date, time_from, time_to);
    }

    @RequestMapping(value = "/delete/{buildingId}", method = RequestMethod.DELETE)
    public void deleteBuilding(@PathVariable int buildingId) {
        buildingRepository.deleteBikeReservation(buildingId);
    }
}
