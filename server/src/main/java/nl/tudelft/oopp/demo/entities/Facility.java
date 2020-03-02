package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @Column(name = "facility_id")
    private long id;

    @Column(name = "facilityName")
    private String name;

    public Facility() {
    }

    /**
     * Create a new Room instance.
     *
     * @param id Unique identifier as to be used in the database.
     * @param name name of the facility

     */
    public Facility(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
