package cscie55.hw2;

/**
 * IMPLEMENTATION
 * CLASS: ELEVATOR
 *
 *
 * Starting with Elevator from homework 1, add the following public methods and fields*:
 *
 * -int currentFloor() : Return the Elevator's current floor number. I.e., this is the number of the floor reached by
 *  the last call to Elevator.move().
 * -int passengers(): Return the number of passengers currently on the Elevator.
 * -void boardPassenger(int destinationFloorNumber) throws ElevatorFullException: Board a passenger who wants to ride
 *  to the indicated floor. Note that this method boards a single passenger and may throw an ElevatorFullException.
 * -Elevator.CAPACITY is a final static field that stores the number of passengers that the Elevator can accommodate.
 *  Set it to 10.
 *
 * [Note that the term 'field' is used here as synonymous with the terms "instance variable" and "state variable".]
 *
 * Also, replace the Elevator() constructor by Elevator(Building building). (Hint: You may find it useful to record
 * the building in a new field of Elevator.)
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw2-fall2017.html
 * Last Accessed: September 20, 2017 @ 15:50 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */
public class Elevator {

    //final static field that stores the number of passengers that the Elevator can accommodate; it is set to 10
    public static final int CAPACITY = 10;

    //final field that instantiates this building
    private final Building building;

    //static final field that sets the minimum floor to the ground floor
    private static final int MIN_FLOOR = Building.GROUND_FLOOR;

    //static final field that sets the maximum floor to the total number of floors in the building
    private static final int MAX_FLOOR = Building.FLOORS;

    //set current floor to 1
    private int currentFloor = 1;

    //set direction to 1 (arbitrary positive number)
    private int direction = 1;

    //declare and instantiate an array for object: 'passengerRequests'
    private int [] passengerRequests = new int [Elevator.MAX_FLOOR + 1];

    /**
     * CONSTRUCTOR: ELEVATOR
     *
     * -records the building in a new field of Elevator
     *
     * @param building
     */
    public Elevator(Building building) {
        this.building = building;
    }

    /**
     * METHOD: PASSENGERS
     *
     * @return the number of passengers that the Elevator can accommodate
     */
    public int passengers() {
        return currentCapacity();
    }

    /**
     * METHOD: CURRENTFLOOR
     *
     * @return the elevator's current floor number
     */
    public int currentFloor() {
        return currentFloor;
    }

    /**
     * METHOD: MOVE
     *
     * -change direction when current floor is equal to minimum or maximum floor
     * -invoke elevatorStop when there are passenger requests on the current floor or when there are passengers
     *  waiting on the current floor of the building
     */
    public void move() {

        currentFloor = currentFloor + (1 * this.direction);

        if (Elevator.MIN_FLOOR == currentFloor || currentFloor == Elevator.MAX_FLOOR) {
            this.direction *= -1;
        }

        if (passengerRequests[currentFloor] > 0) {
            elevatorStop(currentFloor, true);
        }

        if (building.floor(currentFloor).passengersWaiting() > 0) {
            elevatorStop(currentFloor, false);
        }
    }

    /**
     * METHOD: BOARDPASSENGER
     *
     * -Board a passenger who wants to ride to the indicated floor. Note that this method boards a single passenger
     *  and may throw an ElevatorFullException
     *
     * @param destinationFloorNumber is the floor that a passenger will disembark
     * @throws ElevatorFullException if destination floor is verified and current capacity is greater than the
     *                               capacity of the elevator minus 1; else print error message
     */
    public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException {
        if (floorCheck(destinationFloorNumber)) {
            if (currentCapacity() <= (Elevator.CAPACITY - 1)) {
                passengerRequests[destinationFloorNumber]++;
            } else {
                throw new ElevatorFullException();
            }
        } else {
            System.out.println("You are at the wrong floor.");
        }
    }

    /**
     * METHOD: CURRENTCAPACITY
     *
     * @return the capacity for the elevator after looping
     */
    private int currentCapacity() {

        int capacity = 0;
        for (int i = Elevator.MIN_FLOOR; i <= Elevator.MAX_FLOOR; i++) {
            capacity += passengerRequests[i];
        }
        return capacity;
    }

    /**
     * METHOD: FLOORCHECK
     *
     * @param floor is bounded by minimum and maximum floor levels
     * @return boolean value
     */
    private Boolean floorCheck(int floor) {
        if (Elevator.MIN_FLOOR <= floor && floor <= Elevator.MAX_FLOOR) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * METHOD: ELEVATORSTOP
     *
     * -determines when to unload passengers on a current floor
     * -utilizes try for boardPassenger method and catch for ElevatorFullException method in while loop
     *
     * @param floor
     * @param unload
     */
    private void elevatorStop(int floor, Boolean unload) {

        if (unload) {
            passengerRequests[floor] = 0;
        } else {
            while (building.floor(floor).passengersWaiting() > 0) {
                try {
                    boardPassenger(1);
                } catch (ElevatorFullException e) {
                    break;
                }
                building.floor(floor).waitingPassengers--;
            }
        }
    }

    /**
     * METHOD: TOSTRING
     *
     * @return floor, passenger, current floor, and current capacity information
     */
    public String toString() {
            return String.format("Floor %d: " + " %d passengers\n", currentFloor + 1, currentCapacity());
    }
}