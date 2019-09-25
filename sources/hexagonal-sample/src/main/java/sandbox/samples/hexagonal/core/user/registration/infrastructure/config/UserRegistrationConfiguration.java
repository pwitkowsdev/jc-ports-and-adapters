package sandbox.samples.hexagonal.core.user.registration.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sandbox.samples.hexagonal.core.user.registration.domain.ConfirmationMailSender;
import sandbox.samples.hexagonal.core.user.registration.domain.UserRegistrationDataProvider;
import sandbox.samples.hexagonal.core.user.registration.domain.UserRegistrationFacade;
import sandbox.samples.hexagonal.core.user.registration.domain.UserRegistrationNotifier;

@Configuration
class UserRegistrationConfiguration {

  @Bean
  UserRegistrationFacade userRegistrationFacade(
      UserRegistrationDataProvider serviceProvider,
      ConfirmationMailSender confirmationMailSender,
      UserRegistrationNotifier userRegistrationNotifier) {
    return new UserRegistrationFacade(
        serviceProvider, confirmationMailSender, userRegistrationNotifier);
  }
}
