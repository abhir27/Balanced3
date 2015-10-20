
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.TaskID;

public class CalcMapper extends Mapper<LongWritable, Text, IntWritable, DoubleWritable> {
	public static IntWritable one =new IntWritable(1);
    @Override
    public void map(LongWritable key, Text value, Context output) throws IOException,InterruptedException {
    	String[] sz=value.toString().split("\t");
    	
		output.write(one,new DoubleWritable(Double.parseDouble(sz[1])));
    }
}

