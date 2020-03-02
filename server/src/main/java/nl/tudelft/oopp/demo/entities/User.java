package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    private long id;

    @Column(name = "netid")
    private String netid;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "account_level")
    private int level;

    public User() {
    }

    /**
     * Create a new Quote instance.
     *
     * @param id Unique identifier as to be used in the database.
     */
    public User(long id, String netid, String email, String password, int level) {
        this.id = id;
        this.netid = netid;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public String getNetId() {
        return netid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    // netid cannot be null, email and password can (unauthorized user)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                level == user.level &&
                netid.equals(user.netid) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, netid);
    }
}
