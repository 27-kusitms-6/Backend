package com.kusitms.hotsixServer.domain.place.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
@Table(name = "categories2")
public class Category2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category2_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
