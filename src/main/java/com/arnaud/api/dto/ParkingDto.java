package com.arnaud.api.dto;

import com.arnaud.api.entity.Address;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ParkingDto {

    private Integer id;
    private String name;
    private Integer numberPlace;
    private Integer placeAvailable;
    private Integer totalLocation;
    private Address address;
}
