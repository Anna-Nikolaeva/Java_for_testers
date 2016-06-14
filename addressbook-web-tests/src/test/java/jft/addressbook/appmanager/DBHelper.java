package jft.addressbook.appmanager;

import jft.addressbook.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Anna on 05.06.16.
 */
public class DBHelper {

    private final SessionFactory sessionFactory;
    public DBHelper() {

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00' " ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Boolean doesGroupContainContact(Integer contactID, Integer groupID){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Integer> result = session.createQuery( "select id from ContactsInGroups " +
                "where deprecated = '0000-00-00' and group_id=" + groupID + "and id=" + contactID).list();
        session.getTransaction().commit();
        session.close();
        System.out.println(result);

        if(result.size() !=0) {
            return true;
        }else {
            return false;
        }
    }

    public List<ContactsInGroups> getContactsInGroups(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactsInGroups> result = session.createQuery
                ( "from ContactsInGroups where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        System.out.println(result);
        return result;
    }

    public String getGroupNameById(int id){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> result = session.createQuery(" select name from GroupData where id=" + id).list();
        session.getTransaction().commit();
        session.close();
        System.out.println(result.size());
        return result.get(0);
    }
}
