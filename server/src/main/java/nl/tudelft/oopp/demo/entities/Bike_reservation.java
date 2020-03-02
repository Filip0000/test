package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.sql.Date;

@Entity
@Table(name = "bike_reservations")
public class Bike_reservation {
    @Id
    @Column(name = "bike_reservation_id")
    private long id;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "building_id")
    private long building_id;

    @Column(name = "datum")
    private Date datum;

    @Column(name = "time_from")
    private int time_from;

    @Column(name = "time_to")
    private int time_to;

    public Bike_reservation() {
    }

    /**
     * Create a new Room instance.
     *
     * @param id Unique identifier as to be used in the database.
     * @param user_id id of the user that made the reservation
     * @param building_id id of the building where the reservation was made
     * @param datum date of the reservation
     * @param time_from pickup time of the bike
     * @param time_to return time of the bike
     */
    public Bike_reservation(long id, long user_id, long building_id, Date datum, int time_from, int time_to) {
        this.id = id;
        this.user_id = user_id;
        this.building_id = building_id;
        this.datum = datum;
        this.time_from = time_from;
        this.time_to = time_to;
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return user_id;
    }

    public long getBuildingId() {
        return building_id;
    }

    public Date getDate() {
        return datum;
    }

    public int getTimeFrom() {
        return time_from;
    }

    public int getTimeTo() {
        return time_to;
    }

}
