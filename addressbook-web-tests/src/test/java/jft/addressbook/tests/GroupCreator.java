package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreator extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData newGroup = new GroupData().withName("test1");
        app.group().create(newGroup);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(newGroup);
        Comparator<? super GroupData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
