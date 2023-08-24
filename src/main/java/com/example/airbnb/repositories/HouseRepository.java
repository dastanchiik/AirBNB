package com.example.airbnb.repositories;

import com.example.airbnb.models.House;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HouseRepository extends JpaRepository<House,Long> {
    List<House> findAll(@Param("sort") Sort sort);
//    List<House> findAllHouses();

    @Query("select h from House h where h.status = null order by h.createdAt desc")
    List<House> getAllApplications();

    @Query("select h from House h where h.status = null and h.id =:id")
    House getApplicationById(@Param("id") Long id);

//    @Query("select h from House h where h.bookedType =:bookedType and h.homeType =:homType order by h.rating desc")
//    List<House> getSortedPopular(@Param("bookedType") BookedType bookedType,@Param("homeType") HomeType homeType);
//
//    @Query("select h from House h where h.bookedType =:bookedType and h.homeType =:homType order by h.rating,h.price,h.createdAt desc")
//    List<House> getSortedPopularAndHighAndLasted(@Param("bookedType") BookedType bookedType,@Param("homeType") HomeType homeType);
//
//    @Query("select h from House h where h.bookedType =:bookedType and h.homeType =:homType order by h.createdAt desc")
//    List<House> getSortedLasted(@Param("bookedType") BookedType bookedType,@Param("homeType") HomeType homeType);
//
//    @Query("select h from House h where h.bookedType =:bookedType and h.homeType =:homType order by h.price")
//    List<House> getSortedPriceLow(@Param("bookedType") BookedType bookedType,@Param("homeType") HomeType homeType);
//
//    @Query("select h from House h where h.bookedType =:bookedType and h.homeType =:homType order by h.price")
//    List<House> getSortedPriceHigh(@Param("bookedType") BookedType bookedType,@Param("homeType") HomeType homeType);
}