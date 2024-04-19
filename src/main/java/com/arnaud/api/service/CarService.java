package com.arnaud.api.service;

import com.arnaud.api.dto.CarDto;
import com.arnaud.api.entity.Car;
import com.arnaud.api.entity.TypeCar;
import com.arnaud.api.exception.CarErrorException;
import com.arnaud.api.mapper.CarMapper;
import com.arnaud.api.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService  {

    private final CarRepository carRepository;
    private final CarMapper carMapper = CarMapper.INSTANCE;




    public CarDto save(Car car) throws CarErrorException {
        // crée un flux de TypeCar et map en flux de nom
        String validValues = Arrays.stream(TypeCar.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        // Étape 1: car est l'objet Car d'entrée que vous voulez sauvegarder et convertir en CarDto.
        // Vérifie si le typeCarEnum est une valeur valide de l'énumération TypeCar
        if ( car.getTypeCarEnum()==null || !car.getTypeCarEnum().equals(TypeCar.ELECTRIC_20_KW) && !car.getTypeCarEnum().equals(TypeCar.ELECTRIC_50_KW)) {
            // Lancez une exception ou gérez le cas où typeCarEnum n'est ni ELECTRIC_20_KW ni ELECTRIC_50_KW
            throw new CarErrorException("le type de voiture n'ai pas conforme vous devez selectionné une des ces deux valeur"+ validValues );
        }

        // Étape 2: Sauvegarde de car dans la base de données. savedCar est maintenant l'objet Car persisté.
        Car savedCar = carRepository.save(car);

        // Étape 3: Conversion de savedCar en CarDto.
        CarDto carDto = carMapper.carToCarDto(savedCar);

        // Étape 4: carDto est l'objet CarDto converti, qui est renvoyé.
        return carDto;
    }
    public List<CarDto> findAll() {
        return carRepository.findAll().stream().map(carMapper::carToCarDto).collect(Collectors.toList());
    }

    public CarDto findById(Integer id) {
        CarDto carDto = carRepository.findById(id).map(carMapper::carToCarDto).orElse(null);
        boolean carExists = carRepository.existsById(id);
        if(!carExists) {
            throw new CarErrorException("le id de voiture n'existe pas");
        }
        return carDto;
    }

    public void delete(Integer id) {
         carRepository.deleteById(id);
    }
}
