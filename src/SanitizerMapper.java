
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.TaskID;

public class SanitizerMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
	
	public static IntWritable i=new IntWritable(0);
	public static IntWritable one=new IntWritable(1);
    @Override
    public void map(LongWritable key, Text value, Context output) throws IOException,
            InterruptedException {
    	String words=value.toString().replaceAll("[^{}()\\[\\]\\n]+","");
    	int l=words.length();
    	if(l!=0){
    	 	 output.write(i,new Text(words));
    	 	 int val=i.get();
    	  val++;
    	  i=new IntWritable(val);
    	}
    }
}