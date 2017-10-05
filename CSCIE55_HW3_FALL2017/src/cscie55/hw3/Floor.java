package cscie55.hw3;

import java.util.*;

/**
 * IMPLEMENTATION
 * CLASS: FLOOR
 *
 *
 * Floor should be modified as follows:
 * -The int passengersWaiting() is no longer needed and should be removed (or made non-public, if you need it for
 *  your implementation).
 * -The waitForElevator method needs to be modified. Change it to void waitForElevator(Passenger passenger,
 *  int destinationFloor). This allows the Floor to know which Passenger is waiting for the Elevator. And by comparing
 *  destinationFloor to the floor number, the Floor class knows whether the Passenger is waiting to go up or down.
 * -Add boolean isResident(Passenger passenger) which returns true if the passenger is resident on the Floor, (i.e.,
 *  not waiting to go up and not waiting to go down), false otherwise.
 * -Add void enterGroundFloor(Passenger passenger). This method adds a passenger to the Floor's residents.
 *
 * You will need to decide what fields the Floor class should have.
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw3-fall2017.html
 * Last Accessed: September 30, 2017 @ 19:35 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

public class Floor {

    //private final field to record the building the elevator is in
    private final Building building;

    //private final field to record the floor number
    private final int floorNumber;

    /**
     Floor needs three collections of Passengers:
       Passengers who are resident on the Floor, not waiting for the elevator;
       Passengers who are waiting for an Elevator going up;
       Passengers who are waiting for an Elevator going down.
     A Passenger on a Floor will be in exactly one of these collections.
     */
    Collection<Passenger> residentPassengers = new ArrayList <Passenger>();
    Collection<Passenger> goingUpPassengers = new ArrayList<Passenger>();
    Collection<Passenger> goingDownPassengers = new ArrayList<Passenger>();


    /**
     * CONSTRUCTOR: FLOOR
     *
     * -references current building
     * -references current floor number
     *
     * @param building
     * @param floorNumber
     */
    public Floor(Building building, int floorNumber) {
        this.building = building;
        this.floorNumber = floorNumber;
    }

    /**
     * METHOD: ISRESIDENT
     *
     * -returns true if the passenger is resident on the Floor, (i.e.,
     *  not waiting to go up and not waiting to go down), false otherwise
     *
     * @param passenger
     * @return
     */
    public boolean isResident(Passenger passenger) {
        return (residentPassengers.contains(passenger));
    }

    /**
     * METHOD: ENTERGROUNDFLOOR
     *
     * -adds a passenger to the Floor's residents
     *
     * @param passenger
     */
    public void enterGroundFloor(Passenger passenger) {
        if(floorNumber == Building.GROUND_FLOOR) {
            residentPassengers.add(passenger);
        } else {
            throw new RuntimeException("Exception: enterGroundFloor is only called on GROUND_FLOOR");
        }
    }
    /**
     * METHOD: WAITFORELEVATOR
     *
     * -The waitForElevator method needs to be modified. Change it to void waitForElevator(Passenger passenger,
     *  int destinationFloor). This allows the Floor to know which Passenger is waiting for the Elevator. By comparing
     *  destinationFloor to the floor number, the Floor class knows whether the Passenger is waiting to go up or down.
     */
    public void waitForElevator(Passenger passenger, int destinationFloor) {
        floorCheck(destinationFloor);

        passenger.waitForElevator(destinationFloor);

        if(destinationFloor < floorNumber) {
            goingDownPassengers.add(passenger);
        } else if (destinationFloor > floorNumber) {
            goingUpPassengers.add(passenger);
        } else {
            throw new IllegalArgumentException("Exception: Passenger needs to pay attention to floor");
        }
        residentPassengers.remove(passenger);
    }

    /**
     * METHOD: FLOORCHECK
     *
     * -provides exception handling for passengers trying to go to a floor lower than the minimum or higher than the
     *  maximum in a building
     *
     * @param floor
     * @return
     */
    private boolean floorCheck(int floor) {
        if (Building.GROUND_FLOOR <= floor && floor <= Building.FLOORS) {
            return true;
        } else {
            throw new IllegalArgumentException("Exception: Floor is not bounded by min and max floors of Building");
        }
    }

}