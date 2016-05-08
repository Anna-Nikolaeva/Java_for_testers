package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.acl.Group;
import java.util.Comparator;
import java.util.List;

public class GroupCreator extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData newGroup = new GroupData("first", null, null);
        app.getGroupHelper().createGroup(newGroup);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(newGroup);
        Comparator<? super GroupData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
