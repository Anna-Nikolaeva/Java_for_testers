package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreator extends TestBase {

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goHome();
        int before = app.getContactHelper().getGroupCount();
        app.getContactHelper().createAContact(new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978","first"));
        app.getNavigationHelper().goHome();
        int after = app.getContactHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }
}
