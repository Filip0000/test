package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "reserved_facility")
public class Reserved_facility {

    @EmbeddedId
    private Reserved_facilityPK PK;
    @Column(name = "amount")
    private int amount;

    public Reserved_facility() {
    }

    /**
     * Create a new Reserved facility instance.
     *
     * @param PK Unique identifier for each row in the database table
     * @param amount amount of this this specific facility reserved
     */
    public Reserved_facility(Reserved_facilityPK PK, int amount) {
        this.PK = PK;
        this.amount = amount;
    }

    public Reserved_facilityPK getPK() {
        return PK;
    }

    public int getAmount() {
        return amount;
    }
}
