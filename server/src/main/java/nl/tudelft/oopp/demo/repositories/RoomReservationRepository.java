package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Room_reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface RoomReservationRepository extends JpaRepository<Room_reservation, Long> {

    //find all reservations of a specific room
    @Query(value = "SELECT * FROM room_reservations WHERE room_id = ?1", nativeQuery=true)
    public List<Room_reservation> findByRoomId(int roomId);

    //find all reservations of a specific user
    @Query(value = "SELECT * FROM room_reservations WHERE user_id = ?1", nativeQuery=true)
    public List<Room_reservation> findByUserId(int UserId);

    //not working
    @Modifying
    @Transactional
    //insert new reservation
    @Query(value = "INSERT INTO room_reservations (user_id, room_id, building_id, datum, time_from, time_to) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery=true)
    public void insertReservation(long user_id, long room_id, long building_id, Date datum, int time_from, int time_to);

    @Modifying
    @Transactional
    //update a reservation
    @Query(value = "UPDATE room_reservations SET (user_id, room_id, building_id, datum, time_from, time_to) VALUES (?2, ?3, ?4, ?5, ?6, ?7) WHERE reservation_id = ?1", nativeQuery=true)
    public void insertReservation(long reservation_id ,long user_id, long room_id, long building_id, Date datum, int time_from, int time_to);


}
