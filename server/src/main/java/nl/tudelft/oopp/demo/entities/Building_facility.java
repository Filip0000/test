package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "building_facilities")
public class Building_facility {

    @EmbeddedId
    private Building_facilityPK PK;
    @Column(name = "amount")
    private int amount;

    public Building_facility() {
    }

    /**
     * Create a new Room instance.
     *
     * @param PK Unique identifier for each row in the database table
     * @param amount amount of this this specific facility reserved
     */
    public Building_facility(Building_facilityPK PK, int amount) {
        this.PK = PK;
        this.amount = amount;
    }

    public Building_facilityPK getPK() {
        return PK;
    }

    public int getAmount() {
        return amount;
    }
}
