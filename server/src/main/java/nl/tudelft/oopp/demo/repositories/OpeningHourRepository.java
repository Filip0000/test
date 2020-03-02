package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Opening_hour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpeningHourRepository extends JpaRepository<Opening_hour, Long> {

    @Query(value = "SELECT * FROM opening_hours WHERE building_id = ?1", nativeQuery=true)
    public List<Opening_hour> findByBuildingId(int buildingId);


}
