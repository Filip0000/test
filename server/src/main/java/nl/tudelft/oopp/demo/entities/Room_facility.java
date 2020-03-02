package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "building_facilities")
public class Room_facility {

    @EmbeddedId
    private Room_facilityPK PK;
    @Column(name = "amount")
    private int amount;

    public Room_facility() {
    }

    /**
     * Create a new Room instance.
     *
     * @param PK Unique identifier for each row in the database table
     * @param amount amount of this this specific facility reserved
     */
    public Room_facility(Room_facilityPK PK, int amount) {
        this.PK = PK;
        this.amount = amount;
    }

    public Room_facilityPK getRoomId() {
        return PK;
    }

    public int getAmount() {
        return amount;
    }
}
