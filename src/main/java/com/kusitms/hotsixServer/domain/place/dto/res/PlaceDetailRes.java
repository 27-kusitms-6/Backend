package com.kusitms.hotsixServer.domain.place.dto.res;


import com.kusitms.hotsixServer.domain.review.dto.ReviewDto;
import lombok.*;

import java.util.List;

@Data
@Builder
public class PlaceDetailRes {

    private List<PlaceDetailRes.PlaceInfo> places;

    @Data
    @Builder
    public static class PlaceInfo {
        private Long id;
        private String name;
        private float starRating;
        private String content;
        private String placeImg;
        private int reviewCount;
        private String[] top2PositiveStickers;
        private String[] top2NegativeStickers;
        private String[] top2PositiveStickerName;
        private String[] top2NegativeStickerName;
        private int[] top2PositiveStickerCount;
        private int[] top2NegativeStickerCount;
        private char isBookmarked;
        private List<ReviewDto.reviewRes> reviews;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class SimplePlaceRes {
        private Long id;
        private String name;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class SimplePlaceRes2 {
        private Long id;
        private String name;
        private String img;
    }


}


