package com.example.airbnb.repositories;

import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.BookedType;
import com.example.airbnb.models.enums.HomeType;
import com.example.airbnb.models.enums.Kind;
import com.example.airbnb.models.enums.PriceType;
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

    @Query("select h from House h order by h.rating desc")
    List<House>sortedHousesPopular();

    @Query("select h from House h order by h.price desc")
    List<House>sortedHousesHigh();

    @Query("select h from House h order by h.price asc")
    List<House>sortedHousesLow();

    @Query("select h from House h order by h.createdAt desc ")
    List<House>sortedHousesLatest();

    @Query("select h from House h order by h.createdAt,h.price desc ")
    List<House>sortedHousesLatestAndHigh();

    @Query("select h from House h order by h.createdAt desc union select h from House h order by h.price asc")
    List<House>sortedHousesLatestAndLow();

    @Query("select h from House h order by h.rating desc union select h from House h order by h.price asc")
    List<House>sortedHousesPopularAndLow();

    @Query("select h from House h order by h.rating desc union select h from House h order by h.price desc ")
    List<House>sortedHousesPopularAndHigh();

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