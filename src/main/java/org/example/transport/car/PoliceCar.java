// PoliceCar.java
package org.example.transport.car;

import org.example.people.PoliceMan;
import org.example.transport.Transport;

public class PoliceCar extends Car<PoliceMan> {
    public PoliceCar(int capacity) {
        super(capacity);
    }
}