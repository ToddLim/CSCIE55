Todd Lim
Professor Sawyer
CSCI E-55
4 December 2017


HW7
README.MD


Submission requirements

A zip file containing:
-All the Java files for each problem. [All classes should belong to package cscie55.hw7]
-The output files created by running the MR code (locally) for each driver class containing main.
    --first 10 lines [part-r-00000] for problem 1,
    --the entire output file for problem 2. [Ref: prob2-part-r-00000]
-A jar file containing all class files in a form suitable to run solutions for all two problems.
-A solution overview, explaining each of the solutions.


QUESTION: How can you execute the solution?
ANSWER: Download the JAR files, and then run this command in the Terminal (be in the parent directory of files):

For Problem 1 (UrlTagIndex):
hadoop cscie55.hw7.UrlTagIndex -fs file:/// -jt local ../../../input ../../../output

For Problem 2 (LinkStreamer):
hadoop cscie55.hw7.LinkStreamer -fs file:/// -jt local -D from=08-11-2009 -D to=09-11-2009 ../../../input ../
../../output

Please also make sure you have the correct Hadoop user libraries configured in your module dependencies:

/hadoop-2.x.x/share/hadoop/common/hadoop-2.8.1.jar
/hadoop-2.x.x/share/hadoop/common/lib/*.jar
/hadoop-2.x.x/share/hadoop/mapreduce2/*.jar
/hadoop-2.x.x/share/hadoop/mapreduce2/lib/*.jar
/hadoop-2.x.x/share/hadoop/yarn/*.jar
/hadoop-2.x.x/share/hadoop/yarn/lib/*.jar