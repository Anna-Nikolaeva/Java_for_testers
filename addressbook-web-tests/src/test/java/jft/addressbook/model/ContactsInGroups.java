package jft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Anna on 05.06.16.
 */

@Entity
@Table(name = "address_in_groups")
public class ContactsInGroups {

    @Id
    @Column(name = "domain_id")
    private int domain_id;
    @Column(name = "id")
    private int id;
    @Column(name = "group_id")
    private int group_id;

    public int getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(int domain_id) {
        this.domain_id = domain_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
