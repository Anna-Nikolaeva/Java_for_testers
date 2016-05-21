package jft.addressbook.tests;

import jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 21.05.16.
 */
public class ContactInfoTests extends TestBase {

    @Test
    public void testContactInfo(){
        app.goTo().homePage();
        ContactData contactFromMainPage = app.contact().all().iterator().next();
        ContactData contactFromEditPage = app.contact().getContactFromEditForm(contactFromMainPage);

        assertThat(contactFromMainPage.getAllPhones(), equalTo(mergePhones(contactFromEditPage)));
        assertThat(contactFromMainPage.getAddress(), equalTo(contactFromEditPage.getAddress()));
        assertThat(contactFromMainPage.getAllEmails(), equalTo(mergeEmails(contactFromEditPage)));
    }

    private String mergePhones(ContactData contact) {
     return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone()).stream()
                .filter((s)-> ! s.equals(""))
             .map(ContactInfoTests::cleanString)
             .collect(Collectors.joining("\n"));
    }

    public static String cleanString (String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

    public static String mergeEmails(ContactData contact){
        return Arrays.asList(contact.getEmail1(),contact.getEmail2(),contact.getEmail3()).stream()
                .filter((s)-> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
