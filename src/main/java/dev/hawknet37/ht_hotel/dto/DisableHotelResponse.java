package dev.hawknet37.ht_hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisableHotelResponse {
    private Boolean status;
    private String message;
}
