package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"),contactData.getMiddleName());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("company"),contactData.getCompanyName());
        click(By.name("theform"));
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
    }

    public void goToAddContact() {
        click(By.linkText("add new"));
    }

    public void selectFirstContact() {

        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public void deleteSelectedContact() {

        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptDeletionAlert() {

        wd.switchTo().alert().accept();
    }
}
