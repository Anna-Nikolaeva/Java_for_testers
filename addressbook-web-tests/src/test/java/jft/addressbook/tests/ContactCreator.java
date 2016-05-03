package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreator extends TestBase {

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goHome();
        List<ContactData> before = app.getContactHelper().getGroupList();
        app.getContactHelper().createAContact(new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978","first"));
        app.getNavigationHelper().goHome();
        List<ContactData> after = app.getContactHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()+ 1);
    }
}
