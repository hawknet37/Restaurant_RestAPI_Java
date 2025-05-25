package dev.hawknet37.ht_hotel.controller;

import dev.hawknet37.ht_hotel.dto.CreateHotelRequest;
import dev.hawknet37.ht_hotel.dto.DisableHotelResponse;
import dev.hawknet37.ht_hotel.dto.UpdateHotelRequest;
import dev.hawknet37.ht_hotel.entity.Hotel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    public static List<Hotel> listHotel = new ArrayList<>();

    @PostMapping
    public Hotel createHotel(@RequestBody CreateHotelRequest hotel){
        Hotel h = new Hotel();
        h.setHotelId(hotel.getHotelId());
        h.setHotelName(hotel.getHotelName());
        h.setRate(hotel.getRate());
        listHotel.add(h);
        return h;
    }

    @GetMapping
    public List<Hotel> getHotels(@RequestParam(required = false) Integer rate, @RequestParam(required = false) Boolean status){
        if(rate == null && status != null){
            return findHotelByStatus(status);
        }
        else if(rate != null && status == null){
            return findHotelByRate(rate);
        }
        else if(rate != null && status != null){
            return findHotelByRateAndStatus(rate, status);
        }
        else
            return listHotel;
    }

    List<Hotel> findHotelByRate(Integer rate){
        List<Hotel> hotel = new LinkedList<>();

        for(Hotel h : listHotel){
            if(h.getRate() == rate){
                hotel.add(h);
            }
        }

        return hotel;
    }

    List<Hotel> findHotelByStatus(Boolean status){
        List<Hotel> hotel = new LinkedList<>();

        for(Hotel h : listHotel){
            if(h.getStatus().equals(status)){
                hotel.add(h);
            }
        }

        return hotel;
    }

    List<Hotel> findHotelByRateAndStatus(Integer rate, Boolean status){
        List<Hotel> hotel = new LinkedList<>();

        for(Hotel h : listHotel){
            if(h.getStatus().equals(status) && h.getRate() == rate){
                hotel.add(h);
            }
        }

        return hotel;
    }

    @DeleteMapping("/{hotelId}")
    public String disableHotel(@PathVariable String hotelId, @RequestParam(required = false, defaultValue = "false") Boolean delete){
        Hotel h = findHotelById(hotelId);

        if(h == null){
            return "Operation Failed: Hotel not found for ID " + hotelId;
        }

        if(delete){
            listHotel.remove(h);
            return "Remove Successful";

        }
        else{
            h.setStatus(false);
            return "Disable Successful";

        }
    }

    @PatchMapping("/{hotelId}")
    public String updateHotel(@PathVariable String hotelId, @RequestBody UpdateHotelRequest hotel){
        Hotel h = findHotelById(hotelId);
        if(h != null)
        {
            h.setHotelName(hotel.getHotelName());
            h.setStatus(hotel.getStatus());
            return "Update Successful";
        }

        return "Update Failed";
    }

    @PutMapping("/{hotelId}")
    public String updateHotels(@PathVariable String hotelId, @RequestBody UpdateHotelRequest hotel){
        Hotel tmp = findHotelById(hotelId);

        if(tmp != null){
            for(Hotel h : listHotel){
                if(h.getHotelId().equals(hotelId)){
                    h.setHotelName(hotel.getHotelName());
                    h.setStatus(hotel.getStatus());
                }
            }
            return "Update Successful";
        }

        return "Update Failed";
    }

    private Hotel findHotelById(String hotelId)
    {
        for(Hotel h : listHotel){
            if(h.getHotelId().equals(hotelId)){
                return h;
            }
        }
        return null;
    }

}
