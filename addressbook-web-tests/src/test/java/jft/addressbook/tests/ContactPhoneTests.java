package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import static jft.addressbook.tests.TestBase.app;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 21.05.16.
 */
public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contactFromMainPage = app.contact().all().iterator().next();
        ContactData contactFromEditPage = app.contact().getContactFromEditForm(contactFromMainPage);

        assertThat(contactFromMainPage.getHomePhone(), equalTo(cleanString(contactFromEditPage.getHomePhone())));
        assertThat(contactFromMainPage.getMobilePhone(), equalTo(cleanString(contactFromEditPage.getMobilePhone())));
        assertThat(contactFromMainPage.getMobilePhone(), equalTo(cleanString(contactFromEditPage.getMobilePhone())));
    }

    public String cleanString (String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
