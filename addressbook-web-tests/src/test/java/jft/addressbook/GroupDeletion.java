package jft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletion extends TestBase {

    @Test
    public void testGroupDeletion() {

        goToGroups();
        selectFirstElement();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}
