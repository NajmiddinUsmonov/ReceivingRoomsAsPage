package uz.usmonov.lesson10_task1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import uz.usmonov.lesson10_task1.Repository.HotelRepository;
import uz.usmonov.lesson10_task1.Repository.RoomRepository;
import uz.usmonov.lesson10_task1.entity.Room;
import uz.usmonov.lesson10_task1.payload.RoomDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;


    @PostMapping
    public String add(@RequestBody RoomDto roomDto){
        boolean room = roomRepository.existsRoomByNummberAndFloarAndSizeAndHotel_Id(roomDto.getNumber(), roomDto.getFloor(), roomDto.getSize(), roomDto.getHotelId());

        if (!room){
            Room room1=new Room();
            room1.setNummber(roomDto.getNumber());
            room1.setFloar(roomDto.getFloor());
            room1.setSize(roomDto.getSize());
            room1.setHotel(hotelRepository.getById(roomDto.getHotelId()));
            roomRepository.save(room1);
            return "Saved";
        }
        return "Room already exists";

    }

    @GetMapping("/{id}")
    public Room get(@PathVariable Integer id){

        Optional<Room> optionalHotel = roomRepository.findById(id);
        if (optionalHotel.isPresent()){
            Room room = optionalHotel.get();
            return room;
        }
        return new Room();
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> optionalHotel = roomRepository.findById(id);
        if (optionalHotel.isPresent()){
            Room room = optionalHotel.get();
            room.setNummber(roomDto.getNumber());
            room.setFloar(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            room.setHotel(hotelRepository.getById(roomDto.getHotelId()));
            roomRepository.save(room);
            return "Edited";
        }
        return "Room not found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        boolean b = roomRepository.existsById(id);
        if (b) {
            roomRepository.deleteById(id);
            return "Deleted";
        }
        return "Hotel not found";
    }


    /**************** YOU CAN RECEIVE PAGES OF ROOMS ***************************/

    @GetMapping("/forMinistry")
    public Page<Room> getPage(@RequestParam int page){

        Pageable pageable= PageRequest.of(page,5);
        Page<Room> pages = roomRepository.findAll(pageable);
        return pages;
    }

    @GetMapping("/getRoomByHotelID/{hotelId}")
    public Page<Room> getPage(@RequestParam int page,@RequestParam int limit,@PathVariable Integer hotelId){
        Pageable pageable=PageRequest.of(page,limit);
        Page<Room> roomsByHotel_id = roomRepository.findRoomsByHotel_Id(hotelId, pageable);
        return roomsByHotel_id;
    }

}
