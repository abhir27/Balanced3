
import java.io.IOException;
import java.util.Vector;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.TaskID;

public class HashMapper extends Mapper<LongWritable, Text, IntWritable, DoubleWritable> {
	public static int a1=1,a2=2,a3=3,c=2,p=0;
	//public static Hashtable h=new Hashtable(2*Integer.parseInt(s[3])Reducer.maxInteger.parseInt(s[3]));
public static Vector<Integer> v=new Vector<Integer>(1000,100);
    @Override
    public void map(LongWritable key, Text value, Context output) throws IOException,
            InterruptedException {
String[] s=value.toString().split("\t");
double hash=0;
/*Random rand=new Random();
int max=(int) ((int) 2*(Math.pow(HeightReducer.size,1+c)));	;
int min=(int) Math.pow(HeightReducer.size,1+c);	
int range=10;
if(max>min)
{
	range=max-min;
	}*/
		int p = 97;//rand.nextInt((range)) + min;
		if(s[2].equals("{"))
    	{
    		hash=Math.pow(a1,Integer.parseInt(s[3]))%p;
    	}
    	else if(s[2].equals("["))
    	{
    		hash=Math.pow(a2,Integer.parseInt(s[3]))%p;
    	}
    	else if(s[2].equals("("))
    	{
    		hash=Math.pow(a3,Integer.parseInt(s[3]))%p;
    	}
    	else if(s[2].equals("}"))
    	{	
    		hash=Math.pow(a1,Integer.parseInt(s[3])+1)%p;
    		hash=hash*(-1);
    	}
    	else if(s[2].equals("]"))
    	{
    		hash=Math.pow(a2,Integer.parseInt(s[3])+1)%p;
    		hash=hash*(-1);
    	}
    	else if(s[2].equals(")"))
    	{
    		hash=Math.pow(a3,Integer.parseInt(s[3])+1)%p;
    		hash=hash*(-1);
    	}
	//output.write(new Text(s[0]+"\t"+s[1]+"\t"+s[2]), new Text());
		output.write(new IntWritable(Integer.parseInt(s[0])), new DoubleWritable(hash));
    }
}