package cscie55.hw7;

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
 * Problem 1. Using the Hadoop API's write a Map/Reduce solution that lists the url's with the (comma-separated) union
 * of the tags listed in all the records for each url. For example, suppose the following two records were seen in the
 * stream:
 *
 * {"url": "http://freegasmoney.org/", "timestamp": 1257034785, "tags": ["free", "gas"]}
 * {"url": "http://freegasmoney.org/", "timestamp": 1257034877, "tags": ["free", "gas", "vacation"]}
 *
 * The final output from your solution would have one line:
 *
 * http://freegasmoney.org/ free,gas,vacation
 *
 * Note: the tags are comma-separated, no white space, and the key/value pair is separated by a single white space.
 * Run your solution on the local file system, i.e. not against the Hadoop cluster. Capture the first ten lines of the
 * final output and submit it with your solution. For correctness you can compare your results with this reference
 * result: prob1-first-ten
 *
 * Hint: One way to accumulate the tags is for the Reducer to defer writing any results until it executes a cleanup
 * after the last reduce method runs, meanwhile storing tags in a java.util.Set, which cleanup finally uses to write
 * the result.
 *
 * To parse the lines of data use this Java code Link.zip    Link.java has a static method parse that takes a String.
 * In Mapper.map you can run Link.parse on each incoming line. This will produce a Link object. Note that Link.parser
 * returns null when it encounters bogus data. [Suggestion: extract a small set of records, say 5 lines, into a single
 * file to run your code on while developing your solution. Two gotchas: (1) blank lines in a data file, (2) editor
 * backup files that your editor may write out in the same directory, like emacs' ~ recovery files. Either of these
 * will cause problems.]
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw7_2017.html
 * Last Accessed: 19 November 2017 @ 16:58 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

//Change class from WordCount to UrlTagIndex
public class UrlTagIndex extends Configured implements Tool {

    /**
     * METHOD: MAIN
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        int res = ToolRunner.run(new UrlTagIndex(), args);
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
        //Job job = new Job(conf, this.getClass().toString());
        Job job = Job.getInstance(conf, this.getClass().toString());

        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.setJobName("cscie55.hw7.UrlTagIndex");
        job.setJarByClass(UrlTagIndex.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        //Use Text.class instead of IntWritable.class
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        //Use Text.class instead of IntWritable.class
        job.setOutputValueClass(Text.class);

        job.setMapperClass(Map.class);
        //Take out job object invocation of setCombinerClass containing Reduce.class
        //job.setCombinerClass();
        job.setReducerClass(Reduce.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    /**
     * CLASS: MAP
     */
    //Change last value inf Mapper from IntWritable to Text in order to match RUN method configuration
    public static class Map extends Mapper<LongWritable, Text, Text, Text> {
        //Take out IntWritable initialization to match RUN methoc configuration
        //private final static IntWritable one = new IntWritable(1);

        //Create two new objects, url and urlTag, from Text class
        private Text url = new Text();
        private Text urlTag = new Text();

        /**
         * METHOD: MAP
         *
         * -Forked from WordCount.java
         *
         * -Changes:
         *  Create a link object from the Link class that parses the value of toString; also, set an if-condition for
         *  non-null values that will set the link url; simply iterate with for-loop.
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

            Link link = Link.parse(value.toString());
            if (link != null) {
                url.set(link.url());
                for (String tag : link.tags()) {
                    urlTag.set(tag);
                    context.write(url, urlTag);
                }
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
         *  tagSet from HashSet with type, <String>, and then iterate over the tag values with a for-loop so that it
         *  adds the the values to the toString invoked by tag. Then, implement outString using StringBuilder and set
         *  the 'first' field to equal true. Finally, iterate the tags over the tagSet to append a comma delimiter when
         *  appropriate.
         *
         * @param key
         * @param values
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            HashSet<String> tagSet = new HashSet<>();
            for (Text tag : values) {
                tagSet.add(tag.toString());
            }
            StringBuilder outString = new StringBuilder();
            boolean first = true;
            for (String tag: tagSet) {
                if (first) {
                    first = false;
                } else {
                    outString.append(",");
                }
                outString.append(tag);
            }
            context.write(key, new Text(outString.toString()));
        }
    }

}
