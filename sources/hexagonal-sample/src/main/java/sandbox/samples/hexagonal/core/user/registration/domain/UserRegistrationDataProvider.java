package sandbox.samples.hexagonal.core.user.registration.domain;

import java.util.Optional;

public interface UserRegistrationDataProvider {

  Optional<UserAccountData> findUserByActivationCode(String confirmationCode);

}
