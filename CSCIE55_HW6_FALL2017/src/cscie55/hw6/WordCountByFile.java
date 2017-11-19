package cscie55.hw6;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;

/**
 * Problem 2.
 * Define a new adaptation of WordCount, call it WordCountByFile. Design the new Mapper and Reducer classes to report
 * word counts by file name.
 *
 * -Suppose the word "foo" appears twice in the file /Users/charliesawyer/java/input/f1.txt and once in the file
 * /Users/charliesawyer/java/input/f2.txt. Then the output (output/part-r-00000) should contain this line:
 *
 * foo file:/Users/charliesawyer/java/input/f1.txt: 2 file:/Users/charliesawyer/java/input/f2.txt: 1
 *
 * -To accomplish this you need something to report the name of each file that Hadoop is processing. Fortunately,
 * Hadoop has a method that will do this among the myriad methods of the Hadoop API. You can learn what this API is
 * by asking Google to search with keywords "Hadoop" "file name" and "mapper". [Lesson: The starting point for solving
 * all problems is a Google search query.]
 *
 * -Consider what types to provide for your Mapper and Reducer classes.
 *
 * -Arrange to run it on an input file containing the three "fleas" files (fleas1.txt, fleas2.txt, fleas3.txt).
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw6_2017.html
 * Last Accessed: 18 November 2017 @ 15:56 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

//Change class from WordCount to WordCountByFile
public class WordCountByFile extends Configured implements Tool {

    /**
     * METHOD: MAIN
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        int res = ToolRunner.run(new WordCountByFile(), args);
        System.exit(res);
    }

    /**
     * METHOD: RUN
     *
     * @param args
     * @return
     * @throws Exception
     */
    public int run(String[] args) throws Exception {
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        Configuration conf = getConf();
        Job job = new Job(conf, this.getClass().toString());

        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        //Change setJobName value from WordCount to WordCountByFile
        job.setJobName("cscie55.hw6.WordCountByFile");
        //Change setJarByClass value from WordCount to WordCountByFile
        job.setJarByClass(WordCountByFile.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        //Change setMapOutputValue from IntWritable to Text
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        //Change setOutputValue from IntWritable to Text
        job.setOutputValueClass(Text.class);

        job.setMapperClass(Map.class);
        //Take out job object invocation of setCombinerClass containing Reduce.class
        //job.setCombinerClass(Reduce.class);
        //Change setReduerClass from Reduce to Map.Reduce
        job.setReducerClass(Map.Reduce.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    /**
     * CLASS: MAP
     */
    //Change last value in Mapper from IntWritable to Text in order to match RUN method configuration
    public static class Map extends Mapper<LongWritable, Text, Text, Text> {
        //Take out IntWritable initialization to match RUN method configuration
        //private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        //Create a String pattern field to filter characters
        private String pattern = "^[a-z][a-z0-9]*$";

        /**
         * METHOD: MAP
         *
         * -Forked from WordCount.java
         *
         * -Changes:
         *  Create two objects, inputSplit and fileName; then, in the 'while' loop, create stringWord object to invoke
         *  the toString method and toLowerCase method. Create a condition for stringWord object so that the fileName
         *  additionally gets written.
         *
         * @param key
         * @param value
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void map(LongWritable key, Text value,
                        Mapper.Context context) throws IOException, InterruptedException {
            //Create inputSplit object that gets the inputSplit for context object
            InputSplit inputSplit = context.getInputSplit();
            //Create String fileName object that gets the path and name of the inputSplit object
            String fileName = ((FileSplit) inputSplit).getPath().getName();
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                //Create stringWord object that invokes the toString method and converts to lowercase
                String stringWord = word.toString().toLowerCase();
                //Create condition for stringWord object so that the fileName additionally gets written
                if (stringWord.matches(pattern)) {
                    context.write(new Text(stringWord), new Text(fileName));
                }
            }
        }

        /**
         * CLASS: REDUCE
         */
        //Change 2nd and 4th values of Reducer from IntWritable to Text to look like this: <Text, Text, Text, Text>
        public static class Reduce extends Reducer<Text, Text, Text, Text> {

            /**
             * METHOD: REDUCE
             *
             * -Forked from WordCount.java
             *
             * -Changes:
             *  Change Iterable values from <IntWritable> value to <Text> for the initial parameters. Next, initialize
             *  fields: check, fileName, and textString to false, "", and "" respectively. In the 'for' loop, change
             *  IntWritable value to Text value, and then modify the 'for' loop by creating three conditions before
             *  having context call write.
             *
             * @param key
             * @param values
             * @param context
             * @throws IOException
             * @throws InterruptedException
             */
            @Override
            //Change Iterable values from <IntWritable> value to <Text>
            public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
                int sum = 0;
                //Initialize boolean check field to false
                boolean check = false;
                //Initialize fileName to ""
                String fileName = "";
                //Initialize textString to ""
                String textString = "";

                //Change IntWritable value to Text value and modify the 'for' loop
                for (Text value : values) {
                    //Create condition for !check so that when invoked, the fileName is set to value, which calls
                    // toString method; afterwards, deactivate check by setting to true
                    if (!check) {
                        fileName = value.toString();
                        check = true;
                    }
                    //Create condition for fileName.equals(value.toString()) so that the sum increments
                    if (fileName.equals(value.toString())) {
                        sum = sum + 1;
                    } else {
                        //Create else conditional for textString to concatenate fileName and sum
                        textString += fileName + ": " + sum + ", ";
                        //Set fileName to value, which calls toString method
                        fileName = value.toString();
                        //Set sum equal to 1
                        sum = 1;
                    }
                    //Remove for now
                    //sum += value.get();
                }
                //Set textString to add fileName, sum, and a carriage return in output
                textString += fileName + ": " + sum + "\n";
                //Change 2nd parameter value of context.write from new IntWritable(sum) to new Text(textString)
                context.write(key, new Text(textString));
            }
        }
    }
}