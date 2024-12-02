/*
 * Search for a key in a key value hashmap, then push it into a list.
 */

import java.util.*;


public class Filter extends FileLoad{
	List<Map.Entry<String, Integer>> filteredData = new ArrayList<>();
	
	public List<Map.Entry<String, Integer>> dataFilter(String userInput) {
		//iterating through the data, if the data key contains a userinput then, add the whole entry to a list.
		for (Map.Entry<String, Integer> entry : parsedData.entrySet()) {
			if (entry.getKey().toLowerCase().contains(userInput.toLowerCase())) {
				filteredData.add(entry);
				
			}
		}
		//testing
		//System.out.println(filteredData);
		
		
		//returning descending order.
		return filteredData.reversed();
	}
}
