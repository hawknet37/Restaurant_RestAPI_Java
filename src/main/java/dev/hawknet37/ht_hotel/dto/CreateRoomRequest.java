package dev.hawknet37.ht_hotel.dto;

import lombok.Data;

@Data
public class CreateRoomRequest {
    private String roomId;
    private String roomName;
    private Integer rate;
}
