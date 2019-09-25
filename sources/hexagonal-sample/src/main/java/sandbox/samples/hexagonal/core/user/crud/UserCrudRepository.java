package sandbox.samples.hexagonal.core.user.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sandbox.samples.hexagonal.core.user.registration.infrastructure.repository.UserModel;

@Repository
interface UserCrudRepository extends CrudRepository<UserModel, Long> {

}
