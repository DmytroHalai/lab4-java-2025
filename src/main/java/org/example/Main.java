package org.example;

import org.example.people.FireMan;
import org.example.people.Person;
import org.example.people.PoliceMan;
import org.example.road.Road;
import org.example.transport.bus.Bus;
import org.example.transport.car.FireCar;
import org.example.transport.car.PoliceCar;
import org.example.transport.car.Taxi;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Road road = new Road();

        // Автобуси
        Bus bus1 = new Bus(40);
        for (int i = 0; i < 11; i++) bus1.pickUpPassenger(new Person());
        for (int i = 12; i < 23; i++) bus1.pickUpPassenger(new PoliceMan());
        Bus bus2 = new Bus(30);
        for (int i = 0; i < 15; i++) bus2.pickUpPassenger(new FireMan());

        // Автомобілі
        Taxi taxi = new Taxi(4);
        taxi.pickUpPassenger(new Person());
        taxi.pickUpPassenger(new PoliceMan());
        taxi.pickUpPassenger(new FireMan());
        taxi.pickUpPassenger(new Person());

        FireCar fireCar = new FireCar(3);
        fireCar.pickUpPassenger(new FireMan());
        fireCar.pickUpPassenger(new FireMan());

        PoliceCar policeCar = new PoliceCar(2);
        policeCar.pickUpPassenger(new PoliceMan());

        road.addTransport(bus1);
        road.addTransport(bus2);
        road.addTransport(taxi);
        road.addTransport(fireCar);
        road.addTransport(policeCar);
        road.addTransport(new FireCar(12));

        System.out.println(road);

        System.out.println("Пасажири на дорозі");
        road.getTransports()
                .forEach(transport -> {
                    String transportName = transport.getClass().getSimpleName();
                    String passengers = Arrays.stream(transport.getPlaces())
                            .filter(Objects::nonNull)
                            .map(Objects::toString)
                            .collect(Collectors.joining(", "));

                    System.out.printf("%s: [%s]%n", transportName, passengers);
                });
        // 25 (bus1) + 15 (bus2) + 2 (taxi) + 2 (fire) + 1 (police) = 45
    }
}