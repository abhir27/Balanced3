
import java.io.IOException;
import java.util.EmptyStackException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.TaskID;

public class CombinerMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
	
	//public static IntWritable i=new IntWritable(0);
	public static IntWritable one=new IntWritable(1);
	public static final int size=Balanced3.size;
    @Override
    public void map(LongWritable key, Text value, Context output) throws IOException,
            InterruptedException, EmptyStackException {
    	
String[] s=value.toString().split("\t");
    	output.write(new IntWritable(Integer.parseInt(s[0])/size), new Text(s[1]));
    }
}