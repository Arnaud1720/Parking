package com.arnaud.api.controller;

import com.arnaud.api.dto.CarDto;
import com.arnaud.api.entity.Car;
import com.arnaud.api.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/")
    public ResponseEntity<CarDto> save(@RequestBody Car car) {
        CarDto savedCarDto = carService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarDto> findById(@PathVariable int id) {
        CarDto carDto = carService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(carDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
