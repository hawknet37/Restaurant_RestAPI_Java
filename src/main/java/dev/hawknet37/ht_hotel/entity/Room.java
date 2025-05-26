package dev.hawknet37.ht_hotel.entity;

import lombok.Data;

@Data
public class Room {
    private String roomId;
    private String roomName;
    private Integer rate;
    private Boolean status = true;
}
