package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="Opening_hour")
public class Opening_hour {

    @Id
    @Column(name = "opening_hours_id")
    private long id;

    @Column(name = "building_id")
    private long building_id;

    @Column(name = "day_of_the_week")
    private int day_of_the_week;

    @Column(name = "opening_hour")
    private int opening_hour;

    @Column(name = "closing_hour")
    private int closing_hour;

    public Opening_hour() {
    }

    /**
     * Create a new Stockpile instance.
     *
     * @param id Unique identifier as to be used in the database.
     * @param building_id refers to the building owning the stock
     * @param day_of_the_week int representation of the days of the week(0 = monday ..... 6 = sunday)
     * @param opening_hour amount of pointers
     * @param closing_hour amount of smartboards
     */
    public Opening_hour(long id, long building_id, int day_of_the_week, int opening_hour, int closing_hour) {
        this.id = id;
        this.building_id = building_id;
        this.day_of_the_week = day_of_the_week;
        this.opening_hour = opening_hour;
        this.closing_hour = closing_hour;
    }

    public long getId() {
        return id;
    }

    public long getBuilding_id() {
        return building_id;
    }

    public long getDayOfTheWeek() {
        return day_of_the_week;
    }

    public long getOpeningHour() {
        return opening_hour;
    }

    public long getClosingHour() {
        return closing_hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opening_hour that = (Opening_hour) o;
        return id == that.id &&
                building_id == that.building_id &&
                day_of_the_week == that.day_of_the_week &&
                opening_hour == that.opening_hour &&
                closing_hour == that.closing_hour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
