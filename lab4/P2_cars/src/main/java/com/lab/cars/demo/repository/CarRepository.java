package com.lab.cars.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lab.cars.demo.entities.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

   Car findByCarId(Long carID);

   List<Car> findAll();

}
