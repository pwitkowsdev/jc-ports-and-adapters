package sandbox.samples.hexagonal.external;

import org.springframework.stereotype.Component;

//simple implementation of external library to send mails
@Component
public class MailSender {

  public void send(String mailTo, String subject, String content) {
    System.out.println("Send email.");
    System.out.println("To:" + mailTo);
    System.out.println("Subject:" + subject);
    System.out.println("Content: " + content);
  }
}
