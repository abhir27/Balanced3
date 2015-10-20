

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
//import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Balanced3 extends Configured implements Tool{
	 public static Configuration conf=new Configuration();
	public static int size;
	public static void main(String[] args) throws Exception {
    	System.out.println("Started");
    	
    	int res = ToolRunner.run(conf, new Balanced3(), args);
        System.exit(res);       
    }
   
    @Override
    public int run(String[] args) throws Exception {
    	System.out.println("in run");
       if (args.length != 3) {
            System.out.println("usage: [input] [output] [size].");
            System.exit(-1);
        }
      
    String homeDir=args[1];
    size=Integer.parseInt(args[2]);
    long start,end;
   start= new Date().getTime();  
        Job job = Job.getInstance(conf);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        job.setMapperClass(SanitizerMapper.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
       FileOutputFormat.setOutputPath(job, new Path(homeDir+"bal1"));

        job.setJarByClass(Balanced3.class);
        job.waitForCompletion(true);
        
                
        Job job1 = Job.getInstance(conf);
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(Text.class);
        job1.setMapperClass(CombinerMapper.class);
        job1.setReducerClass(CombinerReducer.class);
        job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job1,new Path(homeDir+"bal1"));
        FileOutputFormat.setOutputPath(job1,new Path(homeDir+"bal2"));
        job1.setJarByClass(Balanced3.class);
        job1.waitForCompletion(true);
        
        long start1,end1;
        start1=new Date().getTime();
        Job job2 = Job.getInstance(conf);
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(Text.class);
        job2.setMapperClass(StackMapper.class);
        //job2.setReducerClass(StackReducer.class);
        job2.setInputFormatClass(TextInputFormat.class);
        job2.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job2, new Path(homeDir+"bal2"));
        FileOutputFormat.setOutputPath(job2, new Path(homeDir+"bal3"));
        job2.setJarByClass(Balanced3.class);
        job2.waitForCompletion(true);       
        end1 = new Date().getTime();
        System.out.println("Stack Operation Job took "+(end1-start1) + "milliseconds");
        
        Job job3 = Job.getInstance(conf);
        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(Text.class);
        job3.setMapperClass(HeightMapper.class);
        job3.setReducerClass(HeightReducer.class);
        job3.setInputFormatClass(TextInputFormat.class);
        job3.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job3, new Path(homeDir+"bal3"));
        FileOutputFormat.setOutputPath(job3, new Path(homeDir+"bal4"));
        job3.setJarByClass(Balanced3.class);
        job3.waitForCompletion(true);       
   
        Job job4 = Job.getInstance(conf);
        job4.setOutputKeyClass(IntWritable.class);
        job4.setOutputValueClass(DoubleWritable.class);
        job4.setMapperClass(HashMapper.class);
        //job4.setReducerClass(HashReducer.class);
        job4.setInputFormatClass(TextInputFormat.class);
        job4.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job4, new Path(homeDir+"bal4"));
        FileOutputFormat.setOutputPath(job4, new Path(homeDir+"bal5"));
        job4.setJarByClass(Balanced3.class);
        job4.waitForCompletion(true);
        
        Job job5 = Job.getInstance(conf);
        job5.setOutputKeyClass(IntWritable.class);
        job5.setOutputValueClass(DoubleWritable.class);
        job5.setMapperClass(CalcMapper.class);
        job5.setReducerClass(CalcReducer.class);
        job5.setInputFormatClass(TextInputFormat.class);
        job5.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job5, new Path(homeDir+"bal5"));
        FileOutputFormat.setOutputPath(job5, new Path(args[1]));
        job5.setJarByClass(Balanced3.class);
        job5.waitForCompletion(true);
        end = new Date().getTime();
        System.out.println("Total Job took "+(end-start) + "milliseconds");
        return 0;
    }

}