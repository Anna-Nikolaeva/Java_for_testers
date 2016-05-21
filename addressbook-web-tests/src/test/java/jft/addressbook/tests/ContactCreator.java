package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreator extends TestBase {

    @Test
    public void testContactCreation() {

        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData newContact = new ContactData().withFirstName("first").withMiddleName("middle").withLastname("last").withNickname("nickname").withCompanyName("Microsoft").withHomePhone("111222333").withMobilePhone("444555666").withWorkPhone("555666777").withEmail1("first.lastmiddle.@microsoft.com").withbYear("1978").withGroup("first");
        app.contact().create(newContact);
        app.goTo().homePage();
        assertThat(app.contact().getGroupCount(), equalTo(before.size()+ 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo
                (before.withAdded(newContact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }
}
