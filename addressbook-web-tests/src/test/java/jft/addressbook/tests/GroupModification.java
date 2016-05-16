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
        app.goTo().groupPage();
        if(app.group().all().size()==0){
            app.group().create(new GroupData().withName("first"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData newGroup =  new GroupData()
                .withId(modifiedGroup.getId()).withName("firstupdated").withHeader("secondupdated").withFooter("thirdupdated");
        app.group().modify(newGroup);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(newGroup)));
    }
}
