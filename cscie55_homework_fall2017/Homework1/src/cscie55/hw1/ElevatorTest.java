/**
 * An ElevatorTest class has been created in the "cscie55.hw1.elevatortest" package.
 */

package cscie55.hw1;

/**
 * TESTING
 * CLASS: ELEVATORTEST
 *
 *
 * SPECIFICATION OF REQUIREMENTS
 *
 * To demonstrate that your Elevator class is working properly, create an ElevatorTest class in the package
 * cscie55.hw1.elevatortest. The main() method in this class should do the following:
 *
 * Create an Elevator object.
 * Board two passengers for the 3rd floor, and one for the 5th floor.
 * Move the Elevator from the ground floor to the top floor, and then back to the ground floor.
 * Print the state of the elevator before the first move, and after each move. Your output should look like this:
 *
 *   Floor 1: 3 passengers
 *   Floor 2: 3 passengers
 *   Floor 3: 1 passenger
 *   Floor 4: 1 passenger
 *   Floor 5: 0 passengers
 *   Floor 6: 0 passengers
 *   Floor 7: 0 passengers
 *   Floor 6: 0 passengers
 *   Floor 5: 0 passengers
 *   Floor 4: 0 passengers
 *   Floor 3: 0 passengers
 *   Floor 2: 0 passengers
 *   Floor 1: 0 passengers
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw1-fall2017.html
 * Accessed on September 8, 2017
 *
 * @author Todd Lim
 * @version 1.0
 */

public class ElevatorTest {

    //Reinsert field from Elevator.java into ElevatorTest.java because there is no import statement.
    public static final int totalFloors = 7;

    /**
     * REQUIRED METHOD: MAIN
     * main() method should start here to run the test.
     *
     * @param args
     */

    public static void main(String [] args) {

        Elevator myElevator = new Elevator();

        //Can change floor levels in parameter to reflect different scenarios of boarding for each floor
        myElevator.boardPassenger(3);
        myElevator.boardPassenger(3);
        myElevator.boardPassenger(5);

        System.out.print(myElevator.toString());

        //Can change iterations of going up or down all floors
        for(int i = 0; i < (totalFloors * 2); i++) {
            myElevator.move();
        }

    }

}
