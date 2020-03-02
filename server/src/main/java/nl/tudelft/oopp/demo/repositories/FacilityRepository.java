package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {}
