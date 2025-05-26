package dev.hawknet37.ht_hotel.dto;

import lombok.Data;

@Data
public class UpdateRoomRequest {
    private String roomName;
    private Integer rate;
    private Boolean status;
}
