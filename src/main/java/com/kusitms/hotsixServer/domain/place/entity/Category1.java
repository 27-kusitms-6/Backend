package com.kusitms.hotsixServer.domain.place.entity;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
@Table(name = "categories1")
public class Category1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category1_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
