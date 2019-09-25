package sandbox.samples.hexagonal.core.user.crud;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sandbox.samples.hexagonal.core.user.registration.infrastructure.repository.UserModel;

@Service
@AllArgsConstructor
class UserCrudService {

  private UserCrudRepository userCrudRepository;

  public List<UserListItem> findAll(){
    Iterable<UserModel> users = userCrudRepository.findAll();
    return StreamSupport
        .stream(users.spliterator(), false)
        .map(this::toUserData)
        .collect(Collectors.toList());
  }

  private UserListItem toUserData(UserModel userModel) {
    return UserListItem.builder()
        .id(userModel.getId())
        .activationCode(userModel.getActivationCode())
        .login(userModel.getLogin())
        .active(userModel.isActive()).build();
  }
}
