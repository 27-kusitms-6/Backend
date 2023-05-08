package com.kusitms.hotsixServer.domain.place.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

public class PlaceDto {

    @Data
    @Builder
    public static class category1Response {
        private List<PlaceInfo> places;
        @Data
        @Builder
        public static class PlaceInfo {

            private Long id;
            private String name;
            private float starRating;
            private int reviewCount;
            private String placeImg;
            private String content;
            private String openingHours;
            private String[] top2Filters;

        }
    }
    public static category1Response response(List<PlaceDto.category1Response.PlaceInfo> places) {
        return category1Response.builder()
                .places(places)
                .build();
    }
}
