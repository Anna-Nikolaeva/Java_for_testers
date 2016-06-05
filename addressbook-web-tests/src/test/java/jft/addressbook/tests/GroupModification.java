package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Anna on 02.05.16.
 */
public class GroupModification extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("first"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData newGroup =  new GroupData()
                .withId(modifiedGroup.getId()).withName("firstupdated").withHeader("secondupdated").withFooter("thirdupdated");
        app.goTo().groupPage();
        app.group().modify(newGroup);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(newGroup)));
        verifyGroupListInUI();
    }
}
