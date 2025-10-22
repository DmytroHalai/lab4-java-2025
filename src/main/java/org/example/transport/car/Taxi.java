package org.example.transport.car;

import org.example.people.Person;
import org.example.transport.Transport;

public class Taxi extends Car<Person> {
    public Taxi(int capacity) {
        super(capacity);
    }
}