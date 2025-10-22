// FireCar.java
package org.example.transport.car;

import org.example.people.FireMan;
import org.example.transport.Transport;

public class FireCar extends Car<FireMan> {
    public FireCar(int capacity) {
        super(capacity);
    }
}