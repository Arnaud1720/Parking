package com.arnaud.api.controller;

import com.arnaud.api.dto.ParkingDto;
import com.arnaud.api.entity.Parking;
import com.arnaud.api.service.ParkingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parking")
@AllArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    private final ObjectMapper objectMapper;
    @PostMapping("/")
    public ResponseEntity<ParkingDto> save(@RequestBody Parking parking){
      ParkingDto parkingSave =  parkingService.save(parking);
      return ResponseEntity.ok(parkingSave);
    }
    @GetMapping(value = "/all" )
    public ResponseEntity<List<ParkingDto>> getAll(){
        List<ParkingDto> parkings = parkingService.findAll();
        return ResponseEntity.ok(parkings);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ParkingDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(parkingService.findById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ParkingDto> delete(@PathVariable Integer id){
        parkingService.DeleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/backup/uuid/{uuid}" )
    public ResponseEntity<List<ParkingDto>> getBackupParking(@PathVariable UUID uuid){

        return ResponseEntity.ok(parkingService.backup(uuid));
    }
}
