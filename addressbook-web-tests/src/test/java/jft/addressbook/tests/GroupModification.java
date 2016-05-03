package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Anna on 02.05.16.
 */
public class GroupModification extends TestBase {

    @Test
    public void testGroupModification(){

        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if(! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("first", null, null));
        }
        app.getGroupHelper().selectFirstGroup(before-1);
        app.getGroupHelper().groupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("firstupdated", "secondupdated", "thirdupdated"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(before,after);
    }

}
