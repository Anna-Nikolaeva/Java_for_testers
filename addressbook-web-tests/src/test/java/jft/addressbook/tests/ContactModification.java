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
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            app.contact().create(new ContactData().withFirstName("first").withMiddleName("middle")
                    .withLastname("last").withNickname("nickname").withCompanyName("Microsoft")
                    .withHomePhone("111222333").withMobilePhone("444555666")
                    .withEmail("first.lastmiddle.@microsoft.com").withbYear("1978").withGroup("first"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testContactModification(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData newContact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("first").withMiddleName("middle")
                .withLastname("last").withNickname("nickname").withCompanyName("Microsoft")
                .withHomePhone("111222333").withMobilePhone("444555666").withEmail("first.lastmiddle.@microsoft.com")
                .withbYear("1978").withGroup("first");
        app.contact().modify(newContact);
        app.goTo().homePage();
        assertThat(app.contact().getGroupCount(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
    }
}
