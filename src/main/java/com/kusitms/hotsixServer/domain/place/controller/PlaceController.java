package com.kusitms.hotsixServer.domain.place.controller;


import com.kusitms.hotsixServer.domain.place.constant.PlaceConstants;
import com.kusitms.hotsixServer.domain.place.dto.PlaceDto;
import com.kusitms.hotsixServer.domain.place.service.PlaceService;
import com.kusitms.hotsixServer.global.dto.ResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @ApiOperation("카테고리1(음식점, 카페, 놀거리) 선택시 장소 출력 코드")
    @GetMapping("/list/{category1Id}")
    public ResponseEntity<PlaceDto.category1Response> getPlacesByCategory1(@PathVariable("category1Id") Long category1Id) {
        PlaceDto.category1Response placeResponse = placeService.getPlacesByCategory1Response(category1Id);

        return ResponseEntity.ok(ResponseDto.create(
                PlaceConstants.EBoardResponseMessage.RESPONSE_PLACE_CATEGORY1_SUCCESS.getMessage(),
                placeResponse).getData());
    }
}
