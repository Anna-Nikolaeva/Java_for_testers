package jft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase {

    @Test
    public void testGroupDeletion() {

        app.goToGroups();
        app.selectFirstGroup();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }

}
