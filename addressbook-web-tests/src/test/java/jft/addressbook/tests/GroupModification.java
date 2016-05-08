package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.IntBinaryOperator;

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
        GroupData newGroup =  new GroupData(before.get(before.size()-1).getId(),"firstupdated", "secondupdated", "thirdupdated");
        app.getGroupHelper().fillGroupForm(newGroup);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(),after.size());

        before.remove(before.size() - 1);
        before.add(newGroup);
        Comparator<? super GroupData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
