

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CombinerReducer extends Reducer<IntWritable, Text,IntWritable, Text > {

	public static int size=0;
	@Override
    public void reduce(IntWritable key, Iterable<Text> values, Context output)
            throws IOException, InterruptedException {
		String s="";
		for(Text val:values)
		{
			s=s+val.toString();
		}
        output.write(key, new Text(s));
    }
}