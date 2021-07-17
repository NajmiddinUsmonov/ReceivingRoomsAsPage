package uz.usmonov.lesson10_task1.payload;

import lombok.Data;


@Data
public class RoomDto {

    private String number;

    private String floor;

    private String size;

    private Integer hotelId;
}

