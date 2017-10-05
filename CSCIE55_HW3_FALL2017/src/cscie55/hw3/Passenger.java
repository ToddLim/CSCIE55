package cscie55.hw3;

/**
 * IMPLEMENTATION
 * CLASS: Passenger
 *
 *
 * This is a new class which records a Passenger's current Floor and destination Floor. A Passenger who is resident
 * on a Floor has a current floor number and an undefined destination number. A Passenger who is on an Elevator has
 * a destination number but an undefined current floor number. A Passenger who is waiting for an Elevator has a current
 * floor number (the Floor on which the Passenger is waiting), and a destination floor number.
 *
 * Passenger has the following public methods:
 *
 * -Passenger(int id) constructor. [The id may be useful for debugging.]
 * -int currentFloor(): The Passenger's current floor.
 * -int destinationFloor(): The Passenger's destination floor.
 * -void waitForElevator(int newDestinationFloor): Sets the Passenger's destination floor.
 * -void boardElevator(): Sets the Passenger's current floor number to be undefined.
 * -void arrive(): The Passenger is on an Elevator and arrives at the destination. Copy the value of the destination
 *  floor number to the current floor number, and set the destination floor number to be undefined.
 * -As usual, define a toString method to aid in debugging.
 *
 * A Passenger starts out with current floor number = 1 (the ground floor) and the destination floor number undefined.
 *
 * A word of advice: Be precise about undefined values. Define a static final int UNDEFINED_FLOOR to be -1, for
 * example, and assign this as the value of the current or destination floor numbers as appropriate. If you do this
 * correctly, (and print both current and destination floor numbers in the toString method), it will simplify debugging.
 * E.g., if you see a Passenger in an Elevator collection, and the destination floor is UNDEFINED_FLOOR, then something
 * has gone wrong.
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw3-fall2017.html
 * Last Accessed: September 30, 2017 @ 19:35 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

public class Passenger {

    //static final field storing a value for undefined floor; it is set to -1
    private static final int UNDEFINED_FLOOR = -1;

    //set current floor to undefined floor
    private int currentFloor = UNDEFINED_FLOOR;

    //set desired floor to undefined floor
    private int desiredFloor = UNDEFINED_FLOOR;

    //final field storing ID for constructor
    private final int ID;

    /**
     * CONSTRUCTOR: PASSENGER
     *
     * -A Passenger starts out with current floor number = 1 (the ground floor) and the destination floor number
     *  undefined.
     * -the id may be useful for debugging
     *
     * @param id for passenger
     */
    public Passenger(int id) {
        currentFloor = Building.GROUND_FLOOR;
        ID = id;
    }

    /**
     * METHOD: CURRENTFLOOR
     *
     * @return currentFloor int value
     */
    public int currentFloor() {
        return currentFloor;
    }

    /**
     * METHOD: DESTINATIONFLOOR
     *
     * @return destinationFloor int value
     */
    public int destinationFloor() {
        return desiredFloor;
    }

    /**
     * METHOD: WAITFORELEVATOR
     *
     * -Sets the Passenger's destination floor.
     *
     * @param newDestinationFloor for passenger
     */
    public void waitForElevator(int newDestinationFloor) {
        floorCheck(newDestinationFloor);
        this.desiredFloor = newDestinationFloor;
    }

    /**
     * METHOD: BOARDELEVATOR
     *
     * -Sets the Passenger's current floor number to be undefined
     */
    public void boardElevator() {
        currentFloor = UNDEFINED_FLOOR;
    }

    /**
     * METHOD: ARRIVE
     *
     * -The Passenger is on an Elevator and arrives at the destination. Copy the value of the destination
     *  floor number to the current floor number, and set the destination floor number to be undefined.
     */
    public void arrive() {
        currentFloor = desiredFloor;
        desiredFloor = UNDEFINED_FLOOR;
    }

    /**
     * METHOD:TOSTRING
     *
     * @return information about passenger's current floor and desired floor
     */
    public String toString() {
        String string = "PASSENGER: current: " + currentFloor + " desired: " + desiredFloor;
        return string;
    }

    /**
     * METHOD: FLOORCHECK
     *
     * -provide exception handling for floors below minimum and above maximum in building
     *
     * @param floor in building
     * @return true for boolean, else throw exception
     */
    private boolean floorCheck(int floor) {
        if(Building.GROUND_FLOOR <= floor && floor <= Building.FLOORS) {
            return true;
        } else {
            throw new IllegalArgumentException("Exception: Floor is not bounded by min and max floors of Building");
        }
    }

}