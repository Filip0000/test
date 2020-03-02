package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Bike_reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface BikeReservationRepository extends JpaRepository<Bike_reservation, Long> {


    //finds all bike reservations made by a user
    @Query(value = "SELECT * FROM bike_reservations WHERE user_id = ?1", nativeQuery=true)
    public List<Bike_reservation> findByUserId(int useId);

    //finds all bike reservations made for a specific building
    @Query(value = "SELECT * FROM bike_reservations WHERE building_id = ?1", nativeQuery=true)
    public List<Bike_reservation> findByBuildingId(int buildingId);

    //finds all bike reservations made for a specific building
    @Query(value = "SELECT * FROM bike_reservations WHERE building_id = ?1", nativeQuery=true)
    public List<Bike_reservation> findByDate(Date datum);

    /**
    //insert bike reservation
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO bike_reservations (user_id, building_id, datum, time_from, time_to) VALUES (?!, ?2, ?3, ?4, ?5)", nativeQuery=true)
    public void insertBikeReservation(long userId, long buildingId, Date date, int time_from, int time_to);
    */

    //update bike reservation
    @Modifying
    @Transactional
    @Query(value = "UPDATE bike_reservations SET user_id = ?2, building_id = ?3, datum = ?4, time_from = ?5, time_to = ?6 WHERE bike_reservation_id = ?1)", nativeQuery=true)
    public void updateBikeReservation(long bike_reservation_id, long userId, long buildingId, Date date, int time_from, int time_to);

    //deletes entry from bike_reservation table
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bike_reservations WHERE bike_reservation_id = ?1", nativeQuery=true)
    public void deleteBikeReservation(long bikeId);

}
