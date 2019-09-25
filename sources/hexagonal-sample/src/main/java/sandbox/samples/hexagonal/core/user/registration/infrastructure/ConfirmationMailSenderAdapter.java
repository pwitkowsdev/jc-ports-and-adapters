package sandbox.samples.hexagonal.core.user.registration.infrastructure;

import org.springframework.stereotype.Component;
import sandbox.samples.hexagonal.core.user.registration.domain.ConfirmationMailData;
import sandbox.samples.hexagonal.core.user.registration.domain.ConfirmationMailSender;
import sandbox.samples.hexagonal.external.MailSender;

@Component
class ConfirmationMailSenderAdapter implements ConfirmationMailSender {

  private MailSender mailSender = new MailSender();

  @Override
  public void send(ConfirmationMailData confirmationMailData) {
    mailSender.send(
        confirmationMailData.getMailTo(),
        confirmationMailData.getSubject(),
        confirmationMailData.getContent());
  }
}
