package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;
//import org.apache.tools.mail.MailMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser();
    }
    public List<MailMessage> waitForMail(int count, long timeout) throws IOException, MessagingException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }

    public static MailMessage toModelMail(WiserMessage message) {
        try {
            MimeMessage mimeMessage = message.getMimeMessage();
            return new MailMessage(mimeMessage.getAllRecipients()[0].toString(),
                    (String) mimeMessage.getContent());
        }
        catch (MessagingException exception) {
            exception.printStackTrace();
            return null;
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }
}
