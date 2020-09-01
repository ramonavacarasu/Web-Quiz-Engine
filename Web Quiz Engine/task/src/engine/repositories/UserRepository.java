package engine.repositories;

import engine.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from Users u where u.email = :email")
    engine.model.User findByEmail(@Param("email") String email);


}