import java.util.*;
import java.util.Map.Entry;

public class Sort extends FileLoad {
	//creating a list of a single map entry (k, v) to store the sorted data.
	static public List<Map.Entry<String, Integer>> sortedParsedData = new ArrayList<>();
	
	//adds the data to the list and sorts it.
	static public List<Entry<String, Integer>> sortData(){
		sortedParsedData.addAll(parsedData.entrySet());
		sortedParsedData.sort(Map.Entry.comparingByValue());;
		//descending order
		return sortedParsedData.reversed();
	}
}
