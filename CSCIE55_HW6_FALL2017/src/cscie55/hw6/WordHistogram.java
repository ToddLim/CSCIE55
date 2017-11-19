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
 * Problem 1.
 * Using WordCount.java as a staring point create a new MR program WordHistogram.java so that the final output of
 * Map/Reduce is a histogram of <word-length, frequency>. For example, assuming you gave output as the destination
 * directory, output/part-r-00000 might contain these lines
 *
 * 5 1254
 * 4 6934
 *
 * meaning there were 1,254 words of length 5 and 6,934 words of length 4. Besides making changes to the WordCount
 * computation, you will also need to change the parameterized types of your Mapper and Reducer classes from those
 * used by WordCount. As always, make sure the output K,V types of your Mapper match the K,V input types of your
 * Reducer class. Submit the output for analyzing mobydick.txt, which comes in the data zip-file cited above.
 * You may run it locally.
 *
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw6_2017.html
 * Last Accessed: 18 November 2017 @ 15:56 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

//Change class from WordCount to WordHistogram
public class WordHistogram extends Configured implements Tool {

    /**
     * METHOD: MAIN
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        int res = ToolRunner.run(new WordHistogram(), args);
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

        //Change setJobName value from WordCount to WordHistogram
        job.setJobName("cscie55.hw6.WordHistogram");
        //Change setJarByClass value from WordCount to WordHistogram
        job.setJarByClass(WordHistogram.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Map.class);
        job.setCombinerClass(Reduce.class);
        job.setReducerClass(Reduce.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    /**
     * CLASS: MAP
     */
    public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        /**
         * METHOD: MAP
         *
         * -while loop iterates on word object and invokes set method, which passes String.valueOf; the parameter
         *  of valueOf method comprises tokenizer.nextToken().length() in order to count the number of characters
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
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(String.valueOf(tokenizer.nextToken().length()));
                context.write(word, one);
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
         * @param key
         * @param values
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }

            context.write(key, new IntWritable(sum));
        }
    }

}



