package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        type(By.name("company"),contactData.getCompanyName());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("email"),contactData.getEmail());
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[13]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[13]"));
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[9]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[9]"));
        }
        type(By.name("byear"),contactData.getbYear());

        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

        }else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public void clickContactModification(int index) {
        WebElement el = wd.findElements(By.name("entry")).get(index);
        el.findElement(By.cssSelector("td:nth-child(8)>a")).click();
        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        goToAddContact();
        fillContactForm(contact,true);
        submitContactForm();
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

    public void modify(int index, ContactData newContact) {
        clickContactModification(index);
        fillContactForm(newContact,false);
        submitContactModification();
    }

    public void delete(int index) {
        selectFirstContact(index);
        deleteSelectedContact();
        acceptDeletionAlert();
    }
}
