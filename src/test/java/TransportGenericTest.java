import org.example.people.FireMan;
import org.example.people.Person;
import org.example.people.PoliceMan;
import org.example.road.Road;
import org.example.transport.bus.Bus;
import org.example.transport.car.FireCar;
import org.example.transport.car.PoliceCar;
import org.example.transport.car.Taxi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransportGenericTest {

    @Test
    void testBusAcceptsOnlyPersonSubtypes() {
        Bus bus = new Bus(5);

        Person person = new Person();
        FireMan fireMan = new FireMan();
        PoliceMan policeMan = new PoliceMan();

        bus.pickUpPassenger(person);
        bus.pickUpPassenger(fireMan);
        bus.pickUpPassenger(policeMan);

        Assertions.assertEquals(3, bus.getTakenPlaces());
    }

    @Test
    void testFireCarAcceptsOnlyFireMan() {
        FireCar fireCar = new FireCar(3);

        FireMan fireMan = new FireMan();

        fireCar.pickUpPassenger(fireMan);

        // Компілятор НЕ дозволить:
        // fireCar.pickUpPassenger(new Person()); Помилка компіляції
        // fireCar.pickUpPassenger(new PoliceMan()); Помилка компіляції

        Assertions.assertEquals(1, fireCar.getTakenPlaces());
    }

    @Test
    void testPoliceCarAcceptsOnlyPoliceMan() {
        PoliceCar policeCar = new PoliceCar(2);

        PoliceMan policeMan = new PoliceMan();
        policeCar.pickUpPassenger(policeMan);

        Assertions.assertEquals(1, policeCar.getTakenPlaces());
    }

    @Test
    void testTaxiAcceptsAnyPerson() {
        Taxi taxi = new Taxi(4);

        Person person = new Person();
        FireMan fireMan = new FireMan();
        PoliceMan policeMan = new PoliceMan();

        taxi.pickUpPassenger(person);
        taxi.pickUpPassenger(fireMan);
        taxi.pickUpPassenger(policeMan);

        Assertions.assertEquals(3, taxi.getTakenPlaces());
    }

    @Test
    void testTransportCapacityAndFreePlaces() {
        Bus bus = new Bus(4);
        Person p1 = new Person();
        Person p2 = new Person();

        Assertions.assertEquals(0, bus.getTakenPlaces());
        Assertions.assertEquals(4, bus.getFreePlaces());

        bus.pickUpPassenger(p1);
        bus.pickUpPassenger(p2);

        Assertions.assertEquals(2, bus.getTakenPlaces());
        Assertions.assertEquals(2, bus.getFreePlaces());
    }

    @Test
    void testPickUpPassengerWhenFullThrowsException() {
        Bus bus = new Bus(1);
        bus.pickUpPassenger(new Person());

        assertThrows(IllegalStateException.class, () -> {
            bus.pickUpPassenger(new Person());
        });
    }

    @Test
    void testDropOffPassengerRemovesCorrectly() {
        Taxi taxi = new Taxi(2);
        Person passenger = new Person();

        taxi.pickUpPassenger(passenger);
        Assertions.assertEquals(1, taxi.getTakenPlaces());

        taxi.dropOffPassenger(passenger);
        Assertions.assertEquals(0, taxi.getTakenPlaces());
    }

    @Test
    void testDropOffNonExistentPassengerThrowsException() {
        Taxi taxi = new Taxi(2);
        Person passenger = new Person();

        assertThrows(IllegalStateException.class, () -> {
            taxi.dropOffPassenger(passenger);
        });
    }

    @Test
    void testRoadCountsHumansCorrectly() {
        Road road = new Road();

        Bus bus = new Bus(3);
        bus.pickUpPassenger(new Person());
        bus.pickUpPassenger(new FireMan());

        FireCar fireCar = new FireCar(2);
        fireCar.pickUpPassenger(new FireMan());

        PoliceCar policeCar = new PoliceCar(1);
        policeCar.pickUpPassenger(new PoliceMan());

        road.addTransport(bus);
        road.addTransport(fireCar);
        road.addTransport(policeCar);

        Assertions.assertEquals(4, road.getCountOfHumans()); // 2 в bus + 1 в fireCar + 1 в policeCar
    }

    @Test
    void testToStringOverrideInAllClasses() {
        Person person = new Person();
        FireMan fireMan = new FireMan();
        PoliceMan policeMan = new PoliceMan();

        Assertions.assertEquals("Person", person.toString());
        Assertions.assertEquals("Fireman", fireMan.toString());
        Assertions.assertEquals("PoliceMan", policeMan.toString());

        Bus bus = new Bus(2);
        bus.pickUpPassenger(person);
        String busStr = bus.toString();
        assertTrue(busStr.startsWith("Bus{places=[Person"));
    }

    @Test
    void testNullChecksInTransport() {
        Bus bus = new Bus(1);

        assertThrows(IllegalArgumentException.class, () -> {
            bus.pickUpPassenger(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            bus.dropOffPassenger(null);
        });
    }

    @Test
    void testRoadAddNullTransportThrowsException() {
        Road road = new Road();

        assertThrows(IllegalArgumentException.class, () -> {
            road.addTransport(null);
        });
    }
}