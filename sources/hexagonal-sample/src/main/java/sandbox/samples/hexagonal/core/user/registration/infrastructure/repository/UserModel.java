package sandbox.samples.hexagonal.core.user.registration.infrastructure.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user")
public class UserModel {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String email;
  private String login;
  private String password;
  private boolean active;
  private String activationCode;

}
