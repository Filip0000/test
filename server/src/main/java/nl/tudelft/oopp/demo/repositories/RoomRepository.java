package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    //return a list of rooms given a certain building id
    @Query(value = "SELECT * FROM rooms WHERE building_id = ?1", nativeQuery=true)
    public List<Room> findByBuildingId(long buildingId);

    //update room information
    @Modifying
    @Transactional
    @Query(value = "UPDATE rooms SET name_of_room = ?2, description=?3, capacity=?4, type=?5, building_id=?6 WHERE room_id = ?1", nativeQuery = true)
    public void updateRoom(long roomId, String name, String description, int capacity, int type, int buildingId);

    //insert new room into database
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rooms (name_of_room, description, capacity, type, building_id) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    public void insertRoom(String name, String description, int capacity, int type, long building_id);

    //deletes entry from room table
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rooms WHERE room_id = ?1", nativeQuery=true)
    public void deleteRoom(long roomId);
    
}
