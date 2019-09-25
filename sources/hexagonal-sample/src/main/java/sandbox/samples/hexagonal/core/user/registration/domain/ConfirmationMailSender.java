package sandbox.samples.hexagonal.core.user.registration.domain;

public interface ConfirmationMailSender {

  void send(ConfirmationMailData confirmationMailData);

}
