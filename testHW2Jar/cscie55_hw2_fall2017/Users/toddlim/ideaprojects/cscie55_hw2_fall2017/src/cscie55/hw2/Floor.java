package cscie55.hw2;

/**
 * IMPLEMENTATION
 * CLASS: FLOOR
 *
 *
 * This is a new class, representing one of the floors that the Elevator can visit. It should have the following public
 * methods:
 *
 * -int passengersWaiting(): Returns the number of passengers on the Floor who are waiting for the Elevator.
 * -void waitForElevator() : Called when a passenger on the Floor wants to wait for the Elevator. Calling this should
 *  cause the Elevator to stop the next time it moves to the Floor. NB:For this homework assume that passengers waiting
 *  for the Elevator on floors 2 and above should all be boarded as going to the first floor. (We'll drop this
 *  assumption in the next homework.)
 * -Floor(Building building, int floorNumber): The Floor constructor.
 *
 * You will need to decide what fields the Floor class should have. Note that to meet the first two requirements a
 * Floor object must retain a piece of state that records the number of passengers it has waiting for an Elevator.
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw2-fall2017.html
 * Last Accessed: September 20, 2017 @ 15:50 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

public class Floor {

    //private final field to record the building the elevator is in
    private final Building building;

    //private final field to record the floor number
    private final int floorNumber;

    //public int field storing the number of waiting passengers; it is set to 0
    public int waitingPassengers = 0;

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
     * METHOD: PASSENGERSWAITING
     *
     * @return the number of passengers waiting on that floor for the elevator
     */
    public int passengersWaiting() {
        return waitingPassengers;
    }

    /**
     * METHOD: WAITFORELEVATOR
     *
     * -Called when a passenger on the Floor wants to wait for the Elevator. Calling this should cause the Elevator
     *  to stop the next time it moves to the Floor.
     */
    public void waitForElevator() {
        waitingPassengers++;
    }

}
