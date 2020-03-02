package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
public class Building_facilityPK implements Serializable {

    @Column(name = "building_id")
    private long building_id;

    @Column(name = "facility_id")
    private long facility_id;

    public Building_facilityPK() {
    }

    /**
     * Create a new Room instance.
     *
     * @param building_id id of the reservation linked to the the reserved facility
     * @param facility_id id of the reserved facility
     */
    public Building_facilityPK(long building_id, long facility_id, int amount) {
        this.building_id = building_id;
        this.facility_id = facility_id;
    }

    public long getBuildingId() {
        return building_id;
    }

    public long getFacilityId() {
        return facility_id;
    }

}
