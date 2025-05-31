package com.Prakash.AirBnb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {
    // parameters through which hotels can be searched
    private String city ;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomsCount;

    // adding these fields for pagination
    private Integer page = 0 ;
    private Integer size = 10;
}
