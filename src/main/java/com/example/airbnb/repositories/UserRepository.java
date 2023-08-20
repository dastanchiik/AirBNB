package com.example.airbnb.repositories;

import com.example.airbnb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
