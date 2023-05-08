package com.kusitms.hotsixServer.domain.place.entity;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "place_img", columnDefinition = "TEXT", nullable = false)
    private String placeImg;

    @Column(name = "star_rating")
    private float starRating;

    @Column(name = "region")
    private String region;

    @Column(name = "bookmark_count", columnDefinition = "int default 0")
    private int bookmarkCount;

    @Column(name = "review_count", columnDefinition = "int default 0")
    private int reviewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category1_id", referencedColumnName = "category1_id")
    private Category1 category1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category2_id", referencedColumnName = "category2_id")
    private Category2 category2;

}
