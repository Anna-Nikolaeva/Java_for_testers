package jft.addressbook.tests;

import jft.addressbook.model.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Anna on 06.06.16.
 */
public class TestDeletingContactsFromGroups extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){

        if(app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("first").withMiddleName("middle")
                    .withLastname("last").withNickname("nickname").withCompanyName("Microsoft")
                    .withHomePhone("111222333").withMobilePhone("444555666").withWorkPhone("4645452722")
                    .withEmail1("first.lastmiddle.@microsoft.com").withbYear("1978"));
            app.goTo().homePage();
        }

        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("first"));
        }

        List<ContactsInGroups> contactsInGR = app.db().getContactsInGroups();
        if(contactsInGR.size() == 0) {
            Contacts allContacts = app.db().contacts();
            ContactData contactToAdd = allContacts.iterator().next();
            Groups allGroups = app.db().groups();
            GroupData groupToAddContact = allGroups.iterator().next();
            app.goTo().homePage();
            app.contact().addToGroup(contactToAdd, groupToAddContact.getName());
        }

    }

    @Test
    public void testDeletingContactFromGroup(){

        app.goTo().homePage();
        List<ContactsInGroups> fromDB = app.db().getContactsInGroups();
        String groupName = app.db().getGroupNameById(fromDB.get(0).getGroup_id());
        app.contact().deleteFromGroup(fromDB.get(0).getId(), groupName);
        Assert.assertFalse(app.db().doesGroupContainContact(fromDB.get(0).getId(), fromDB.get(0).getGroup_id()));
    }
}
