
import java.io.IOException;
import java.util.Stack;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.TaskID;

public class StackMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
	
	public static int blocks=0;
	public static IntWritable one=new IntWritable(1);
    @Override
    public void map(LongWritable key, Text value, Context output) throws IOException,
            InterruptedException {
    	 String[] s=value.toString().split("\t");
         Stack<Character> st=new Stack<>();
  		char[] ch=s[1].toString().toCharArray();
   	int size=ch.length;
 for(int i=0;i<size;i++)
 {
  	if(st.empty())
 	{
 		st.push(ch[i]);
 	}
 	else if(ch[i]=='{' || ch[i]=='[' || ch[i]=='(')
 	{
 		st.push(ch[i]);
 	}
 	else if(st.peek()=='{' && ch[i]=='}')
 	{
 		st.pop();
 	}
 	else if(st.peek()=='[' && ch[i]==']')
 	{
 		st.pop();
 	}
 	else if(st.peek()=='(' && ch[i]==')')
 	{
 		st.pop();
 	}
 	else
 	{
 		st.push(ch[i]);
 	}
 	}
 char[] c=new char[st.size()];
 int ct=0;
 while(!st.empty())
 {
 	c[ct]=st.pop();
 	ct++;
 }
 String nword=new String(c);
 //StringBuffer a = new StringBuffer(nword);
//String s1=new String(a.reverse());  
if(!nword.equals("")){
output.write(new IntWritable(Integer.parseInt(s[0])), new Text(nword));
}
blocks++;
    }
}