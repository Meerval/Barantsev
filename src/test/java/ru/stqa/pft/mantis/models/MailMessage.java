package ru.stqa.pft.mantis.models;

public class MailMessage {
    public String to;
    public static String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
