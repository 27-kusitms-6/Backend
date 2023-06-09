package com.kusitms.hotsixServer.domain.place.entity;

import com.kusitms.hotsixServer.domain.user.entity.User;
import com.kusitms.hotsixServer.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
@Table(name = "bookmarks")
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder
    public Bookmark (User user, Place place) {
        this.user = user;
        this.place = place;
    }
}

