package com.kusitms.hotsixServer.domain.review.repository;

import com.kusitms.hotsixServer.domain.place.entity.Place;
import com.kusitms.hotsixServer.domain.review.entity.ReviewSticker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewStickerRepository extends JpaRepository<ReviewSticker, Long> {


    @Query("SELECT rs.sticker.url, COUNT(rs.sticker) AS stickerCount FROM ReviewSticker rs WHERE rs.review.place = :place AND rs.isPositive = 'Y' GROUP BY rs.sticker ORDER BY stickerCount DESC")
    List<Object[]> findTopPositiveStickersByPlace(@Param("place") Place place, Pageable pageable);

    @Query("SELECT rs.sticker.url, COUNT(rs.sticker) AS stickerCount FROM ReviewSticker rs WHERE rs.review.place = :place AND rs.isPositive = 'N' GROUP BY rs.sticker ORDER BY stickerCount DESC")
    List<Object[]> findTopNegativeStickersByPlace(@Param("place") Place place, Pageable pageable);

    @Query("SELECT rs.sticker.name, COUNT(rs.sticker) AS stickerCount FROM ReviewSticker rs WHERE rs.review.place = :place AND rs.isPositive = 'Y' GROUP BY rs.sticker ORDER BY stickerCount DESC")
    List<Object[]> findTopPositiveStickerNameByPlace(@Param("place") Place place, Pageable pageable);

    @Query("SELECT rs.sticker.name, COUNT(rs.sticker) AS stickerCount FROM ReviewSticker rs WHERE rs.review.place = :place AND rs.isPositive = 'N' GROUP BY rs.sticker ORDER BY stickerCount DESC")
    List<Object[]> findTopNegativeStickerNameByPlace(@Param("place") Place place, Pageable pageable);

    @Query("SELECT COUNT(rs.sticker) FROM ReviewSticker rs WHERE rs.review.place = :place AND rs.isPositive = 'Y' GROUP BY rs.sticker ORDER BY COUNT(rs.sticker) DESC")
    List<Long> findTopPositiveStickerCountsByPlace(@Param("place") Place place, Pageable pageable);

    @Query("SELECT COUNT(rs.sticker) FROM ReviewSticker rs WHERE rs.review.place = :place AND rs.isPositive = 'N' GROUP BY rs.sticker ORDER BY COUNT(rs.sticker) DESC")
    List<Long> findTopNegativeStickerCountsByPlace(@Param("place") Place place, Pageable pageable);

}
