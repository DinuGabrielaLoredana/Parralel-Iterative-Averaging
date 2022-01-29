import java.util.concurrent.Phaser;

public class FuzzyPhasedParralellAveraging {
	/*
	 * For the number of iterations, for all elements in array, compute the average
	 */
	public static void main(final int nrOfIterations, 
							 long[] result, 
							 long[] array, 
							 final int nrOfElements,
							 final int nrOfTasks)
	{
		Phaser[] phase = new Phaser[nrOfTasks];
        for(int iterator=0;iterator<phase.length;iterator++)
        {
        	phase[iterator] = new Phaser(1);
        }
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
					threadPrivateResult[left] = (long) ((threadPrivateArray[left - 1] + threadPrivateArray[left + 1]) / 2.0);
					threadPrivateResult[right] = (long) ((threadPrivateArray[right - 1] + threadPrivateArray[right + 1]) / 2.0);
					phase[i].arrive();
					for (int j = left + 1; j <= right - 1; j++) 
					{
						threadPrivateResult[j] = (long) ((threadPrivateArray[j - 1] + threadPrivateArray[j + 1]) / 2.0);
					}
					if(i-1>=0)
					{
//				        System.out.println("Arrived task "+ i +" Waiting for "+ (i-1));
				        phase[i-1].awaitAdvance(iteration);
				    }
				    if(i+1<nrOfTasks)
				    {
//				       System.out.println("Arrived task "+ i +" Waiting for "+ (i+1));
				       phase[i+1].awaitAdvance(iteration);
				    }

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
