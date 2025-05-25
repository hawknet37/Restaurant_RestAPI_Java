package dev.hawknet37.ht_hotel.dto;
import lombok.Data;

@Data
public class UpdateHotelRequest {
    private String hotelName;
    private Boolean status;
}
