package com.example.airbnb.repositories;

import com.example.airbnb.models.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
}
