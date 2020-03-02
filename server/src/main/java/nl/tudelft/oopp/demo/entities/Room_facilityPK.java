package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
public class Room_facilityPK implements Serializable {

    @Column(name = "room_id")
    private long room_id;

    @Column(name = "facility_id")
    private long facility_id;

    public Room_facilityPK() {
    }

    /**
     * Create a new Room instance.
     *
     * @param room_id id of the reservation linked to the the reserved facility
     * @param facility_id id of the reserved facility
     */
    public Room_facilityPK(long room_id, long facility_id, int amount) {
        this.room_id = room_id;
        this.facility_id = facility_id;
    }

    public long getRoomId() {
        return room_id;
    }

    public long getFacilityId() {
        return facility_id;
    }

}
