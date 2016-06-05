package jft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreator extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testContactCreation(ContactData contact) {

        app.goTo().homePage();
        Contacts before = app.db().contacts();

        File photo = new File("src/test/resources/image.png");
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().getGroupCount(), equalTo(before.size()+ 1));
        Contacts after = app.db().contacts();
        //Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(), c2.getId());
        //before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()));
        //before.sortedContacts();
        //after.sortedContacts();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
        //Assert.assertEquals(after,before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt())));
    }

}
