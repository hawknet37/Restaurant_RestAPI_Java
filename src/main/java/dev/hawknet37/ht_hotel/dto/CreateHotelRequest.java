package dev.hawknet37.ht_hotel.dto;
import lombok.Data;

@Data
public class CreateHotelRequest {
    private String hotelId;
    private String hotelName;
    private Integer rate;
}
