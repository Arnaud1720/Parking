package com.arnaud.api.repository;

import com.arnaud.api.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface CarRepository extends JpaRepository<Car,Integer> {
}
