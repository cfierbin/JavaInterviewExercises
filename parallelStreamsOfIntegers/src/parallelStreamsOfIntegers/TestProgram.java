package parallelStreamsOfIntegers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestProgram {
	
	private static int sum;

	public static void main(String[] args) {
		
	sum = 0;
		
		/*
		FilesReader fr = new FilesReader("D:\\JavaInterviewExercises\\processParallelStreamsOfIntegers\\data\\firstFile.txt");
	//	JMSReader jr = new JMSReader("jms/queue/test");
		
		fr.start();
	//	jr.start();
		
	//	fr.read().forEach(i->System.out.println(i));
		try {
			fr.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fr.getList().forEach(i->System.out.println(i));
		*/
	
	//http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
	
		CallableFilesReader cfr = new CallableFilesReader("D:\\JavaInterviewExercises\\parallelStreamsOfIntegers\\data\\firstFile.txt");
		CallableFilesReader cfr2 = new CallableFilesReader("D:\\JavaInterviewExercises\\parallelStreamsOfIntegers\\data\\firstFile.txt");
		
		ExecutorService executor = Executors.newFixedThreadPool(10);		
		List<Callable<Integer>> callableTasks = new ArrayList<>();		
		
		callableTasks.add(cfr);
		callableTasks.add(cfr2);
		
		try {
			List<Future<Integer>> futures = executor.invokeAll(callableTasks);
			futures.forEach(f -> {
				try {
					Integer i = f.get();
					sum += i;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executor.shutdown();
		try {
		    if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
		        executor.shutdownNow();
		    } 
		} catch (InterruptedException e) {
		    executor.shutdownNow();
		}
		
		System.out.println("The sum is: " + sum);
	
		/*
		cfr.read();
		System.out.println(cfr.calculateSum());
		*/
		
	}
}

/****
 * Bibliography
 * 
 * http://www.baeldung.com/java-executor-service-tutorial
 ***/
