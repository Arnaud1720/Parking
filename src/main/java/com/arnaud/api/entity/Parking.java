package com.arnaud.api.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@Entity(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer numberPlace;
    private Integer placeAvailable;
    private Integer totalLocation;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
