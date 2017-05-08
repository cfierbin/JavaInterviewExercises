package collectionsAndGenerics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {

	public static void main(String[] args) {
		
		/*
		
		Map<List<Employee>, Employee> groupsOfPeople = new HashMap<>();
        
        groupsOfPeople.put(new ArrayList<Employee>(Arrays.asList(new Employee())), new Employee());
        
        */
		
		Map<List<Employee>, ? extends Employee> groupsOfPeople = new HashMap<>();
        
   //     groupsOfPeople.put(new ArrayList<Employee>(Arrays.asList(new Employee())), new Manager());
	


	}

}
