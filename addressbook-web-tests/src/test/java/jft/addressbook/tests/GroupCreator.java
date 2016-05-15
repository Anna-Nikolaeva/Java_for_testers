package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreator extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData newGroup = new GroupData().withName("test1");
        app.group().create(newGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        newGroup.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(newGroup);
        Assert.assertEquals(before, after);
    }

}
