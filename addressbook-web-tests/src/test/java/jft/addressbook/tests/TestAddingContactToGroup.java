package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Anna on 05.06.16.
 */
public class TestAddingContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){

        if(app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("first").withMiddleName("middle")
                    .withLastname("last").withNickname("nickname").withCompanyName("Microsoft")
                    .withHomePhone("111222333").withMobilePhone("444555666").withWorkPhone("4645452722")
                    .withEmail1("first.lastmiddle.@microsoft.com").withbYear("1978"));
            app.goTo().homePage();

            if(app.db().groups().size() == 0){
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("first"));
            }
        }
    }

    @Test
    public void testAddingContactToGroup(){
        app.goTo().homePage();
        Contacts allContacts = app.db().contacts();
        Iterator<ContactData> iteratorContacts = allContacts.iterator();
        Iterator<ContactData> iteratorContacts2 = allContacts.iterator();
        ContactData contactToAdd = iteratorContacts.next();
        Groups allGroups = app.db().groups();
        Iterator<GroupData> iteratorGroups = allGroups.iterator();
        Iterator<GroupData> iteratorGroups2 = allGroups.iterator();
        //GroupData groupToAddContact = allGroups.iterator().next();
        GroupData groupToAddContact = iteratorGroups.next();

        while(app.db().doesGroupContainContact(contactToAdd.getId(), groupToAddContact.getId())){

            System.out.println(contactToAdd.getGroups()+"  " + groupToAddContact);

            if(contactToAdd.getGroups().contains(groupToAddContact)){
                if(iteratorGroups.hasNext()) {
                    groupToAddContact = iteratorGroups.next();
                }else{
                    //iteratorGroups = iteratorGroups2;
                    if(iteratorContacts.hasNext()) {
                        contactToAdd = iteratorContacts.next();
                    }else {
                        iteratorContacts = iteratorContacts2;
                        iteratorGroups = iteratorGroups2;
                    }
                }
            }else{
                break;
            }
        }
        app.goTo().homePage();
        app.contact().addToGroup(contactToAdd, groupToAddContact.getName());

        Assert.assertTrue(app.db().doesGroupContainContact(contactToAdd.getId(), groupToAddContact.getId()));
    }
}
