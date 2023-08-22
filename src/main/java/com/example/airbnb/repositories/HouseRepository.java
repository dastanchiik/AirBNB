package com.example.airbnb.repositories;

import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HouseRepository extends JpaRepository<House,Long> {

    @Query("select h from House h where h.status = null order by h.createdAt desc")
    List<House> getAllApplications();

    @Query("select h from House h where h.status = null and h.id =:id")
    House getApplicationById(@Param("id") Long id);


    @Query("select  s from  House  s order by s.title desc")
    List<House> search(@RequestParam("title") String title,
                         @RequestParam("town") String town,
                         @RequestParam("region") Region region,@RequestParam("homeType") String homeType);

//    @Query("select s from House s where s.region = :region")
//    List<House> findByRegion(@Param("region") Region region);
//    @Query("select s from House s order by s.region desc ")
//    List<House> searchByRegion();

}
