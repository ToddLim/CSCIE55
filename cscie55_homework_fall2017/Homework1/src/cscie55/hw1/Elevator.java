/**
 * An Elevator class has been created in the "cscie55.hw1.elevator" package.
 */

package cscie55.hw1;

/**
 * IMPLEMENTATION
 * CLASS: ELEVATOR
 *
 *
 * SPECIFICATION OF REQUIREMENTS
 *
 * Create an Elevator class in the package cscie55.hw1.Elevator. The class should have a no-argument constructor that
 * sets up the Elevator's state.
 *
 * Define a constant, (i.e., a static final field), for the number of floors in the building, and set it to 7. Use it
 * where appropriate.
 *
 * Define fields for tracking the Elevator's current floor, the direction of travel.
 *
 * Define an array-valued field for tracking, for each floor, the number of passengers destined for that floor.
 *
 * Define a move() method which, when called, modifies the Elevator's state, (i.e., updates the fields appropriately):
 * -Increments/decrements the current floor, i.e. the Elevator moves one floor at a time.
 * -Modifies the direction of travel, if the ground floor or top floor has been reached.
 * -Clears the array entry tracking the number of passengers destined for the floor that the Elevator has just
 * arrived at.
 * -Prints out the status of the Elevator [see toString() method below]
 *
 * Define a boardPassenger(int destinationFloor) method which adds to the Elevator a passenger destined for the
 * indicated floor.
 * Define a toString() method to aid in debugging and testing. The String returned by toString() should indicate the
 * number of passengers on board, and the current floor.
 *
 * This list defines requirements of the Elevator class. Your submission should therefore define all the fields and
 * methods specified above, with appropriate access modes (private, public, etc.) You are free to provide additional
 * fields and methods that you may find to be useful.
 *
 * In this homework, the only reason to stop at a floor is that a passenger wants to get off there. However, in
 * future homeworks, passengers on a floor who want to leave the building (for example) will summon the Elevator,
 * and then the Elevator must stop there, even if nobody already on the Elevator wants to stop there.
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw1-fall2017.html
 * Accessed on September 8, 2017
 *
 * @author Todd Lim
 * @version 1.0
 */

public class Elevator {

    //Defines the public constant for number of floors in the building as 7
    public static final int totalFloors = 7;

    //Defines the private array-valued field for each floor, the number of passengers destined for that floor
    private int [] destinationRequests = new int [totalFloors];

    //Defines the private field for tracking the Elevator's current floor
    private int currentFloor;

    //Defines the private field for counting the number of passengers
    private int passengerCount;

    //Defines the private field for direction of travel; by default, it's ascending
    private boolean moveUp;

    /**
     * REQUIRED CONSTRUCTOR: ELEVATOR
     *
     * Description:
     * public Elevator() is the no-argument constructor that sets up the elevator's state, which in this case involves
     * setting currentFloor = 0 and passengerCount = 0.
     */
    public Elevator() {
        currentFloor = 0;
        passengerCount = 0;
    }

    /**
     * REQUIRED METHOD: MOVE
     *
     * Description:
     * move() method moves elevator up or down by incrementing or decrementing currentFloor. It uses if-elseif-if-else
     * statements logic respectively for stopElevator() method, floorSnapshot() method, and changeDirection() method.
     */
    public void move() {
        if(destinationRequests[currentFloor] > 0) {
            stopElevator();
        }
        else if(destinationRequests[currentFloor] <= 0) {
            floorSnapshot();
        }
        if(moveUp) {
            if(currentFloor == (totalFloors - 1)) {
                changeDirection();
            }
            currentFloor++;
        }
        else {
            if(currentFloor == 0) {
                changeDirection();
            }
            else {
                currentFloor--;
            }
        }
    }

    /**
     * REQUIRED METHOD: BOARDPASSENGER
     *
     * Description:
     * boardPassenger() method boards passengers on test floors by incrementing passengerCount and decrementing
     * destinationRequests for specific destinationFloor.
     *
     * @param destinationFloor
     */
    public void boardPassenger(int destinationFloor) {
        passengerCount++;
        destinationRequests[destinationFloor - 1]++;
    }

    /**
     * REQUIRED METHOD: TOSTRING
     *
     * Description:
     * toString() method aids in debugging and testing. It also stores currentFloor and passengerCount data for
     * stop() method and floorSnapshot() method when invoked.
     *
     * @return
     */
    public String toString() {
        return String.format("Floor %d: " + "%d passengers\n",
                currentFloor + 1, passengerCount);

    }

    /**
     * ADDITIONAL METHOD: DISEMBARKPASSENGER
     *
     * Description:
     * disembarkPassenger() method drops passengers off at their destination floors by decrementing passengerCount and
     * decrementing destinationRequests for that currentFloor. It is iterated by the for loop.
     */
    private void disembarkPassenger() {
        int disembark = destinationRequests[currentFloor];
        for (int i = 0; i < disembark; i++) {
            passengerCount--;
            destinationRequests[currentFloor]--;
        }
    }

    /**
     * ADDITIONAL METHOD: CHANGEDIRECTION
     *
     * Description:
     * changeDirection() method is responsible for changing the direction of the elevator at top or bottom floors by
     * changing the boolean value of moveUp, and then going back to execute the move() method.
     */
    private void changeDirection() {
        moveUp = !moveUp;
        move();
    }

    /**
     * ADDITIONAL METHOD: STOPELEVATOR
     *
     * Description:
     * stopElevator() method stops the elevator and lets passengers on or off. It decrements the number of
     * destinationRequests, uses disembarkPassenger() method, and then prints the data stored in the toString()
     * method for currentFloor.
     */
    private void stopElevator() {
        destinationRequests[currentFloor - 1] = 0;
        disembarkPassenger();
        System.out.print(this.toString());
    }

    /**
     * ADDITIONAL METHOD: FLOORSNAPSHOT
     *
     * Description:
     * floorSnapshot() method prints data stored in toString() method on floors where the elevator has not stopped to
     * disembark passengers.
     */
    private void floorSnapshot() {
        System.out.print(this.toString());
    }

}