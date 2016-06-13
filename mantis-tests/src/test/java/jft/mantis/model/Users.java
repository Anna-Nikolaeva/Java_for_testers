package jft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Anna on 13.06.16.
 */

@Entity
@Table(name = "mantis_user_table")
public class Users {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private int username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }
}
