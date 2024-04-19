package com.arnaud.api.mapper;

import com.arnaud.api.dto.UsersDto;
import com.arnaud.api.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);
    UsersDto userToUserDto(Users users); // converti une User en UserDto
    Users userDtoToUser(UsersDto usersDto); // converti un UserDto en User
}
