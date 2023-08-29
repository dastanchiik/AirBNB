package com.example.airbnb.repositories;

import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByUserName(String username);

    Optional<User> findByEmailOrUserName(String email, String userName);

    @Query("select case when count(u)>0 then true else false end from User u where u.email like :email")
    boolean existsByEmail(@Param(value = "email") String email);

    @Query("select count(u) from User u")
    int countAllUsers();

    @Query(value = "SELECT * FROM User u WHERE u.email = :email ORDER BY u.id DESC LIMIT 1", nativeQuery = true)
    User findOneByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:email% ORDER BY u.id DESC")
    List<User> findLastUsersWithSimilarEmail(@Param("email") String email);



}

