package com.backendfiscale.backendfiscale.Repository;


import com.backendfiscale.backendfiscale.Entites.Role;
import com.backendfiscale.backendfiscale.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User existsUserByEmail(String email);
    User findByRole(Role role);



}
