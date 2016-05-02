package jft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Anna on 02.05.16.
 */
public class ContactDeletion  extends TestBase{

    @Test
    public void testContactDeletion(){

        app.getNavigationHelper().goHome();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptDeletionAlert();
        app.getNavigationHelper().goHome();
    }
}
