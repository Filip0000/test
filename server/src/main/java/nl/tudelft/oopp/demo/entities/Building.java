package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "buildings")
    public class Building {
        @Id
        @Column(name = "building_id")
        private long id;

        @Column(name = "name_of_building")
        private String name;

        @Column(name = "address_of_building")
        private String address;

        @Column(name = "numb_of_available_bikes")
        private long bikes;

        public Building() {
        }

    /**
     * Create a new Building instance.
     *
     * @param id Unique identifier as to be used in the database.
     * @param name Name of building
     * @param address Address of building
     * @param bikes number of available bikes
     */
    public Building(long id, String name, String address, long bikes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bikes = bikes;
    }

    public long getId() {
        return id;
    }

    public String getBuildingName() {
        return name;
    }

    public String getBuildingAddress() {
        return address;
    }

    public long getBuildingBikes() {
        return bikes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return id == building.id &&
                bikes == building.bikes &&
                name.equals(building.name) &&
                Objects.equals(address, building.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
