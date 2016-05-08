package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreator extends TestBase {

    @Test
    public void testContactCreation() {

        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData newContact = new ContactData().withFirstName("first").withMiddleName("middle").withLastname("last").withNickname("nickname").withCompanyName("Microsoft").withHomePhone("111222333").withMobilePhone("444555666").withEmail("first.lastmiddle.@microsoft.com").withbYear("1978").withGroup("first");
        app.contact().create(newContact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()+ 1);

        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
