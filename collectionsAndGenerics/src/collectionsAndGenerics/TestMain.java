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
		
		Map<Integer, List<String>> map = new HashMap<>();
		map.put(3, new ArrayList<>(Arrays.asList("1","2")));
		map.putIfAbsent(3,  new ArrayList<>()).add("3");
		
		map.forEach((i, list) ->
		list.forEach(s -> System.out.println(s)));
	


	}

}
