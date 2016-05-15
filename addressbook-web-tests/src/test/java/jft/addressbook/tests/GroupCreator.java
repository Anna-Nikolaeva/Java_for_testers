package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreator extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData newGroup = new GroupData().withName("test1");
        app.group().create(newGroup);
        Set<GroupData> after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

         assertThat(after, equalTo(
                before.withAdded(newGroup.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
