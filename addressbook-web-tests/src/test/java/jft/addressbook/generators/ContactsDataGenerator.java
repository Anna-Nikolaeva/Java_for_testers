package jft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jft.addressbook.model.ContactData;
import jft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 22.05.16.
 */
public class ContactsDataGenerator {
    @Parameter(names = "-c", description = "Group Count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {

        ContactsDataGenerator generator = new ContactsDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("json")) {
            saveAsJson(contacts, new File(file));
        }else{
            System.out.println("Unrecognized format "+ format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i<count;i++){
            contacts.add(new ContactData().withFirstName(String.format("fname %s", i)).withLastname(String.format("lname %s", i))
                    .withHomePhone(String.format(" %s", i*1200)).withNickname(String.format("nick %s", i))
                    .withAllEmails(String.format("emails %s", i)).withCompanyName(String.format("cname %s", i))
                    .withMobilePhone(String.format(" %s", i*3000)).withWorkPhone(String.format(" %s", i*4000)));
        }
        return contacts;
    }
}
