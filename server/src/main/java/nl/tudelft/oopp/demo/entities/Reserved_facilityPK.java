package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
public class Reserved_facilityPK implements Serializable {

    @Column(name = "reservation_id")
    private long reservation_id;

    @Column(name = "facility_id")
    private long facility_id;

    public Reserved_facilityPK() {
    }

    /**
     * Create a new Reserved facilityPK instance.
     *
     * @param reservation_id id of the reservation linked to the the reserved facility
     * @param facility_id id of the reserved facility
     */
    public Reserved_facilityPK(long reservation_id, long facility_id, int amount) {
        this.reservation_id = reservation_id;
        this.facility_id = facility_id;
    }

    public long getReservationId() {
        return reservation_id;
    }


    public long getFacilityId() {
        return facility_id;
    }

}
