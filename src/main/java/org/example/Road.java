package org.example;

import org.example.transport.Transport;
import org.example.transport.car.Car;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Transport> carsInRoad = new ArrayList<>();
    public int getCountOfHumans(){
        int count = 0;
        for (Transport car : carsInRoad) {
            count += car.getCountOfHumans();
        }
        return count;
    }
    public void addCarToRoad(Car car) {
        carsInRoad.add(car);
    }
}
