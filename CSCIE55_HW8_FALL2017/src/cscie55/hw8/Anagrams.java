package cscie55.hw8;

import java.util.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Problem 2 cscie55.hw8.Anagrams (extra credit).
 *
 * cscie55.hw8.Anagrams are words or sequences of words that use exactly the same letters but in a different order.
 * For instance the words "now" and "won" are anagrams of each other.
 *
 * Here are some more complicated anagrams:
 *
 * "Public relations" -> "Crap built on lies"
 * "Wolfgang Amadeus Mozart" -> "A famous German waltz god"
 * "Eleven plus Two" -> "Twelve plus One"
 *
 * Your task in this problem is to identify anagrams in a list of words.
 * Considerations:
 *
 * -Consider each line in the file as a record, i.e. a word or sequence of words that is an anagram candidate.
 * -Ignore white spaces and punctuation in testing for anagrams
 * -Ignore cases, upper vs. lower.
 * -To look for anagrams you need to extract a core representation of the constituent characters of each record from
 *  the data file, call it the "character key string.". For example, for the line that reads "Wolfgang Amadeus Mozart"
 *  you should generate a pair of strings, the first being the character key string, "aaaadefgglmmnoorstuwz", and the
 *  second be the word or phrase on the line. Likewise, when your program eats the line "A famous German waltz god",
 *  it should generate the same key string, allowing it to identify the two strings as anagrams of one another. Notice
 *  that the letters are in alphabetic order and that each letter appears as many times in the key string as it occurs
 *  in the line. Thus, your stream processing should generate a pair of strings,
 * -Use Java 8 streams to
 * --read the input lines
 * --eliminate duplicate lines, and
 * --generate Key/Value pairs combining the character key string and the line
 * --collect the results into a map where the key is the character key string and the value is the string concatenation
 *   of the anagrams. Output should have the form:
 *
 *   eeehnoorttw->one two three...three two one
 *
 * -Run your solution on the data file in this zipfile: anagram-data.zip and submit the last 20 lines of the output
 *  together with your Java code.
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw8_2017.html
 * Last Accessed: 2 December 2017 @ 12:42 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

public class Anagrams {

    //private static final field that stores the FILE_CHARSET; it is set to "ISO-8859-1"
    private static final String FILE_CHARSET = "ISO-8859-1";
    //private static final field that stores the FILE_NAME; it is set to "anagram-data.txt"
    private static final String FILE_NAME = "anagram-data.txt";

    /**
     * METHOD: MAIN
     *
     * -Create an object called 'path' that gets the FILE_NAME. Then, implement try-catch exception handling for
     *  IOExceptions in the stream. Finally, print the stream using a forEach loop, and return the stack trace for
     *  IOExceptions.
     *
     * @param args
     */
    public static void main(String[] args) {
        //create an object called 'path' that gets the FILE_NAME
        Path path = Paths.get(FILE_NAME);
        //implement try-catch exception handling for IOExceptions in the stream
        try(Stream<String> lines = Files.lines(path, Charset.forName(FILE_CHARSET))){
            Map<String, List<String>> sortedChars =
                    lines.collect(Collectors.groupingBy(Anagrams::getSortedCharacters));
            List<String> stringsJoined = getMultipleJoinedStrings(sortedChars);
            //print the stream using a forEach loop
            stringsJoined.stream().forEach(System.out::println);
        } catch (IOException e) {
            //return the stack trace for IOExceptions
            e.printStackTrace();
        }
    }

    /**
     * LAMBDA: GETMULTIPLEJOINEDSTRINGS
     *
     * -Invoke filter() from stream() from entrySet() from groupedStrings List. Then, call map() to concatenate anagrams
     *  with a "," delimiter
     *
     * @param groupedStrings
     * @return
     */
    static List<String> getMultipleJoinedStrings(Map<String, List<String>> groupedStrings) {
        //invoke filter() from stream() from entrySet() from groupedStrings List
        return groupedStrings.entrySet().stream().filter(e -> e.getValue().size() > 1)
                //call map() to concatenate anagrams with a "," delimiter
                .map(e -> String.join(",", e.getValue())).collect(Collectors.toList());
    }

    /**
     * LAMBDA: GETSORTEDCHARACTERS
     *
     * -Invoke mapToObj() from sorted() from chars() from word String
     *
     * @param word
     * @return
     */
    static String getSortedCharacters(String word) {
        //invoke mapToObj() from sorted() from chars() from word String
        return word.chars().sorted().mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining());
    }

}
