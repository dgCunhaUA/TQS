package com.lab.cars.demo;

import com.lab.cars.demo.entities.Car;
import com.lab.cars.demo.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindByCarId_thenReturnCar() {
        Car newCar = new Car("Tesla", "ModelS");
        entityManager.persistAndFlush(newCar);

        Car car_find = carRepository.findByCarId(newCar.getId());
        assertThat( car_find ).isEqualTo(newCar);
    }

    @Test
    public void whenFindAll_thenReturnCars() {
        Car newCar = new Car("tesla", "modelX");
        Car newCar2 = new Car("tesla", "modelS");

        entityManager.persistAndFlush(newCar);
        entityManager.persistAndFlush(newCar2);

        Car find_car = carRepository.findByCarId(newCar.getId());
        assertThat( find_car ).isEqualTo(newCar);

        Car find_car2 = carRepository.findByCarId(newCar2.getId());
        assertThat( find_car2 ).isEqualTo(newCar2);

        List<Car> allCars_find = Arrays.asList(find_car, find_car2);
        List<Car> allCars = carRepository.findAll();

        assertThat( allCars ).isEqualTo(allCars_find);
    }
}
