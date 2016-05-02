package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreator extends TestBase {

    @Test
    public void testContactCreation() {

        app.getContactHelper().goToAddContact();
        app.getContactHelper().fillContactForm(new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978"));
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().goHome();
    }
}
