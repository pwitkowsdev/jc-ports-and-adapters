package sandbox.samples.hexagonal.core.user.registration.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserRegistrationInput {

  private String login;
  private String email;
  private String password;

}
