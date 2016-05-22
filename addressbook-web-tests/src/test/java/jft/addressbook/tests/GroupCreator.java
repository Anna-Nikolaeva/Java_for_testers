package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import jft.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreator extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.scv")));
        String line = reader.readLine();
        while(line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {

        app.goTo().groupPage();
        Groups before = app.group().all();
        //GroupData newGroup = new GroupData().withName("test1");
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
        Set<GroupData> after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData newGroup = new GroupData().withName("test1'");
        app.group().create(newGroup);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Set<GroupData> after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
