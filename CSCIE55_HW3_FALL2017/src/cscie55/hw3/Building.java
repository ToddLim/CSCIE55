package cscie55.hw3;

/**
 * IMPLEMENTATION
 * CLASS: BUILDING
 *
 *
 * -Add void enter(Passenger passenger): Simply calls Floor.enterGroundFloor(passenger) for the Floor representing
 * the ground floor.
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw3-fall2017.html
 * Last Accessed: September 30, 2017 @ 19:35 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */
public class Building {

    //instantiate the object; it will be known as 'elevator'
    private Elevator elevator;

    //static final field storing the number of floors in the building; it is set to 7
    public static final int FLOORS = 7;

    //static final field storing the value of the ground floor, or what we'd call the first floor; it is set to 1
    public static final int GROUND_FLOOR = 1;

    //declare and instantiate an array for object: 'Floor'
    private Floor [] floor = new Floor[Building.FLOORS + 1];

    /**
     * CONSTRUCTOR: BUILDING
     *
     * -creates an Elevator in the building
     * -creates a new floor object for each floor in the building
     */
    public Building() {

        elevator = new Elevator(this);
        for (int i = Building.GROUND_FLOOR; i <= Building.FLOORS; i++) {
            floor[i] = new Floor(this, i);
        }
        this.elevator = new Elevator(this);
    }

    /**
     * METHOD: ELEVATOR (INSTANCE)
     *
     * @return elevator to correct building
     */
    public Elevator elevator() {
        return this.elevator;
    }

    /**
     * METHOD: FLOOR (INSTANCE)
     *
     * @param floorNumber is to return floor number in the form of an int
     * @return the Floor object for the given floor number.
     */
    public Floor floor(int floorNumber) {
        return floor[floorNumber];
    }

    /**
     * METHOD: ENTER
     *
     * -simply calls floor.enterGroundFloor(passenger) for the Floor representing the ground floor
     *
     * @param passenger that enters from GROUND_FLOOR
     */
    public void enter(Passenger passenger) {
        floor[GROUND_FLOOR].enterGroundFloor(passenger);
    }

}