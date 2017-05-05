package processParallelStreamsOfIntegers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableFilesReader implements Reader, Callable<Integer>{
	
	private List<Integer> list;
	private File file;
	private int sum;
	
	CallableFilesReader(String fileName){
		file = new File(fileName);
		list = new ArrayList<Integer>();
	}

	@Override
	public List<Integer> read() {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){		    
		    String text = null;
		    while ((text = reader.readLine()) != null) {
		        list.add(Integer.parseInt(text));
		        System.out.println(text);
		    }
		}
		catch (FileNotFoundException e) {
		    System.out.println("I can't find this file!!!");
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer call() throws Exception {
		read();
		return calculateSum();
	}
	
	Integer calculateSum(){
		sum = 0;
		list.forEach(i -> sum += i);
		return sum;
		}
	}


