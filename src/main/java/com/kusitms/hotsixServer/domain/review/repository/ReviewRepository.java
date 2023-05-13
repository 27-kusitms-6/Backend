package com.kusitms.hotsixServer.domain.review.repository;

import com.kusitms.hotsixServer.domain.place.entity.Place;
import com.kusitms.hotsixServer.domain.review.entity.Review;
import com.kusitms.hotsixServer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceId(Long placeId);
  
    @Query("SELECT r FROM Review r JOIN FETCH r.place WHERE r.user = :user ORDER BY r.modifiedAt ASC")
    List<Review> findMyReview(@Param("user") User user);

    List<Review> findAllByUserOrderByModifiedAt(User user);

    @Query("SELECT rs.sticker.name, COUNT(rs) AS stickerCount FROM ReviewSticker rs WHERE rs.review.place = :place GROUP BY rs.sticker ORDER BY stickerCount DESC")
    List<Object[]> findTopStickersByPlace(@Param("place") Place place, Pageable pageable);

}

