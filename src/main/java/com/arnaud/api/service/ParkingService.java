package com.arnaud.api.service;

import com.arnaud.api.dto.ParkingDto;
import com.arnaud.api.entity.Parking;
import com.arnaud.api.entity.Role;
import com.arnaud.api.entity.Users;
import com.arnaud.api.exception.AddressException;
import com.arnaud.api.exception.NotPermitedException;
import com.arnaud.api.exception.ParkingNotFoundException;
import com.arnaud.api.mapper.ParkingMapper;
import com.arnaud.api.repository.ParkingRepository;
import com.arnaud.api.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    private final ParkingMapper parkingMapper = ParkingMapper.INSTANCE;

    public ParkingDto save(Parking parking) throws AddressException {
        // Vérifier si l'adresse est présente
        if (parking.getAddress() == null || parking.getName().length() > 12) {
            throw new AddressException("L'adresse est obligatoire pour enregistrer un parking." + parking.getAddress() + "ou le nom dépace le nombre de caractère max" + parking.getName());
        }

        Parking parkingSaved = parkingRepository.save(parking);
        ParkingDto parkingDto = parkingMapper.parkingToParkingDto(parkingSaved);


        return parkingDto;
    }

    public List<ParkingDto> findAll() {
        return parkingRepository.findAll().stream().map(parkingMapper::parkingToParkingDto).collect(Collectors.toList());
    }

    public ParkingDto findById(Integer id) throws ParkingNotFoundException {
        boolean parkingExists = parkingRepository.existsById(id);
        if (!parkingExists) {
            throw new ParkingNotFoundException("l'id du parking n'existe pas " + id);
        }
        return parkingRepository.findById(id).map(parkingMapper::parkingToParkingDto).orElse(null);
    }

    public void DeleteById(Integer id) throws ParkingNotFoundException {
        boolean parkingExists = parkingRepository.existsById(id);
        if (!parkingExists) {
            throw new ParkingNotFoundException("l'id du parking n'existe pas " + id);
        }
        parkingRepository.deleteById(id);
    }

    public List<ParkingDto> backup(UUID uuid) throws ParkingNotFoundException {
        List<Parking> parkings = parkingRepository.findAll();
        Users users = userRepository.findById(uuid).orElseThrow(null);
        if (users.getRole()!= Role.ADMIN) {
            throw new NotPermitedException("pas admin");
        }
        // Spécifier le chemin du répertoire
        Path directoryPath = Paths.get(System.getProperty("user.home"), "test");
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            // Créer le fichier et écrire la liste des parkings avec pretty print
            Path filePath = directoryPath.resolve("parkings.json");
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            String parkingJson = writer.writeValueAsString(parkings);
            Files.write(filePath, parkingJson.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parkingRepository.findAll().stream().map(parkingMapper::parkingToParkingDto).collect(Collectors.toList());
    }
}

