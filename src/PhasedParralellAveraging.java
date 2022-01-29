import java.util.concurrent.Phaser;

public class PhasedParralellAveraging {
	/*
	 * For the number of iterations, for all elements in array, compute the average
	 */
	public static void main(final int nrOfIterations, 
							 long[] result, 
							 long[] array, 
							 final int nrOfElements,
							 final int nrOfTasks)
	{
		Phaser ph = new Phaser(0);
		ph.bulkRegister(nrOfTasks);

		Thread[] threads = new Thread[nrOfTasks];

		for (int task = 0; task < nrOfTasks; task++) 
		{
			final int i = task;
			threads[task] = new Thread(() -> 
			{
				long[] threadPrivateArray = array;
				long[] threadPrivateResult = result;

				for (int iteration = 0; iteration < nrOfIterations; iteration++) 
				{
					final int left = i * (nrOfElements / nrOfTasks) + 1;
					final int right = (i + 1) * (nrOfElements / nrOfTasks);

					for (int j = left; j <= right; j++) 
					{
						threadPrivateResult[j] = (long) ((threadPrivateArray[j - 1] + threadPrivateArray[j + 1]) / 2.0);
					}
					ph.arriveAndAwaitAdvance();

					long[] temp = threadPrivateResult;
					threadPrivateResult = threadPrivateArray;
					threadPrivateArray = temp;
				}
			});
			threads[task].start();
		}

		for (int ii = 0; ii < nrOfTasks; ii++) 
		{
			try 
			{
				threads[ii].join();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
