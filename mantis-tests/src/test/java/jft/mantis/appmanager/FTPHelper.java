package jft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Anna on 10.06.16.
 */
public class FTPHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FTPHelper(ApplicationManager app){
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException{
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target,backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException{

        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup,target);
        ftp.disconnect();
    }

}