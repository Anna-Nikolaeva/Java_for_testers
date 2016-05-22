package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"),contactData.getMiddleName());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        attach(By.name("photo"),contactData.getPhoto());
        type(By.name("company"),contactData.getCompanyName());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
        type(By.name("email"),contactData.getAllEmails());
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[13]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[13]"));
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[9]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[9]"));
        }
        type(By.name("byear"),contactData.getbYear());

        /*if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

        }else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }*/
    }

    public void goToAddContact() {
        click(By.linkText("add new"));
    }

    public void selectFirstContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {

        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptDeletionAlert() {

        wd.switchTo().alert().accept();
    }

    public void clickContactModification(int id) {

        WebElement checkbox = wd.findElement(By.id(""+id));
        checkbox.findElement(By.xpath("./../../td[8]/a")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        goToAddContact();
        fillContactForm(contact,true);
        submitContactForm();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement el:elements){
            String lName = el.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String fName = el.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(fName).withLastname(lName));
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement el:elements){
            String lName = el.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String fName = el.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String phones = el.findElement(By.cssSelector("td:nth-child(6)")).getText();
            String address = el.findElement(By.cssSelector("td:nth-child(4)")).getText();
            String emails = el.findElement(By.cssSelector("td:nth-child(5)")).getText();
            int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstName(fName).withLastname(lName)
                .withAllPhones(phones).withAddress(address).withAllEmails(emails));
        }
        return new Contacts(contactCache);
    }

    public void modify(ContactData contact) {
        clickContactModification(contact.getId());
        fillContactForm(contact,false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(int index) {
        selectFirstContact(index);
        deleteSelectedContact();
        acceptDeletionAlert();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptDeletionAlert();
        contactCache = null;
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public ContactData getContactFromEditForm(ContactData contact){

        clickContactModification(contact.getId());
        String fName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lName = wd.findElement(By.name("lastname")).getAttribute("value");
        String mName = wd.findElement(By.name("middlename")).getAttribute("value");
        String homePh = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePh = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPh = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        return new ContactData().withFirstName(fName).withLastname(lName).withMiddleName(mName)
                .withHomePhone(homePh).withMobilePhone(mobilePh).withWorkPhone(workPh)
                .withAddress(address).withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }

    public String getContactInfoFromDetailsPage(ContactData contact){
        clickContactDetails(contact.getId());
        String info = wd.findElement(By.id("content")).getText();
        //System.out.println(info);
        return info;
    }

    public void clickContactDetails(int id) {
        WebElement checkbox = wd.findElement(By.id(""+id));
        checkbox.findElement(By.xpath("./../../td[7]/a")).click();
    }

}


