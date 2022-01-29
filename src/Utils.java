import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Utils {

	public static long[] readInput() throws FileNotFoundException {
		Scanner s = new Scanner(new File("dataset_100000000.txt"));
		long[] array = new long[s.nextInt()];
		int i;
		for (i = 0; i < array.length; i++)
			if (s.hasNext())
				array[i] = s.nextInt();
		return array;
	}
	
	public static void generateData(int nrOfInputs) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File("dataset_1000000000.txt"));
	    Random rand = new Random();
	    int number = 0;
	    out.println(nrOfInputs);
		for (int i = 0; i < nrOfInputs; i++)
		{
			number=rand.nextInt(1000000)+1;
            out.println(number);
		}
		out.close();
	}
	
	public static void print(long[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	/* A utility function to swap two elements */
	public static void swap(long[] array, int i, int j)
	{
	    long temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
}
