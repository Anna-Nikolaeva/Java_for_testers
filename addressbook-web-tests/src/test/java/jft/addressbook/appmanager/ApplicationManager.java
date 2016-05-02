package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Anna on 17.04.16.
 */
public class ApplicationManager {

    FirefoxDriver wd;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    //contacts
    public void goHome() {
        groupHelper.wd.findElement(By.linkText("home")).click();
    }

    public void submitContactForm() {
        groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        groupHelper.wd.findElement(By.name("firstname")).click();
        groupHelper.wd.findElement(By.name("firstname")).clear();
        groupHelper.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        groupHelper.wd.findElement(By.name("middlename")).click();
        groupHelper.wd.findElement(By.name("middlename")).clear();
        groupHelper.wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
        groupHelper.wd.findElement(By.name("lastname")).click();
        groupHelper.wd.findElement(By.name("lastname")).clear();
        groupHelper.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        groupHelper.wd.findElement(By.name("nickname")).click();
        groupHelper.wd.findElement(By.name("nickname")).clear();
        groupHelper.wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        groupHelper.wd.findElement(By.name("company")).click();
        groupHelper.wd.findElement(By.name("company")).clear();
        groupHelper.wd.findElement(By.name("company")).sendKeys(contactData.getCompanyName());
        groupHelper.wd.findElement(By.name("theform")).click();
        groupHelper.wd.findElement(By.name("home")).click();
        groupHelper.wd.findElement(By.name("home")).clear();
        groupHelper.wd.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
        groupHelper.wd.findElement(By.name("mobile")).click();
        groupHelper.wd.findElement(By.name("mobile")).clear();
        groupHelper.wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhone());
        groupHelper.wd.findElement(By.name("email")).click();
        groupHelper.wd.findElement(By.name("email")).clear();
        groupHelper.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
        if (!groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[13]")).isSelected()) {
            groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[13]")).click();
        }
        if (!groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[9]")).isSelected()) {
            groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[9]")).click();
        }
        groupHelper.wd.findElement(By.name("byear")).click();
        groupHelper.wd.findElement(By.name("byear")).clear();
        groupHelper.wd.findElement(By.name("byear")).sendKeys(contactData.getbYear());
    }

    public void goToAddContact() {
        groupHelper.wd.findElement(By.linkText("add new")).click();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
