package com.example.airbnb.repositories;

import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.BookedType;
import com.example.airbnb.models.enums.HomeType;
import com.example.airbnb.models.enums.Region;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HouseRepository extends JpaRepository<House,Long> {

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.countViewing desc")
    List<House>sortedHousesPopular(@Param("homeType")HomeType homeType,@Param("bookedType")BookedType bookedType);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.countViewing desc")
    List<House>sortedMainHousesPopular(@Param("homeType")HomeType homeType, @Param("bookedType")Region region);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.price desc")
    List<House>sortedHousesHigh(@Param("homeType")HomeType homeType,@Param("bookedType")BookedType bookedType);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.price desc")
    List<House>sortedMainHousesHigh(@Param("homeType")HomeType homeType,@Param("bookedType")Region region);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.price asc")
    List<House>sortedHousesLow(@Param("homeType")HomeType homeType,@Param("bookedType")BookedType bookedType);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.price asc")
    List<House>sortedMainHousesLow(@Param("homeType")HomeType homeType,@Param("bookedType")Region region);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.createdAt desc ")
    List<House>sortedHousesLatest(@Param("homeType")HomeType homeType,@Param("bookedType")BookedType bookedType);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.createdAt desc ")
    List<House>sortedMainHousesLatest(@Param("homeType")HomeType homeType,@Param("bookedType")Region region);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.createdAt,h.price desc ")
    List<House>sortedHousesLatestAndHigh (@Param("homeType")HomeType homeType,@Param("bookedType")BookedType bookedType);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.createdAt,h.price desc ")
    List<House>sortedMainHousesLatestAndHigh (@Param("homeType")HomeType homeType,@Param("bookedType")Region region);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.createdAt desc, case when h.createdAt is null then 0 else 1 end, h.price asc")
    List<House> sortedHousesLatestAndLow(@Param("homeType") HomeType homeType, @Param("bookedType") BookedType bookedType);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.createdAt desc, case when h.createdAt is null then 0 else 1 end, h.price asc")
    List<House> sortedMainHousesLatestAndLow(@Param("homeType") HomeType homeType, @Param("bookedType") Region region);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.countViewing desc, case when h.countViewing is null then 0 else 1 end, h.price desc")
    List<House> sortedHousesPopularAndHigh(@Param("homeType") HomeType homeType, @Param("bookedType") BookedType bookedType);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.countViewing desc, case when h.countViewing is null then 0 else 1 end, h.price desc")
    List<House> sortedMainHousesPopularAndHigh(@Param("homeType") HomeType homeType, @Param("bookedType") Region region);
    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.region =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.countViewing desc, case when h.countViewing is null then 0 else 1 end, h.price asc")
    List<House> sortedMainHousesPopularAndLow(@Param("homeType") HomeType homeType, @Param("bookedType") Region region);

    @Query("select h from House h where (h.homeType =:homeType or :homeType = 'ALL') and (h.bookedType =:bookedType or :bookedType = 'ALL') and h.status = 'ACCEPTED' order by h.countViewing desc, case when h.countViewing is null then 0 else 1 end, h.price asc")
    List<House> sortedHousesPopularAndLow(@Param("homeType") HomeType homeType, @Param("bookedType") BookedType bookedType);
    @Query("select h from House h where h.status = null order by h.createdAt desc")
    List<House> getAllApplications();

    @Query("select h from House h where h.status = null and h.id =:id")
    House getApplicationById(@Param("id") Long id);

    @Query("select h from House h where h.status = null and h.user.id =:id and h.blocked = false")
    List<House>getAllOnModerationByUserId(@Param("id") Long id);

    @Query(value = "select h from House h where h.homeType = 'HOUSE' and h.blocked = false order by h.countViewing desc")
    List<House> getPopularHouses();

    @Query(value = "select h from House h where h.homeType = 'APARTMENT' and h.blocked = false order by h.countViewing desc")
    List<House> getPopularApartments();

    @Query(value = "select h from House h where h.blocked = false order by h.createdAt desc")
    List<House> getLasted();
}