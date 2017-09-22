package cscie55.hw2;

/**
 * IMPLEMENTATION
 * CLASS: BUILDING
 *
 *
 * This is a new class, which keeps track of one Elevator and multiple Floors. It provides access to these objects
 * for tests. Building has the following public methods and fields.
 *
 * -Elevator elevator() : Returns the building's Elevator
 * -Floor floor(int floorNumber): Returns the Floor object for the given floor number.
 * -FLOORS: A static final field storing the number of floors in the building. Set the value to 7.
 * -Building(): The Building constructor creates an Elevator, and one floor for each floor number. (Move the FLOORS
 *  variable, that was in the Elevator class in homework 1 to the Building class.)
 *
 * These specifications are requirements. As such, they specify things that must be done in any correct implementation.
 * You are, of course, free to add other methods (as long as they are not public) and any fields (that should
 * probably not be public).
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw2-fall2017.html
 * Last Accessed: September 20, 2017 @ 15:50 CST
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
        return elevator;
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

}
