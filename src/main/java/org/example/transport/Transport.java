package org.example.transport;

import org.example.people.Person;

import java.util.HashMap;
import java.util.Map;

public class Transport {
    protected Map<Integer, Person> peopleIn = new HashMap<>();

    public int getCountOfHumans() {
        return peopleIn.size();
    }
}
