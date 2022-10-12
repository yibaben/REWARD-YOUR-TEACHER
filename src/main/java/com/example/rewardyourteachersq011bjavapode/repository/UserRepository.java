package com.example.rewardyourteachersq011bjavapode.repository;


import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);


    List<User> findByRoleAndNameContainingIgnoreCase(Role role , String keyword);

    @Query(value = "SELECT * FROM users WHERE name = ?1" , nativeQuery = true)
    User findUserByName( String keyword);


}
