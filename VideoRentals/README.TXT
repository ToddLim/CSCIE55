Todd Lim
Professor Sawyer
CSCI E-55
6 November 2017


HW5
README.TXT
-Summarize your strategy and any bugs you have discovered.


***STRATEGY***
First, I followed the instructions from the instructions on installing Maven and setting up a Maven project in my IDE,
IntelliJ IDEA. Any questions I had were answered in Piazza; unfortunately, setting up Maven to work for this assignment
was the longest part of completing this assignment, which is why it took me longer than usual to submit.

Second, I went through the UML document and created the test stubs for each of the methods in the java classes for:
Account, Video, and VideoRental in the JUnitTest classes. Then, I made sure to import the proper libraries; I also
found out that because of the way the package directory is structured, I didn't have to import any of the individual
classes to create objects of those classes and use their methods, so I'm guessing that is one of the advantages of
using Maven.

Third, I created tests to test the functionality of the code provided in the classes I was testing. Though I could've
created tests specifically for the exception handling, I tried to integrate as much as I could into the unit tests I
made for each method because of time constraints; consequently, isolating bugs was limited to just the unit tests.

Finally, when I went through each of the unit tests, I made sure to document why I coded the programs that I did in
order to provide readability and hopefully get some of my questions answered, like if I was on the right track.


***BUGS***
Please see Java source file documentation for specifics about bugs; this is just a reference for discovered bugs.

AccountJUnitTest (3/18 tests failed)
-testGetOpenRentals()
-testGetClosedRentals()
-testClearHistory()

VideoJUnitTest (1/13 tests failed)
-testRemoveFromStock()

VideoRentalJUnitTest (5/12 tests failed)
-test2VideoRental()
-testGetVideo()
-testGetAccount()
-testRentalReturn()
-testIsOverDue()
