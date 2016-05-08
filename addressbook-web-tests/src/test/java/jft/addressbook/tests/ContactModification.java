package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            app.contact().create(new ContactData("first", "middle", "last", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978","first"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData newContact = new ContactData(before.get(index).getId(),"firstUpdated", "middleUp", "lastUp", "nickname", "Microsoft", "111222333", "444555666", "first.lastmiddle.@microsoft.com", "1978",null);
        app.contact().modify(index, newContact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());

        before.remove(index);
        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
