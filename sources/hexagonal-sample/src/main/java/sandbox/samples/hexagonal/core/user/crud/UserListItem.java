package sandbox.samples.hexagonal.core.user.crud;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class UserListItem {

  private Long id;
  private String login;
  private String activationCode;
  private boolean active;

}
