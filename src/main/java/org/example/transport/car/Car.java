package org.example.transport.car;

import org.example.people.Person;
import org.example.transport.Transport;

public abstract class Car<T extends Person> extends Transport<T> {
    protected Car(int capacity) {
        super(capacity);
    }
}
