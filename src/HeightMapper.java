
import java.io.IOException;
import java.util.EmptyStackException;
//import java.util.Vector;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.TaskID;

public class HeightMapper extends Mapper<LongWritable, Text, Text, Text> {
	
public static int[] v=new int[StackMapper.blocks];
    @Override
    public void map(LongWritable key, Text value, Context output) throws IOException,
            InterruptedException, EmptyStackException {
String[] s=value.toString().split("\t");
char[] ch=s[1].toString().toCharArray();
int size=ch.length;
int height=0;
int subindex=0;
for(int i=0;i<size;i++)
{
	if(ch[i]=='{' || ch[i]=='[' || ch[i]=='(')
	{
		height++;
	}
	else
	{
		height--;
	}
	output.write(new Text(s[0]+"\t"+Integer.toString(subindex)), new Text(Character.toString(ch[i])+"\t"+Integer.toString(height)));
	subindex++;
}
v[Integer.parseInt(s[0])]=height;

    }
}