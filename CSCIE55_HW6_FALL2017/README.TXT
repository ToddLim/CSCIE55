Todd Lim
Professor Sawyer
CSCI E-55
20 November 2017


HW6
README.TXT


Submission requirements: A zip file containing:

-All the Java files for each problem. [All classes should belong to package cscie55.hw6]
-The output files [part-r-00000] created by running the MR code locally as opposed to running against the HDFS.
-A jar file containing all class files in a form suitable to run solutions for both of these problems.
-A solution overview, explaining each of the MR solutions.


QUESTION: How can you execute the solution?
ANSWER: Download the JAR files, and then run this command in the Terminal (be in the parent directory of files):

java -classpath $CLASSPATH cscie55.hw6.WordHistogram
java -classpath $CLASSPATH cscie55.hw6.WordCountByFile

Please set your program arguments correctly as well.

For WordHistogram, mine is:
/Users/toddlim/IdeaProjects/CSCIE55_HW6_FALL2017/input/mobydick.txt
/Users/toddlim/IdeaProjects/CSCIE55_HW6_FALL2017/output/WordHistogram_Results

For WordCountByFile, mine is:
/Users/toddlim/IdeaProjects/CSCIE55_HW6_FALL2017/input/fleas/*
/Users/toddlim/IdeaProjects/CSCIE55_HW6_FALL2017/output/WordCountByFile_Results

Please also make sure you have the correct Hadoop user libraries configured in your module dependencies:

/hadoop-2.x.x/share/hadoop/common/hadoop-2.8.1.jar
/hadoop-2.x.x/share/hadoop/common/lib/*.jar
/hadoop-2.x.x/share/hadoop/mapreduce2/*.jar
/hadoop-2.x.x/share/hadoop/mapreduce2/lib/*.jar
/hadoop-2.x.x/share/hadoop/yarn/*.jar
/hadoop-2.x.x/share/hadoop/yarn/lib/*.jar
