package uz.usmonov.lesson10_task1.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uz.usmonov.lesson10_task1.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    boolean existsRoomByNummberAndFloarAndSizeAndHotel_Id(String number,String floor,String size,Integer hotel_id);

    Page<Room> findRoomsByHotel_Id(Integer hotelId, Pageable pageable);
}
