package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactDeletion  extends TestBase{

    @Test
    public void testContactDeletion(){

        app.getNavigationHelper().goHome();
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createAContact(new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978","first"));
            app.getNavigationHelper().goHome();
        }
        List<ContactData> before = app.getContactHelper().getGroupList();
        app.getContactHelper().selectFirstContact(before.size()-1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptDeletionAlert();
        app.getNavigationHelper().goHome();
        List<ContactData> after = app.getContactHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()-1);
    }
}
