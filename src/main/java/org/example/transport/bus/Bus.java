// Bus.java
package org.example.transport.bus;

import org.example.people.Person;
import org.example.transport.Transport;

public class Bus extends Transport<Person> {
    public Bus(int capacity) {
        super(capacity);
    }
}