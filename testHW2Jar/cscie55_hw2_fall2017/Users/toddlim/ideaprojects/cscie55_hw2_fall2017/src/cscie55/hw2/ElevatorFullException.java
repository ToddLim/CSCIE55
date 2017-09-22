package cscie55.hw2;

/**
 * IMPLEMENTATION
 * CLASS: ELEVATORFULLEXCEPTION
 *
 *
 * This needs to be a public class, extending java.lang.Exception.
 *
 * ElevatorFullException, like any other exception, must be thrown when something out of the ordinary happens. Because
 * we do not want this exception to end execution of your program, some other code must catch ElevatorFullException
 * and handle the situation, (i.e., that the elevator cannot board a passenger when it is full).
 *
 * In this case, Elevator.boardPassenger(int destinationFloorNumber) throws ElevatorFullException when the Elevator
 * is already at capacity.
 *
 * Elevator.boardPassenger(int destinationFloorNumber) is called when the Elevator stops on a Floor, some passengers
 * leave, and the passengers waiting on that floor try to board. This code must catch the exception, e.g.
 *
 * try {
 *     ...
 *     elevator.boardPassenger(...); // May throw ElevatorFullException
 *     // The passenger boarded successfully
 *     ...
 * } catch (ElevatorFullException e) {
 *     // The passenger was unable to board because the elevator is full.
 *     ...
 * }
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw2-fall2017.html
 * Last Accessed: September 20, 2017 @ 15:50 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

public class ElevatorFullException extends Exception {

    public ElevatorFullException() {
        super();
    }

    public ElevatorFullException(String message) {
        super(message);
    }

}
