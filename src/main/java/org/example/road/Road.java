package org.example.road;

import org.example.transport.Transport;

import java.util.ArrayList;
import java.util.List;

public class Road {
    private final List<Transport<?>> transports = new ArrayList<>();

    public void addTransport(Transport<?> transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Transport cannot be null");
        }
        transports.add(transport);
    }

    public int getCountOfHumans() {
        int total = 0;
        for (Transport<?> t : transports) {
            total += t.getTakenPlaces();
        }
        return total;
    }

    public List<Transport<?>> getTransports() {
        return new ArrayList<>(transports);
    }

    @Override
    public String toString() {
        return "Road{transports=" + transports.size() + ", totalHumans=" + getCountOfHumans() + "}";
    }
}