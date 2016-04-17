package jft.addressbook;

import org.testng.annotations.Test;

public class GroupCreator extends TestBase {

    @Test
    public void testGroupCreation() {

        goToGroups();
        initGroupCreation();
        fillGroupForm(new GroupData("first", "second", "third"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
