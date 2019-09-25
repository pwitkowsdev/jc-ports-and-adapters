package sandbox.samples.hexagonal.core.user.registration.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sandbox.samples.hexagonal.core.user.registration.domain.UserAccountData;
import sandbox.samples.hexagonal.core.user.registration.domain.UserRegistrationFacade;
import sandbox.samples.hexagonal.core.user.registration.domain.UserRegistrationInput;
import sandbox.samples.hexagonal.core.user.registration.infrastructure.repository.UserModel;
import sandbox.samples.hexagonal.core.user.registration.infrastructure.repository.UserRegistrationRepository;

@AllArgsConstructor
@Service
public class UserRegistrationService {

  private UserRegistrationFacade userRegistrationFacade;
  private UserRegistrationRepository userRegistrationRepository;

  @Transactional
  public Long createUserAccount(UserRegistrationInput registrationInput) {
    UserAccountData userData = userRegistrationFacade.createUserAccount(registrationInput);
    UserModel userModel = toModel(userData);
    userRegistrationRepository.save(userModel);
    return userModel.getId();
  }

  @Transactional
  public void activateUserAccount(String activationCode) {
    UserAccountData userData = userRegistrationFacade.activateUserAccount(activationCode);
    UserModel userModel = userRegistrationRepository.findById(userData.getId()).get();
    userModel.setActive(userData.isActive());
  }

  private UserModel toModel(UserAccountData userData) {
    UserModel userModel = new UserModel();
    userModel.setEmail(userData.getEmail());
    userModel.setLogin(userData.getLogin());
    userModel.setActivationCode(userData.getActivationCode());
    userModel.setPassword(userData.getPassword());
    return userModel;
  }

}
