package parallelStreamsOfIntegers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestWithCompletableFuture {

	public static void main(String[] args) {
		
		List<CompletableFuture<Integer>> futures = new ArrayList<>();
		
		try(Stream<Path> paths = Files.walk(Paths.get("D:\\JavaInterviewExercises\\parallelStreamsOfIntegers\\data"))) {
		    paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath)) {
		            System.out.println(filePath);
		            futures.add(CompletableFuture.supplyAsync(() -> {
		            	int sum = 0;
		            	File file = new File(filePath.toString());
		            	try (BufferedReader reader = new BufferedReader(new FileReader(file))){		    
		        		    String text = null;
		        		    
		        		    while ((text = reader.readLine()) != null) {
		        		        sum += (Integer.parseInt(text));
		        		        System.out.println(text);
		        		    }
		        		}
		        		catch (FileNotFoundException e) {
		        		    System.out.println("I can't find this file!!!");
		        		}
		        		catch (IOException e){
		        			e.printStackTrace();
		        		}
		            	return sum;
		            }));
		            	
		        }
		    });
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		Integer combined = futures.stream()
				  .map(CompletableFuture::join)
				  .mapToInt(i -> i)
				  .sum();
		
		System.out.println(combined);

	}

}
