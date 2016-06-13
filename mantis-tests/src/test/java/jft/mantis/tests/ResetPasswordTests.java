package jft.mantis.tests;

import jft.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Anna on 13.06.16.
 */
public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testPasswordReset(){

        System.out.println(app.db().users());
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
