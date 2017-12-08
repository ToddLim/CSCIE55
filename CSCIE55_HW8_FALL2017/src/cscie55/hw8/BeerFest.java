package cscie55.hw8;

/* Adapted from code in "Java Programming", Chapter 20
   by Yakov Fain
   Reference "Java8 Resources" [http://courses.dce.harvard.edu/~cscie55/Java8Resources.html]
 */
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;

import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

/**
 * Problem #1 Java 8 Streams and Predicates
 *
 * Complete the code for cscie55.hw8.BeerFest.java in this zipfile cscie55.hw8.BeerFest.zip It contains unimplemented
 * code [see ToDo comments.
 * For examples of lambda expressions visit the Java files listed here: Video Rentals There are lambda expressions
 * sprinkled around those classes.].
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw8_2017.html
 * Last Accessed: 2 December 2017 @ 12:42 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

public class BeerFest {
    public  static class Beer {
	public final String name;
	public final String country;
	private float price;

	public Beer(String name, String country,float price){
	    this.name=name;
	    this.country=country;
	    this.price=price;
	}

	public String toString(){
	    return "Country: " + country +  " Name: " + name + ", price: " + price;
	}
	public float getPrice() {
	    return price;
	}
	public void setPrice(float price) {
	    this.price = price;
	}
    }

    /**
     * LAMBDA: BEERQUERY
     *
     * -Beerquery is used to filter beer records that start with the character given, in this case, "S"
     *
     * @param beerList
     * @param predicate
     * @return
     */
/**
    public static List<Beer> beerQuery(List<Beer> beerList, Predicate <Beer> predicate) {
        List<Beer> result = beerList.stream()
                //if the query used is "S", then the filter should invoke the startsWith method for "S"
                .filter(x -> x.startsWith("S"))
                //iterate through println
                .forEach(System.out::println);
        if x.startsWith("S") == null {
            throw Exception ("There are no results for your search. Please try again.")
        }
	    // ToDo Select Beer's that meet the predicate
        return result;
    }
*/

    static List<Beer> loadCellar(){
        List<Beer> beerStock = new ArrayList<>();

        beerStock.add(new Beer("Stella", "Belgium", 7.75f));
        beerStock.add(new Beer("Sam Adams", "USA", 7.00f));
        beerStock.add(new Beer("Obolon", "Ukraine", 4.00f));
        beerStock.add(new Beer("Bud Light", "USA", 5.00f));
        beerStock.add(new Beer("Yuengling", "USA", 5.50f));
        beerStock.add(new Beer("Leffe Blonde", "Belgium", 8.75f));
        beerStock.add(new Beer("Chimay Blue", "Belgium", 10.00f));
        beerStock.add(new Beer("Brooklyn Lager", "USA", 8.25f));

        return beerStock;
    }

    /**
     * LAMBDA: PRICERANGEQUERY
     *
     * -Pricerangequery is used to filter beer records that are within a price range, in this case, < 7.00f
     *
     * @param y
     * @return
     */
/**
    static Predicate<Beer> priceRangeQuery(float y) {
        List<Beer> result = beerList.stream()
                //map the prices
                .map(y -> y.price)
                //if the query used is "7.00", then  filter should invoke  getPrice method for values less than 7.00f
                .filter(y -> y.getPrice() < 7.00f)
                //iterate through println
                .forEach(System.out::println);
        if y.getPrice() >= 7.00f {
            throw Exception ("There are no results for your search. Please try again.")
	// ToDo: compose and return a Predicate that will 
	//       express the selection criterion
    }
*/

    /**
     * LAMBDA: COUNTRYQUERY
     *
     * -Countryquery is used to filter through countries that start with a certain character, in this case, "B"
     *
     * @param z
     * @return
     */
/**
    static Predicate<Beer> countryQuery(String z) {
        List<Beer> result = beerList.stream()
                //map the countries
                .map(z -> z.country)
                //if the query used is "B", then the filter should invoke the startsWith method for "B"
                .filter(z -> z.startsWith("B"))
                //iterate through println
                .forEach(System.out::println);
        if z.startsWith("B") == null {
            throw Exception ("There are no results for your search. Please try again.")
	// ToDo: compose and return a Predicate that will 
	//       express the selection criterion
    }
*/

    /**
     * METHOD: MAIN
     *
     * @param argv
     */
    public static void main(String argv[]) {
	List<Beer> beerList = loadCellar();
/**
	// Call beerQuery with a predicate for selecting a country
    // use "Belgium" as an example
	beerQuery(beerList, countryQuery("Belgium")).forEach(System.out::println);
	// Call beerQuery with a predicate for a price range
    // use "7.50" as an example
	beerQuery(beerList, priceRangeQuery(y >= 7.50f)).forEach(System.out::println);
    }
}
*/
    }}