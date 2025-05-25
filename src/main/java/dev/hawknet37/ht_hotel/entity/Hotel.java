package dev.hawknet37.ht_hotel.entity;

import lombok.Data;

@Data
public class Hotel {
    private String hotelId;
    private String hotelName;
    private Boolean status = true;
    private Integer rate;
}
