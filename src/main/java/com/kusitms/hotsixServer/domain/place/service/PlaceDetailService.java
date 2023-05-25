package com.kusitms.hotsixServer.domain.place.service;

import com.kusitms.hotsixServer.domain.main.dto.res.StickerRes;
import com.kusitms.hotsixServer.domain.place.dto.res.PlaceDetailRes;
import com.kusitms.hotsixServer.domain.place.entity.Bookmark;
import com.kusitms.hotsixServer.domain.place.entity.Place;
import com.kusitms.hotsixServer.domain.place.repository.BookmarkRepository;
import com.kusitms.hotsixServer.domain.place.repository.PlaceRepository;
import com.kusitms.hotsixServer.domain.review.dto.ReviewDto;
import com.kusitms.hotsixServer.domain.review.entity.Review;
import com.kusitms.hotsixServer.domain.review.entity.ReviewSticker;
import com.kusitms.hotsixServer.domain.review.repository.ReviewRepository;
import com.kusitms.hotsixServer.domain.review.repository.ReviewStickerRepository;
import com.kusitms.hotsixServer.domain.review.repository.StickerRepository;
import com.kusitms.hotsixServer.domain.user.entity.User;
import com.kusitms.hotsixServer.domain.user.repository.UserRepository;
import com.kusitms.hotsixServer.global.error.BaseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kusitms.hotsixServer.global.config.SecurityUtil.getCurrentUserEmail;
import static com.kusitms.hotsixServer.global.error.ErrorCode.PLACE_ERROR;

@Service
@Transactional
public class PlaceDetailService {
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewStickerRepository reviewStickerRepository;
    private final UserRepository userRepository;
    private final StickerRepository stickerRepository;
    private final BookmarkRepository bookmarkRepository;

    public PlaceDetailService(PlaceRepository placeRepository, ReviewRepository reviewRepository, ReviewStickerRepository reviewStickerRepository,
                              UserRepository userRepository, BookmarkRepository bookmarkRepository, StickerRepository stickerRepository) {
        this.placeRepository = placeRepository;
        this.reviewRepository = reviewRepository;
        this.reviewStickerRepository = reviewStickerRepository;
        this.userRepository = userRepository;
        this.stickerRepository =stickerRepository;
        this.bookmarkRepository = bookmarkRepository;
        }

    public PlaceDetailRes getPlaceDetail(Long placeId) {
        User user = userRepository.findByUserEmail(getCurrentUserEmail()).orElseThrow();
        Place place = placeRepository.findById(placeId).orElseThrow();
        List<Review> reviews = reviewRepository.findByPlaceId(placeId);

        if (place == null) {
            //장소값 존재하지 않을 시 에러 처리
            throw new BaseException(PLACE_ERROR);
        }

        List<ReviewDto.reviewRes> reviewInfos = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDto.reviewRes reviewInfo = ReviewDto.reviewRes.from(review.getId(), review.getUser().getNickname(),
                    review.getReviewImg(), review.getStarRating(), review.getContent(),
                    review.getLikeCount(),review.getDislikeCount(),StickerInfo(review));
            reviewInfos.add(reviewInfo);
        }

        String[] top2PositiveStickers = getTopPositiveStickers(place);
        String[] top2NegativeStickers = getTopNegativeStickers(place);
        String[] top2PositiveStickerName = getTopPositiveStickerName(place);
        String[] top2NegativeStickerName = getTopNegativeStickerName(place);
        int[] top2PositiveStickerCount = getStickerCounts(place, true);
        int[] top2NegativeStickerCount = getStickerCounts(place, false);


        PlaceDetailRes.PlaceInfo placeInfo = PlaceDetailRes.PlaceInfo.builder()
                .id(place.getId())
                .name(place.getName())
                .starRating(place.getStarRating())
                .content(place.getContent())
                .placeImg(place.getPlaceImg())
                .reviewCount(reviews.size())
                .top2PositiveStickers(top2PositiveStickers)
                .top2NegativeStickers(top2NegativeStickers)
                .top2PositiveStickerName(top2PositiveStickerName)
                .top2NegativeStickerName(top2NegativeStickerName)
                .top2PositiveStickerCount(top2PositiveStickerCount)
                .top2NegativeStickerCount(top2NegativeStickerCount)
                .isBookmarked(checkBookmark(user, place))
                .reviews(reviewInfos)
                .build();

        return PlaceDetailRes.builder()
                .places(Collections.singletonList(placeInfo))
                .build();
    }

    public List<StickerRes> StickerInfo(Review review){
        return stickerRepository.findStickers(review.getId());
    }

    public String[] getStickerNames(Review review) {
        List<ReviewSticker> reviewStickers = review.getReviewStickers();
        String[] stickerNames = new String[reviewStickers.size()];

        for (int i = 0; i < reviewStickers.size(); i++) {
            ReviewSticker reviewSticker = reviewStickers.get(i);
            String stickerName = reviewSticker.getSticker().getName();
            stickerNames[i] = stickerName;
        }

        return stickerNames;
    }

    public String[] getTopPositiveStickers(Place place) {
        List<Object[]> result = reviewStickerRepository.findTopPositiveStickersByPlace(place, PageRequest.of(0, 2));
        String[] topPositiveStickers = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            Object[] row = result.get(i);
            String stickerName = (String) row[0];
            topPositiveStickers[i] = stickerName;
        }

        return topPositiveStickers;
    }

    public String[] getTopNegativeStickers(Place place) {
        List<Object[]> result = reviewStickerRepository.findTopNegativeStickersByPlace(place, PageRequest.of(0, 2));
        String[] topNegativeStickers = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            Object[] row = result.get(i);
            String stickerName = (String) row[0];
            topNegativeStickers[i] = stickerName;
        }

        return topNegativeStickers;
    }

    public String[] getTopPositiveStickerName(Place place) {
        List<Object[]> result = reviewStickerRepository.findTopPositiveStickerNameByPlace(place, PageRequest.of(0, 2));
        String[] topPositiveStickers = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            Object[] row = result.get(i);
            String stickerName = (String) row[0];
            topPositiveStickers[i] = stickerName;
        }

        return topPositiveStickers;
    }
    public String[] getTopNegativeStickerName(Place place) {
        List<Object[]> result = reviewStickerRepository.findTopNegativeStickerNameByPlace(place, PageRequest.of(0, 2));
        String[] topNegativeStickers = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            Object[] row = result.get(i);
            String stickerName = (String) row[0];
            topNegativeStickers[i] = stickerName;
        }

        return topNegativeStickers;
    }

    public int[] getStickerCounts(Place place, boolean isPositive) {
        List<Long> result;
        if (isPositive) {
            result = reviewStickerRepository.findTopPositiveStickerCountsByPlace(place, PageRequest.of(0, 2));
        } else {
            result = reviewStickerRepository.findTopNegativeStickerCountsByPlace(place, PageRequest.of(0, 2));
        }

        int[] stickerCounts = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            stickerCounts[i] = result.get(i).intValue();
        }

        return stickerCounts;
    }

    public char checkBookmark(User user, Place place){
        Bookmark bookmark = bookmarkRepository.findByUserAndPlace(user, place);
        if (bookmark == null) {
            return 'N';
        } else {
            return 'Y';
        }
    }

}





