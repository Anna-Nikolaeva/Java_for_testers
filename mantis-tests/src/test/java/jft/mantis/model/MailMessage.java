package jft.mantis.model;

/**
 * Created by Anna on 11.06.16.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text = text;
    }
}
