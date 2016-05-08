package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreator extends TestBase {

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goHome();
        List<ContactData> before = app.getContactHelper().getGroupList();
        ContactData newContact = new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978","first");
        app.getContactHelper().createAContact(newContact);
        app.getNavigationHelper().goHome();
        List<ContactData> after = app.getContactHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()+ 1);

        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
