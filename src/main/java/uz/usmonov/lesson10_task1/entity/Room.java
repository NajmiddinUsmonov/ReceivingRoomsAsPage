package uz.usmonov.lesson10_task1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String nummber;


    @Column(nullable = false)
    private String floar;

    @Column(nullable = false)
    private String size;

    @ManyToOne
    private Hotel hotel;
}
