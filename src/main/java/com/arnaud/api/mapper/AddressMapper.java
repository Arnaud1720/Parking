package com.arnaud.api.mapper;

import com.arnaud.api.dto.AddressDto;
import com.arnaud.api.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto carToCarDto(Address address); // converti une Address en AddressDto
    Address carDtoToCar(AddressDto addressDto); // converti un AddressDto en Address
}
