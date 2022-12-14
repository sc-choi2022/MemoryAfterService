package ssafy;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class MatrixMulti {
	// Map
	public static class MMMapper extends Mapper<Object, Text, Text, Text>{
                private String Matrix1name;
                private String Matrix2name;

		private int n;	// number of rows in matrix A
		private int l;	// number of columns in matrix A
		private int m;	// number of columns matrix B
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration config = context.getConfiguration();
			// TODO : get a data from config broadcast by main
			// ------------------------------------------------------
			Matrix1name = config.get("Maxrix1name", "A");
			Matrix2name = config.get("Matrix2name", "B");
			n = config.getInt("n", 0);
			l = config.getInt("l", 0);
			m = config.getInt("m", 0);
			// ------------------------------------------------------
		}
		public void map(Object key, Text value, Context context
				) throws IOException, InterruptedException {
			// TODO : make a key-value set
			// ------------------------------------------------------
			String[] inputs = value.toString().split("\t");
			Text pos = new Text();
			Text val = new Text();
			int row = Integer.parseInt(inputs[1]);
			int col = Integer.parseInt(inputs[2]);
			int v = Integer.parseInt(inputs[3]);
			if(inputs[0].equals(Matrix1name)){
				val.set(String.format("%d %d", col, v));
				for (int i = 0; i < m; i++) {
					pos.set(String.format("%d %d", row, i));
					context.write(pos, val);
				}
			}else if(inputs[0].equals(Matrix2name)){
				val.set(String.format("%d %d", row, v));
				for (int i = 0; i < n; i++) {
					pos.set(String.format("%d %d", i, col));
					context.write(pos, val);
				}
			}
			// ------------------------------------------------------
		}
	}
	// Reduce
	public static class MMReducer extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
			// TODO : computes!!!!
			// ------------------------------------------------------
			TreeMap<Integer, Integer> vals = new TreeMap<>();
			for (Text t: values) {
				String[] input = t.toString().split(" ");
				int k = Integer.parseInt(input[0]);
				int v = Integer.parseInt(input[1]);
				if(!vals.containsKey(k)){
					vals.put(k, v);
				}else{
					vals.replace(k, vals.get(k) * v);
				}
			}

			// add
			int sum = 0;
			for (int i: vals.values()) {
				sum += i;
			}

			context.write(key, new Text(String.valueOf(sum)));
			// ------------------------------------------------------
		}
	}
	// Main
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 7) {
			System.err.println("Usage: <Matrix 1 name> <Matrix 2 name> <Number of rows in Matrix 1><Number of columns in Matrix 1 (i.e., Number of rows in Matrix 2)> <Number of columns in Matrix 2> <in> <out>");
			System.exit(2);
		}

		FileSystem hdfs = FileSystem.get(conf);
		Path output = new Path(otherArgs[6]);
		if (hdfs.exists(output))
				hdfs.delete(output, true);

		Job job = new Job(conf, "matrix multiplication prepare");
		Configuration config = job.getConfiguration();
		config.set("Matrix1name", otherArgs[0]);
		config.set("Matrix2name", otherArgs[1]);
		config.setInt("n",Integer.parseInt(otherArgs[2]));
		config.setInt("l",Integer.parseInt(otherArgs[3]));
		config.setInt("m",Integer.parseInt(otherArgs[4]));


		job.setJarByClass(MatrixMulti.class);
		job.setMapperClass(MMMapper.class);
		job.setReducerClass(MMReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(2);

		FileInputFormat.addInputPath(job, new Path(otherArgs[5]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[6]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
