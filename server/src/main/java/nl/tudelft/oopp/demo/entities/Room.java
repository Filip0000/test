package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "room_id")
    private long id;

    @Column(name = "name_of_room")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "type")
    private int type;

    @Column(name = "building_id")
    private long building_id;

    public Room() {
    }

    /**
     * Create a new Room instance.
     *
     * @param id Unique identifier as to be used in the database.
     * @param name name of room
     * @param description description of room
     * @param capacity capacity of room
     * @param type 'type=1' is equivalent to lecture hall or similar 'type=2' is equivalent to project rooms and similar
     * @param building_id refers to the building this room is in
     */
    public Room(long id, String name, String description, int capacity, int type, long building_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.type = type;
        this.building_id = building_id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getType() {
        return type;
    }

    public long getBuildingId() {
        return building_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                capacity == room.capacity &&
                type == room.type &&
                building_id == room.building_id &&
                name.equals(room.name) &&
                Objects.equals(description, room.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
