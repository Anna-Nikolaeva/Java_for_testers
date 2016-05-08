package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Anna on 02.05.16.
 */
public class GroupModification extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();
        if(app.group().list().size()==0){
            app.group().create(new GroupData().withName("first"));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData newGroup =  new GroupData()
                .withId(before.get(index).getId()).withName("firstupdated").withHeader("secondupdated").withFooter("thirdupdated");
        app.group().modify(index, newGroup);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(),after.size());

        before.remove(index);
        before.add(newGroup);
        Comparator<? super GroupData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
