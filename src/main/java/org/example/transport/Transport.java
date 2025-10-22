package org.example.transport;

import org.example.people.Person;

import java.util.Arrays;
import java.util.Objects;

public abstract class Transport<T extends Person> {
    protected final T[] places;

    @SuppressWarnings("unchecked")
    protected Transport(int capacity) {
        this.places = (T[]) new Person[capacity];
    }

    protected Transport(T[] places) {
        if (places == null || places.length == 0) {
            throw new IllegalArgumentException("Places array cannot be null or empty");
        }
        this.places = places.clone();
    }

    public T[] getPlaces() {
        return places;
    }

    public int getMaxPlaces() {
        return places.length;
    }

    public int getTakenPlaces() {
        return (int) Arrays.stream(places)
                .filter(Objects::nonNull)
                .count();
    }

    public int getFreePlaces() {
        return getMaxPlaces() - getTakenPlaces();
    }

    public void pickUpPassenger(T passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }
        if (getFreePlaces() == 0) {
            throw new IllegalStateException("No free places in the transport");
        }
        for (int i = 0; i < places.length; i++) {
            if (places[i] == null) {
                places[i] = passenger;
                return;
            }
        }
    }

    public void dropOffPassenger(T passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }
        for (int i = 0; i < places.length; i++) {
            if (Objects.equals(places[i], passenger)) {
                places[i] = null;
                return;
            }
        }
        throw new IllegalStateException("Passenger is not in this transport");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{places=" + Arrays.toString(places) + "}";
    }
}