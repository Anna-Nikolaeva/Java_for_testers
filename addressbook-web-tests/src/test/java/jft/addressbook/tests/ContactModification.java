package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactModification extends TestBase {

    @Test
    public void testContactModification(){

        app.getNavigationHelper().goHome();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createAContact(new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978","first"));
            app.getNavigationHelper().goHome();
        }
        app.getContactHelper().clickContactModification();
        app.getContactHelper().fillContactForm(new ContactData("firstUpdated", "middleUp", "lastUp", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978",null),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goHome();
    }
}
