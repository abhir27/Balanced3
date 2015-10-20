

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HeightReducer extends Reducer<Text, Text,Text, Text > {

	public static int maxheight=0;
	public static int size=0;
	public void reduce(Text key, Text values, Context output)
            throws IOException, InterruptedException {
		String[] k=key.toString().split("\t");
		int prevheight=0;
		for(int i=0;i<Integer.parseInt(k[0]);i++)
		{
			prevheight=prevheight+HeightMapper.v[i];
		}
		String[] val=values.toString().split("\t");
		int h=prevheight + Integer.parseInt(val[1]);
		if(h>maxheight)
		{
			maxheight=h;
		}
		output.write(key, new Text(val[0]+Integer.toString(h)));
		size++;
    }
}