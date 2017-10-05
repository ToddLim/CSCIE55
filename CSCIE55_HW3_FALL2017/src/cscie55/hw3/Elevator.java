package cscie55.hw3;

import java.util.*;

/**
 * IMPLEMENTATION
 * CLASS: ELEVATOR
 *
 *
 * Starting with Elevator from Homework 2, add the following public methods:
 * -boolean goingUp(): Return true if the elevator is going up, false otherwise.
 * -boolean goingDown(): Return true if the elevator is going down, false otherwise.
 * -Change the passengers() method to return a Set<Passenger> containing all of the Passengers on the elevator. (It
 *  returned a passenger count in Homework 2.) Note: Set<Passenger> as described here is an abstract class. You must
 *  choose some species of Set to actually return.
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw3-fall2017.html
 * Last Accessed: September 30, 2017 @ 19:35 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */
public class Elevator {

    //final static field that stores the number of passengers that the Elevator can accommodate; it is set to 10
    public static final int CAPACITY = 10;

    //final field that instantiates this building
    private final Building building;

    //set int value of current floor to 1
    private int currentFloor = 1;

    //set int value direction to 1 (arbitrary positive number)
    private int direction = 1;

    //set records each floor's passengers who plan to get off at that floor (key: floor level, value: passenger)
    private Map<Integer, ArrayList<Passenger>> occupants = new HashMap<Integer, ArrayList<Passenger>>();

    /**
     * CONSTRUCTOR: ELEVATOR
     *
     * -records the building in a new field of Elevator
     *
     * @param building instance
     */
    public Elevator(Building building) {
        this.building = building;
        for(int i = Building.GROUND_FLOOR; i <= Building.FLOORS; i++) {
            occupants.put(i, new ArrayList<Passenger>());
        }
    }

    /**
     * METHOD: PASSENGERS
     *
     * -change the passengers() method to return a Set<Passenger> containing all of the Passengers on the elevator. (It
     *  returned a passenger count in Homework 2.) Note: Set<Passenger> as described here is an abstract class. You must
     *  choose some species of Set to actually return.
     *
     * @return number of passengers in elevator in current capacity
     */
    public Set<Passenger> passengers() {
        return currentCapacity();
    }

    /**
     * METHOD: CURRENTCAPACITY
     *
     * @return new hash set of passengers
     */
    private Set<Passenger> currentCapacity() {
        Set<Passenger> returnSet = new HashSet<Passenger>();
        for(int floor: occupants.keySet()) {
            for (Passenger passenger : this.occupants.get(floor)) {
                returnSet.add(passenger);
            }
        }
        return returnSet;
    }

    /**
     * METHOD: CURRENTFLOOR
     *
     * @return the elevator's current floor number
     */
    public int currentFloor() {
        return this.currentFloor;
    }

    /**
     * METHOD: GOINGUP
     *
     * -Return true if the elevator is going up, false otherwise
     *
     * @return direction as 1
     */

    public boolean goingUp() {
        return (this.direction == 1);
    }

    /**
     * METHOD: GOINGDOWN
     *
     * -Return true if the elevator is going up, false otherwise
     *
     * @return direction as -1
     */
    public boolean goingDown() {
        return (this.direction == -1);
    }

    /**
     * METHOD: MOVE
     *
     * -change direction when current floor is equal to minimum or maximum floor
     * -invoke elevatorStop when there are passenger requests on the current floor or when there are passengers
     *  waiting on the current floor of the building
     */
    public void move() {
        currentFloor = currentFloor + this.direction;
        if (Building.GROUND_FLOOR == this.currentFloor || this.currentFloor == Building.FLOORS) {
            this.direction *= -1;
        }

        Floor floorObject = this.building.floor(currentFloor);
        if (this.occupants.get(currentFloor).size() > 0) {
            this.elevatorStop(currentFloor, true, this.occupants.get(currentFloor));
        }
        if (this.goingUp() && floorObject.goingUpPassengers.size() > 0) {
            this.elevatorStop(currentFloor, false, floorObject.goingUpPassengers);
        }
        else if (this.goingDown() && floorObject.goingDownPassengers.size() > 0) {
            this.elevatorStop(currentFloor, false, floorObject.goingDownPassengers);
        }
    }

    /**
     * METHOD: ELEVATORSTOP
     *
     * -determines when to unload passengers on a current floor
     * -utilizes try-catch blocks and throws keyword for ElevatorFullException method when adding new passenger exceeds
     *  current capacity
     *
     * @param floor
     * @param unload
     * @param passengers
     */
    private void elevatorStop(int floor, boolean unload, Collection<Passenger> passengers) {
        if (unload) {
            for (Passenger passenger : this.occupants.get(floor)) {
                passenger.arrive();
                this.building.floor(floor).residentPassengers.add(passenger);
            }
            this.occupants.put(floor, new ArrayList<Passenger>());
        } else {
            Collection<Passenger> recentlyBoarded = new ArrayList<Passenger>();
            try {
                for (Passenger passenger : passengers) {
                    if (this.currentCapacity().size() == Elevator.CAPACITY) {
                        throw new ElevatorFullException();
                    }

                    passenger.boardElevator();
                    this.occupants.get(passenger.destinationFloor()).add(passenger);
                    recentlyBoarded.add(passenger);
                }
            } catch (ElevatorFullException e) {}
            for(Passenger passenger : recentlyBoarded) {
                passengers.remove(passenger);
            }
            recentlyBoarded = null;
        }
    }

}