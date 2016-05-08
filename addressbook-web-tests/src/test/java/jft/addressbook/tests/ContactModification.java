package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getGroupList();
        app.getContactHelper().clickContactModification(before.size()-1);
        ContactData newContact = new ContactData(before.get(before.size()-1).getId(),"firstUpdated", "middleUp", "lastUp", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978",null);
        app.getContactHelper().fillContactForm(newContact,false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goHome();
        List<ContactData> after = app.getContactHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size());

        before.remove(before.size()-1);
        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
