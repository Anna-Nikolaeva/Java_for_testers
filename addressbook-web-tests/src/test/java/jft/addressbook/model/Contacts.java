package jft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

/**
 * Created by Anna on 15.05.16.
 */
public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate());
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact){

        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactData contact){

        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

   public Contacts sortedContacts() {

       Contacts contacts = new Contacts(this);
       contacts.stream().sorted((c1, c2) -> Integer.compare((c1.getId()), c2.getId()));
       return contacts;
   }
}
