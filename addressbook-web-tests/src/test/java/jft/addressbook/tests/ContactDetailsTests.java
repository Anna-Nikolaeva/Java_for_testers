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
public class ContactDetailsTests extends TestBase {

    @Test
    public void testContactDetails(){

        app.goTo().homePage();
        ContactData infoFromMainPage = app.contact().all().iterator().next();
        String infoFromDetailsPage = app.contact().getContactInfoFromDetailsPage(infoFromMainPage);
        app.goTo().homePage();
        ContactData infoFromEditPage = app.contact().getContactFromEditForm(infoFromMainPage);
        changeAllEmails(infoFromEditPage);
        addLettersToPhone(infoFromEditPage);

        assertThat(cleanString(infoFromDetailsPage), equalTo(cleanString(createStringFromEditPage(infoFromEditPage))));
    }

    public String createStringFromEditPage(ContactData contact){

        String firstRaw = Arrays.asList(contact.getFirstName(),contact.getMiddleName(),contact.getLastname()).stream()
                .filter((s)-> ! s.equals(""))
                .collect(Collectors.joining(" "));
        String result = Arrays.asList(firstRaw, contact.getAddress(),"", contact.getHomePhone(),
                contact.getMobilePhone(),contact.getWorkPhone(),"",contact.getEmail1(),
                contact.getEmail2(),contact.getEmail3()).stream()
                .filter((s)-> ! s.equals(""))
                .collect(Collectors.joining("\n"));

        return result;
    }

    public static String cleanString (String content){
        return content.replaceAll("\\s","");
    }

    public void addLettersToPhone(ContactData contact){
        String phone = "";
        if (!contact.getHomePhone().equals("")) {
            phone = "H: " + contact.getHomePhone();
            contact.withHomePhone(phone);
        }
        if (!contact.getMobilePhone().equals("")) {
            phone = "M: " + contact.getMobilePhone();
            contact.withMobilePhone(phone);
        }
        if (!contact.getWorkPhone().equals("")) {
            phone = "W: " + contact.getWorkPhone();
            contact.withWorkPhone(phone);
        }
    }

    public void changeAllEmails(ContactData contact){

        String email = changeEmail(contact.getEmail1());
        contact.withEmail1(email);
        if(contact.getEmail2() != null) {
            email = changeEmail(contact.getEmail2());
            contact.withEmail2(email);
        }
        email = changeEmail(contact.getEmail3());
        contact.withEmail3(email);

    }

    public String changeEmail(String email){

        String result = "";
        if(email.equals("")){
            return "";
        }
        if(email.contains("@")){

            result =  email.replaceAll(".+?(?<=@)","");
            result = email + " (www." + result + ")";


        } else{
            result =  email + " (www." + email + ")";
        }
        return result;
    }

}
