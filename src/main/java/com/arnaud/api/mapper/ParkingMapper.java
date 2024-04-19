package com.arnaud.api.mapper;

import com.arnaud.api.dto.CarDto;
import com.arnaud.api.dto.ParkingDto;
import com.arnaud.api.entity.Car;
import com.arnaud.api.entity.Parking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParkingMapper {
    ParkingMapper INSTANCE = Mappers.getMapper(ParkingMapper.class);
    ParkingDto parkingToParkingDto(Parking parking);
    Parking parkingDtoToParking(ParkingDto parkingDto);

}
