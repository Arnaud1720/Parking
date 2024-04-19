package com.arnaud.api.mapper;

import com.arnaud.api.dto.CarDto;
import com.arnaud.api.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    CarDto carToCarDto(Car car); // converti un Car en CarDto
    Car carDtoToCar(CarDto carDto); // converti un CarDto en Car
}
