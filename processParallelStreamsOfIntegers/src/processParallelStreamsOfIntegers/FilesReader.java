package processParallelStreamsOfIntegers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesReader implements Reader{
	
	private List<Integer> list;
	private File file;
	
	FilesReader(String fileName){
		file = new File(fileName);
		list = new ArrayList<Integer>();
	}

	@Override
	public List<Integer> read() {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){		    
		    String text = null;
		    while ((text = reader.readLine()) != null) {
		        list.add(Integer.parseInt(text));
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
}
