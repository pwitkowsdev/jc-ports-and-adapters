package sandbox.samples.hexagonal.core.user.registration.infrastructure.entrypoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDto {

  private String login;
  private String email;
  private String password;

}
