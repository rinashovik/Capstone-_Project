package org.launchCode.procrastiNOT.data;

import org.launchCode.procrastiNOT.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
}
