package dev.hawknet37.ht_hotel.controller;

import dev.hawknet37.ht_hotel.dto.CreateRoomRequest;
import dev.hawknet37.ht_hotel.dto.UpdateRoomRequest;
import dev.hawknet37.ht_hotel.entity.Room;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/api/v1/rooms")
@RestController
public class RoomController {
    private static List<Room> rooms = new ArrayList<>();

    @PostMapping
    public Room createRoom(@RequestBody CreateRoomRequest room){
        Room r = new Room();
        r.setRoomId(room.getRoomId());
        r.setRoomName(room.getRoomName());
        r.setRate(room.getRate());

        rooms.add(r);
        return r;
    }

    @GetMapping
    public List<Room> getRooms(@RequestParam(required = false) Integer rate, @RequestParam(required = false) Boolean status){
        if(rate != null && status == null){
            return getRoomsByRate(rate);
        }
        else if(rate == null && status != null){
            return getRoomsByStatus(status);
        }
        else if(rate != null && status != null){
            return getRoomsByRateAndStatus(rate, status);
        }

        return rooms;
    }

    public List<Room> getRoomsByRate(Integer rate){
        List<Room> list = new LinkedList<>();
        for(Room r : rooms){
            if(r.getRate() == rate){
                list.add(r);
            }
        }

        return list;
    }

    public List<Room> getRoomsByStatus(Boolean status){
        List<Room> list = new LinkedList<>();
        for(Room r : rooms){
            if(r.getStatus().equals(status)){
                list.add(r);
            }
        }

        return list;
    }

    public List<Room> getRoomsByRateAndStatus(Integer rate, Boolean status){
        List<Room> list = new LinkedList<>();
        for(Room r : rooms){
            if(r.getRate() == rate && r.getStatus().equals(status)){
                list.add(r);
            }
        }

        return list;
    }

    @PatchMapping("/{roomId}")
    public String updateRoom(@PathVariable String roomId, @RequestBody UpdateRoomRequest room){
        Room r = findRoomById(roomId);

        if(r != null){
            r.setRoomName(room.getRoomName());
            r.setRate(room.getRate());
            r.setStatus(room.getStatus());

            return "Update Successful";
        }

        return "Update Failed";
    }

    @PutMapping("/{roomId}")
    public String updateRoomsById(@PathVariable String roomId, @RequestBody UpdateRoomRequest room){
        Room r = findRoomById(roomId);

        if(r != null){
            for(Room rtmp : rooms){
                if(rtmp.getRoomId().equals(roomId)){
                    rtmp.setRoomName(room.getRoomName());
                    rtmp.setRate(room.getRate());
                    rtmp.setStatus(room.getStatus());
                }
            }

            return "Update Successful";
        }


        return "Update Failed";
    }

    @DeleteMapping
    public String deleteAndDisableRooms(@RequestParam(required = false, defaultValue = "false") Boolean delete){
        if(delete){
            rooms.clear();
            return "Delete successful";
        }
        else{
            for(Room r : rooms){
                r.setStatus(false);
            }

            return "Disable successful";
        }
    }

    @DeleteMapping("/{roomId}")
    public String deleteAndDisableRoom(@PathVariable String roomId, @RequestParam(required = false, defaultValue = "false") Boolean delete){
        Room r = findRoomById(roomId);

        if(r == null){
            return "Not found " + roomId;
        }

        if(delete){
            rooms.remove(r);
            return "Delete " + roomId + " successful";
        }
        else{
            r.setStatus(false);
            return "Disable " + roomId + " successful";
        }
    }

    private Room findRoomById(String roomId){
        for(Room r : rooms){
            if(r.getRoomId().equals(roomId)){
                return r;
            }
        }
        return null;
    }
}
