package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Bike_reservation;
import nl.tudelft.oopp.demo.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    //update building information
    @Modifying
    @Transactional
    @Query(value = "UPDATE buildings SET name_of_building = ?2, address_of_building = ?3, numb_of_available_bikes = ?4 WHERE building_id = ?1", nativeQuery=true)
    public void updateBuilding(long buildingId, String name, CharSequence address, int bikes);

    //insert new building in database
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO buildings (name_of_building, address_of_building, numb_of_available_bikes) VALUES (?1, ?2, ?3)", nativeQuery = true)
    public void insertBuilding(String name, CharSequence address, int bikes);

    //deletes entry from building table
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM buildings WHERE building_id = ?1", nativeQuery=true)
    public void deleteBikeReservation(long buildingId);

}
