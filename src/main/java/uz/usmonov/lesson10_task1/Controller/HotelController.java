package uz.usmonov.lesson10_task1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.usmonov.lesson10_task1.Repository.HotelRepository;
import uz.usmonov.lesson10_task1.entity.Hotel;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;


    @PostMapping
    public String add(@RequestBody Hotel hotel){
        boolean hotelByName = hotelRepository.existsByName(hotel.getName());
        if (!hotelByName) {
            Hotel hotel1 = new Hotel();
            hotel1.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "Saved";
        }
        return "Hotel already exists";
    }

    @GetMapping("/{id}")
    public Hotel get(@PathVariable Integer id){

        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel hotel = optionalHotel.get();
            return hotel;
        }
        return new Hotel();
    }

    @GetMapping
    public List<Hotel> getAll(){
        List<Hotel> all = hotelRepository.findAll();
        return all;
    }
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel hotel1 = optionalHotel.get();
            hotel1.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "Edited";
        }
        return "Hotel not found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        boolean b = hotelRepository.existsById(id);
        if (b) {
            hotelRepository.deleteById(id);
            return "Deleted";
        }
        return "Hotel not found";
    }




}

