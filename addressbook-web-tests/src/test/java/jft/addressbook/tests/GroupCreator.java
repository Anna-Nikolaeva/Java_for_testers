package jft.addressbook.tests;

import jft.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreator extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goToGroups();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("first", "second", "third"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
