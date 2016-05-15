package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData newGroup =  new GroupData()
                .withId(modifiedGroup.getId()).withName("firstupdated").withHeader("secondupdated").withFooter("thirdupdated");
        app.group().modify(newGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size(),after.size());

        before.remove(modifiedGroup);
        before.add(newGroup);
        Assert.assertEquals(before, after);
    }
}
