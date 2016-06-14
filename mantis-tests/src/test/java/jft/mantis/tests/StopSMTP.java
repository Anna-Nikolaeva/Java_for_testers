package jft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Anna on 14.06.16.
 */
public class StopSMTP extends TestBase{

    @Test
    public void stopMailServer(){
        app.mail().stop();
    }
}
