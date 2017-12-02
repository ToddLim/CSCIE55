package cscie55.hw7;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.TimeZone;
import java.util.Date;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.stream.Stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;

/**
 * Problem 2. Solve the same problem using Java 8 features, i.e. Lambda Expressions, Function References and Streams.
 * A single Java class should be all that is necessary. Call it LinkStreamer, in the same package as above, cscie55.hw7
 *
 * Usage:
 *
 * java cscie55.hw7.LinkStreamer input-directory output-file [dd-MM-yyyy dd-MM-yyyy]
 *
 * When the program is called with two arguments it will look in the named input directory and stream the content of
 * all the files it finds there, writing out the result to the named output file. Output will have the form:
 *
 * http://haberdashers.com/ hats, coats, gloves
 *
 * Add a new feature that counts occurrences of each URL between a range of dates when given on the command line. Thus,
 * when the program is called with two additional arguments to specify a date range the program will write out a total
 * count of the occurences, output looking like this:
 *
 * http://haberdahser.com/ 21
 *
 * The date format to support is dd-MM-yyyy
 *
 * In order to generate a long value for comparison to the timestamp of each Link in the stream you will need some
 * Java date API's. These will do the trick:
 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
 * simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
 * Date startDate = simpleDateFormat.parse(args[2]);
 * Long startseconds = startDate.getTime()/1000;
 *
 * The division by 1000 converts milliseconds to seconds.
 *
 * Considerations:
 *
 * First command line argument will be the directory name. Solution will have to iterate over the files in that
 * directory.
 *
 * In order to integrate all the data in those files the processing of the files as individual streams will need to
 * maintain some state from stream to stream, all the while parsing the lines of input and turning each one into a Link.
 *
 * Once your solution has collected the result, have it print to standard out the pairs <URL, Tags>, as above in the
 * problem statement for Hadoop.
 *
 * Collect the output in a file and include it with your solution.
 *
 * Skip generating javadoc. Instead, comment the code to narrate the algorithm.
 *
 * Run your solution, giving start date as November 8, 2009 and end date as November 9, 2009.
 *
 *
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw7_2017.html
 * Last Accessed: 19 November 2017 @ 16:52 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

/**
public class LinkStreamer {
    public static void main(String argv[]) throws Exception {
        Stream<String> lines = Files.lines(Paths.get(argv[0]));
        Map<String, Long> wordCountMap = lines
                .map(s -> s.split"\\s+"))
                .flatMap(Arrays::stream)
                .collect(groupingBy(identity(), counting()));
    System.out.println(wordCountMap);
    }
}
 */

//Change class from WordCount to LinkStreamer
public class LinkStreamer extends Configured implements Tool {
    /**
     * METHOD: MAIN
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        int res = ToolRunner.run(new LinkStreamer(), args);
        System.exit(res);
    }

    /**
     * METHOD: RUN
     *
     * -Forked from WordCount.java
     *
     * -Changes: Create conditional if-statements to invoke getMillisecondsFromdateString method and setLong of conf
     *  object to millis; do this for both "from" and "to" values
     *
     * @param args
     * @return
     * @throws Exception
     */
    public int run(String[] args) throws Exception {
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        Configuration conf = getConf();

        if (conf.get("from") != null) {
            Long millis = getMillisecondsFromDateString(conf.get("from"));
            if (millis != null) {
                conf.setLong("startTimeMilliseconds", millis);
            }
        }
        if (conf.get("to") != null) {
            Long millis = getMillisecondsFromDateString(conf.get("to"));
            if (millis != null) {
                conf.setLong("endTimeMilliseconds", millis);
            }
        }

        //Job job = new Job(conf, this.getClass().toString());
        Job job = Job.getInstance(conf, this.getClass().toString());

        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.setJobName("cscie55.hw7.LinkStreamer");
        job.setJarByClass(LinkStreamer.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Map.class);
        //job.setCombinerClass(Reduce.class);
        job.setReducerClass(Reduce.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    /**
     * METHOD: GETMILLISECONDSFROMDATESTRING
     *
     * The date format to support is dd-MM-yyyy
     *
     * In order to generate a long value for comparison to the timestamp of each Link in the stream you will need some
     * Java date API's. These will do the trick:
     * SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
     * simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
     * Date startDate = simpleDateFormat.parse(args[2]);
     * Long startseconds = startDate.getTime()/1000;
     *
     * The division by 1000 converts milliseconds to seconds.
     *
     * @param dateString
     * @return
     */
    private Long getMillisecondsFromDateString(String dateString) {
        Long returnValue = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));

        //Implement Exception Handling with try-catch for parse exceptions
        try {
            Date startDate = simpleDateFormat.parse(dateString);
            returnValue = startDate.getTime()/1000;
        } catch (ParseException pe) {
            System.err.println("INVALID DATE!");
        }

        return returnValue;
    }

    /**
     * CLASS: MAP
     */
    public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        //Change object name from 'word' to 'url'
        private Text url = new Text();

        /**
         * METHOD: MAP
         *
         * -Forked from WordCount.java
         *
         * -Changes: Create long objects, start and end, which are getters for the Configuration values. Next, iterate
         *  over the tokens to parse them as Link objects. Next, use conditional if-statements for quality control.
         *
         * @param key
         * @param value
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void map(LongWritable key, Text value,
                        Context context) throws IOException, InterruptedException {
            Long start = context.getConfiguration().getLong("startTimeMilliseconds", 0L);
            Long end = context.getConfiguration().getLong("endTimeMilliseconds", Long.MAX_VALUE);

            Link link = Link.parse(value.toString());

            if (link != null) {
                url.set(link.url());

                //Verify that the date in the link object is valid within the user input parameters
                if (link.timestamp() >= start && link.timestamp() < end) {
                    context.write(url, one);
                }
            }
        }
    }

    /**
     * CLASS: REDUCE
     */
    public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {

        /**
         * METHOD: REDUCE
         *
         * -Forked from WordCount.java
         *
         * -Changes: none
         *
         * @param key
         * @param values
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }

            context.write(key, new IntWritable(sum));
        }
    }

}