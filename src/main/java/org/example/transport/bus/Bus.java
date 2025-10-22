package org.example.transport.bus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.transport.Transport;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class Bus extends Transport {
    private final int passengerCapacity;
    private final Map<Integer, Boolean> seatAvailability;
}
