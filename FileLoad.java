/*
 * This class is abstract to give other classes common methods,
 * while preventing subclasses being associated directly with this class name.
 */

import java.io.*;
import java.util.*;
public abstract class FileLoad{
	public static String extension1 = ".csv", extension2 = ".txt";
	
	//get current directory the file is in.
	public static String currentDirStr = "/Users/bs/eclipse-workspace/Project2Info2315/src";
	
	//creating an object for file class to be able to organize the directory into an array.
	public static File currentDir = new File(currentDirStr);
	String[] allFiles = currentDir.list();
	
	//filtered version of allFiles from search()
	public static List<String> files = new ArrayList<String>();
	
	//the parsed data from selectFile()
	public static HashMap<String, Integer> parsedData = new HashMap<>();
	
	public static void selectFile(String selectedFile) {
		if (files.contains(selectedFile)) {
			//creating an object with the file to be able to work within it
			File currentFile = new File(currentDir, selectedFile);
			
			//scanner to help parse data easier
			if(selectedFile.contains(extension2)) {	
				try (Scanner input = new Scanner(currentFile)) {
					while(input.hasNext()) {
						//whitespace is used as a delimiter in .txt
						parsedData.put(input.next(), input.nextInt());
						
					}
					//file not found error
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//parsing data for csv files
			if (selectedFile.contains(extension1)) {
				try (Scanner input = new Scanner(currentFile)) {
		            while (input.hasNextLine()) {
		            	//spliting each line by the comma and storing it in an array
		                String line = input.nextLine();
		                String[] data = line.split(",");
		                //if data is split into two push it into hashmap
		                if (data.length == 2) {
		                    parsedData.put(data[0], Integer.parseInt(data[1]));
		                }
		            }
		            //if file is not there error
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
			}
		}
	}
	
	
	//searching through the array of allFiles to find the files we want. (.csv & .txt)
	public void search() {
		//caused error had to had.
		files.clear();
		//if the file extension is in a given file add it to a list for later use.
		for (int x = 0; x < allFiles.length; x++) {
			if (allFiles[x].contains(extension1) == true || allFiles[x].contains(extension2) == true) {
				
				files.add(allFiles[x]);
			}
		}
		//testing
		//System.out.println(currentDirStr);
		//System.out.println("there are " + allFiles.length + " files in the directory.");
		//System.out.println("found " + files.size() + " .csv & .txt files.");
    } 
}
