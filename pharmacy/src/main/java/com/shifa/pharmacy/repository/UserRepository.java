package com.shifa.pharmacy.repository;

import com.shifa.pharmacy.model.Role;
import com.shifa.pharmacy.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
    List<Role> findAllByAccountLockedFalse();
    boolean   existsByUsername(String username);
    User findUserByUsername(String username);
    List<User> findUsersByJobTittle(String jobTittle);
    List<User> findUsersByJobTittleNot(String jobTittle);
}

