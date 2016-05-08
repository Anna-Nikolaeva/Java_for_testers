package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactDeletion  extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if(!app.contact().isThereAContact()){
            app.contact().create(new ContactData().withFirstName("first").withMiddleName("middle")
                    .withLastname("last").withNickname("nickname").withCompanyName("Microsoft")
                    .withHomePhone("111222333").withMobilePhone("444555666")
                    .withEmail("first.lastmiddle.@microsoft.com").withbYear("1978").withGroup("first"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testContactDeletion(){
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
