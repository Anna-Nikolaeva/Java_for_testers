package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by Anna on 02.05.16.
 */
public class GroupModification extends TestBase {

    @Test
    public void testGroupModification(){

        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectFirstGroup();
        app.getGroupHelper().groupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("firstupdated", "secondupdated", "thirdupdated"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }

}
