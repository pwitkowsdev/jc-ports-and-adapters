package sandbox.samples.hexagonal.core.user.registration.domain;

public interface UserRegistrationNotifier {

  void sendMessage(UserAccountData userData);

}
