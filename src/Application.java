
import java.io.FileNotFoundException;

public class Application {

	static long[] array;
	static long[] result;
	static int nrOfIterations = 1000;
	/* Number of times the algorithm is executed on the same dataset*/
	static int testRepetitions = 2;
	public static void main(String[] args) {
	
		long[][] results = new long[3][testRepetitions];
		long averageSecvential= 0;
		long averageParallel= 0;
		long averageFuzzyParallel= 0;
		Application application = new Application();
		try {
			for (int i = 0; i < testRepetitions; i++) {
				results[0][i] = application.launch(0);
				results[1][i] = application.launch(1);
				results[2][i] = application.launch(2);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < testRepetitions; i++)
		{
			averageSecvential += results[0][i];
			averageParallel += results[1][i];
			averageFuzzyParallel+= results[2][i];
			//System.out.println("Iteratia "+ i + " " +results[0][i] + " " + results[1][i]+ " " + results[2][i]);
			
		}
		averageSecvential /= testRepetitions;
		averageParallel /= testRepetitions;
		averageFuzzyParallel /= testRepetitions;
		System.out.println("Secvential: "+averageSecvential+ " Paralel: " + averageParallel+ " Paralel fuzzy: " +averageFuzzyParallel);
		
//		try {
//			Utils.generateData(1000000000);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	private long launch(int algorithTmype) throws FileNotFoundException {
		
		long start = System.currentTimeMillis();
		
		array = Utils.readInput();
//		Utils.print(array);
		result = new long[array.length-1];
		if (0 == algorithTmype) {
			SecventialAveraging.main(nrOfIterations, result, array, array.length - 1);
//			 Utils.print(result);
		} 
		else if (1 == algorithTmype){
			PhasedParralellAveraging.main(nrOfIterations, result, array, array.length - 1, 4);	
//			 Utils.print(result);
		}
		else if (2 == algorithTmype){
			FuzzyPhasedParralellAveraging.main(nrOfIterations, result, array, array.length - 1, 4);
//			Utils.print(result);
			
		}

		long stop = System.currentTimeMillis();
//        System.out.println("Execution time for "+nrofthreads+" threads: ");
//        System.out.println(stop - start + " ns");
		return (stop - start);
	}

}
