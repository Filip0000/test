package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "room_reservations")
public class Room_reservation {
    @Id
    @Column(name = "reservation_id")
    private long id;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "room_id")
    private long room_id;

    @Column(name = "building_id")
    private long building_id;

    @Column(name = "datum")
    private String datum;

    @Column(name = "time_from")
    private int time_from;

    @Column(name = "time_to")
    private int time_to;

    public Room_reservation() {
    }

    /**
     * Create a new Room instance.
     *
     * @param id Unique identifier as to be used in the database.
     * @param user_id id of user that made reservation
     * @param room_id id of room that is reserved
     * @param time_from starting time of reservation and date
     * @param time_to end time of reservation
     * @param building_id id of building where room is reserved
     */
    public Room_reservation(long id, long user_id, long room_id, long building_id, String datum, int time_from, int time_to) {
        this.id = id;
        this.user_id = user_id;
        this.room_id = room_id;
        this.time_from = time_from;
        this.time_to = time_to;
        this.datum = datum;
        this.building_id = building_id;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return user_id;
    }

    public long getRoomId() {
        return room_id;
    }

    public long getBuildingId() {
        return building_id;
    }

    public String getDatum() { return datum; }

    public int getTimeFrom() {
        return time_from;
    }

    public int getTimeTo() {
        return time_to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room_reservation that = (Room_reservation) o;
        return id == that.id &&
                user_id == that.user_id &&
                room_id == that.room_id &&
                building_id == that.building_id &&
                Objects.equals(time_from, that.time_from) &&
                Objects.equals(time_to, that.time_to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
