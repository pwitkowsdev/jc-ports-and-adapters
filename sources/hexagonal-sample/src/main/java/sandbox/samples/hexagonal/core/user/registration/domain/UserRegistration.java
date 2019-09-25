package sandbox.samples.hexagonal.core.user.registration.domain;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
class UserRegistration {

  private UserRegistrationDataProvider serviceProvider;
  private ConfirmationMailSender confirmationMailSender;
  private UserRegistrationNotifier userRegistrationNotifier;

  UserAccountData createUserAccount(UserRegistrationInput registrationInput) {
    validate(registrationInput);
    final String activationCode = RandomStringUtils.randomAlphanumeric(20);
    UserAccountData userData = new UserAccountData();
    userData.setActivationCode(activationCode);
    userData.setEmail(registrationInput.getEmail());
    userData.setPassword(registrationInput.getPassword());
    userData.setLogin(registrationInput.getLogin());

    ConfirmationMailData confirmationMailData =  ConfirmationMailData.builder()
        .confirmationCode(activationCode)
        .mailTo(registrationInput.getEmail())
        .login(registrationInput.getLogin()).build();
    confirmationMailSender.send(confirmationMailData);
    return userData;
  }

  UserAccountData activateUserAccount(String activationCode) {
    UserAccountData userData = serviceProvider.findUserByActivationCode(activationCode)
        .orElseThrow(() ->  new RuntimeException("Couldn't confirm your account."));
    userData.setActive(true);
    userRegistrationNotifier.sendMessage(userData);
    return userData;
  }

  private void validate(UserRegistrationInput registrationInput) {
    if(StringUtils.isBlank(registrationInput.getEmail())) {
      throw new IllegalArgumentException("Email is required.");
    }
  }

}
