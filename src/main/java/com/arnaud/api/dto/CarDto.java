package com.arnaud.api.dto;

import com.arnaud.api.entity.TypeCar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private Integer id;
    private TypeCar typeCarEnum;
}
