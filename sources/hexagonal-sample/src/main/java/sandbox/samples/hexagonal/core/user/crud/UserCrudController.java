package sandbox.samples.hexagonal.core.user.crud;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
@AllArgsConstructor
public class UserCrudController {

  private UserCrudService userCrudService;

  @RequestMapping(path = "list", method = GET)
  public ResponseEntity<List<UserListItem>> listUsers() {
    return new ResponseEntity<>(userCrudService.findAll(), HttpStatus.OK);
  }

}
