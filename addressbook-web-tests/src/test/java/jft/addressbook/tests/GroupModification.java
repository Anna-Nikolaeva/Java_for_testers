package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Anna on 02.05.16.
 */
public class GroupModification extends TestBase {

    @Test
    public void testGroupModification(){

        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("first", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectFirstGroup(before.size()-1);
        app.getGroupHelper().groupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("firstupdated", "secondupdated", "thirdupdated"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(),after.size());
    }

}
