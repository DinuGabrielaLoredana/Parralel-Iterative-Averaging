public class SecventialAveraging {

	/* For the number of iterations, for all elements in array, compute the average */
	public static void main(final int nrOfIterations, 
							long[] result, 
							long[] array, 
							final int nrOfElements) 
	{
		for (int iteration = 0; iteration < nrOfIterations; iteration++) 
		{
			for (int element = 1; element <  nrOfElements-1; element++) 
			{
				result[element] = (long) ((array[element - 1] + array[element + 1]) / 2.0);
			}
			long[] tmp = result;
			result = array;
			array = tmp;
		}
	}

}
