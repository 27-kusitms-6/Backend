package com.kusitms.hotsixServer.domain.place.repository;

import com.kusitms.hotsixServer.domain.place.entity.Category1;
import com.kusitms.hotsixServer.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByCategory1(Category1 category1);

}
