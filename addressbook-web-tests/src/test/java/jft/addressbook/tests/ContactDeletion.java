package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactDeletion  extends TestBase{

    @Test
    public void testContactDeletion(){

        app.getNavigationHelper().goHome();
        int before = app.getContactHelper().getGroupCount();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createAContact(new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978","first"));
            app.getNavigationHelper().goHome();
        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptDeletionAlert();
        app.getNavigationHelper().goHome();
        int after = app.getContactHelper().getGroupCount();
        Assert.assertEquals(after, before-1);
    }
}
