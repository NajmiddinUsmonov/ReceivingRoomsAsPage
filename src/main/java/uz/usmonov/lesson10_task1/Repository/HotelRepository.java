package uz.usmonov.lesson10_task1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.usmonov.lesson10_task1.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    boolean existsByName(String name);
}
