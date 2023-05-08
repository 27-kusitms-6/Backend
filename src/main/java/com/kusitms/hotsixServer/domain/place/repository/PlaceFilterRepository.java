package com.kusitms.hotsixServer.domain.place.repository;

import com.kusitms.hotsixServer.domain.place.entity.PlaceFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceFilterRepository extends JpaRepository<PlaceFilter, Long> {
    List<PlaceFilter> findByPlaceIdOrderByCountDesc(Long placeId);

}