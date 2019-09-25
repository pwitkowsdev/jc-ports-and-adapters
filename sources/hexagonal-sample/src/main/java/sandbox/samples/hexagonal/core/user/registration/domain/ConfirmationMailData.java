package sandbox.samples.hexagonal.core.user.registration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ConfirmationMailData {

  private String login;
  private String mailTo;
  private String confirmationCode;

  public String getMailTo() {
    return mailTo;
  }

  public String getSubject() {
    return "Please confirm Your account - " + login;
  }

  public String getContent() {
    return "Please confirm your account with code: " + confirmationCode;
  }

}
