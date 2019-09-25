package sandbox.samples.hexagonal.core.user.registration.infrastructure.repository;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sandbox.samples.hexagonal.core.user.registration.domain.UserAccountData;
import sandbox.samples.hexagonal.core.user.registration.domain.UserRegistrationDataProvider;

@Service
@AllArgsConstructor
public class UserRegistrationDataProviderAdapter implements UserRegistrationDataProvider {

  private UserRegistrationRepository userRegistrationRepository;

  @Override
  public Optional<UserAccountData> findUserByActivationCode(String activationCode) {
    Optional<UserModel> userModel = userRegistrationRepository.findByActivationCode(activationCode);
    return userModel.map(this::toUserData);
  }

  private UserAccountData toUserData(UserModel userModel) {
    return UserAccountData.builder()
        .id(userModel.getId())
        .activationCode(userModel.getActivationCode())
        .login(userModel.getLogin())
        .email(userModel.getEmail())
        .password(userModel.getPassword())
        .active(userModel.isActive()).build();
  }
}
