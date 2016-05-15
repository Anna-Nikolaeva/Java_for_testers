package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreator extends TestBase {

    @Test
    public void testContactCreation() {

        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData newContact = new ContactData().withFirstName("first").withMiddleName("middle").withLastname("last").withNickname("nickname").withCompanyName("Microsoft").withHomePhone("111222333").withMobilePhone("444555666").withEmail("first.lastmiddle.@microsoft.com").withbYear("1978").withGroup("first");
        app.contact().create(newContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+ 1);

        newContact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
        before.add(newContact);
        Assert.assertEquals(before, after);
    }
}
