import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Merge extends Sort {
	
HashMap<String, Integer> parsedData1 = new HashMap<>();
HashMap<String, Integer> parsedData2 = new HashMap<>();
public static List<Map.Entry<String, Integer>> combinedDataList = new ArrayList<>();
	
// same method as in FileLoad class, overridden to handle 2 files
	public void selectFile(String selectedFile1, String selectedFile2) {
		if (files.contains(selectedFile1) && files.contains(selectedFile2)) {
			//Check File1
			File file1 = new File(currentDir, selectedFile1);
			if(selectedFile1.contains(extension2)) {	
				try (Scanner input = new Scanner(file1)) {
					while(input.hasNext()) {
						parsedData1.put(input.next(), input.nextInt());
						
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (selectedFile1.contains(extension1)) {
				try (Scanner input = new Scanner(file1)) {
		            while (input.hasNextLine()) {
		                String line = input.nextLine();
		                String[] data = line.split(",");  
		                if (data.length >= 2) {
		                    parsedData1.put(data[0], Integer.parseInt(data[1]));
		                }
		            }
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
			}
			//
			//
			//
			//
			
			File file2 = new File(currentDir, selectedFile2);
			if(selectedFile2.contains(extension2)) {	
				try (Scanner input = new Scanner(file2)) {
					while(input.hasNext()) {
						parsedData2.put(input.next(), input.nextInt());
						
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (selectedFile2.contains(extension1)) {
				try (Scanner input = new Scanner(file2)) {
		            while (input.hasNextLine()) {
		                String line = input.nextLine();
		                String[] data = line.split(",");  
		                if (data.length >= 2) {
		                	parsedData2.put(data[0], Integer.parseInt(data[1]));
		                }
		            }
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
			}
			
		}
	}
	
	
// method adds 2 data's to a list  and sorts as done previously.
	public List<Entry<String, Integer>> compareTo() {
	    	
	    	combinedDataList.addAll(parsedData2.entrySet());
	    	combinedDataList.addAll(parsedData1.entrySet());
	    	
	    	combinedDataList.sort(Map.Entry.comparingByValue());;
	 
	    return combinedDataList.reversed();
	}


}