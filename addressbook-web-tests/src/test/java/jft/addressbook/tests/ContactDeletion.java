package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactDeletion  extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){

        if(app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("first").withMiddleName("middle")
                    .withLastname("last").withNickname("nickname").withCompanyName("Microsoft")
                    .withHomePhone("111222333").withMobilePhone("444555666").withWorkPhone("4645452722")
                    .withEmail1("first.lastmiddle.@microsoft.com").withbYear("1978"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        assertThat(app.contact().getGroupCount(), equalTo(before.size()- 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListUI();
    }
}
