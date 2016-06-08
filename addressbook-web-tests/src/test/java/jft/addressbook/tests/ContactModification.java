package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactModification extends TestBase {

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
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData newContact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("first").withMiddleName("middle")
                .withLastname("last").withNickname("nickname").withCompanyName("Microsoft")
                .withHomePhone("999333777").withMobilePhone("666222999").withWorkPhone("333222111")
                .withEmail1("first.lastmiddle.@microsoft.com").withbYear("1978");
        app.goTo().homePage();
        app.contact().modify(newContact);
        app.goTo().homePage();
        assertThat(app.contact().getGroupCount(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
        verifyContactListUI();
    }
}
