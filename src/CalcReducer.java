

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class CalcReducer extends Reducer<IntWritable, DoubleWritable,IntWritable, DoubleWritable > {

	
	public void reduce(IntWritable key, Iterable<DoubleWritable> values, Context output)
            throws IOException, InterruptedException {
		double hash=0;
		for(DoubleWritable val : values)
		{
			hash=hash+val.get();
		}
		output.write(key, new DoubleWritable(hash));
    }
}