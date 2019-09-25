package sandbox.samples.hexagonal.core.user.registration.domain;

public class UserRegistrationFacade {

  private UserRegistration userRegistration;

  public UserRegistrationFacade(
      UserRegistrationDataProvider serviceProvider,
      ConfirmationMailSender confirmationMailSender,
      UserRegistrationNotifier userRegistrationNotifier) {
    this.userRegistration =
        new UserRegistration(serviceProvider, confirmationMailSender, userRegistrationNotifier);
  }

  public UserAccountData createUserAccount(UserRegistrationInput registrationInput) {
    return userRegistration.createUserAccount(registrationInput);
  }

  public UserAccountData activateUserAccount(String activationCode) {
    return userRegistration.activateUserAccount(activationCode);
  }
}
