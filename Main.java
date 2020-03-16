import java.util.*;
import java.io.*;

/*
 * Driver class for LazyBinarySearchTree
 */
public class Main {
	public static void main(String[] args) {
		LazyBinarySearchTree implementedTree = new LazyBinarySearchTree();
		
		if(args.length == 2) {
			String text = "";
			Scanner inFile = null;
			PrintWriter outFile = null;
            File outputFile = new File(args[1]);
			// Opening the input files
			try {
				inFile = new Scanner(new File(args[0]));
				if (!outputFile.exists()) {
                    outputFile.createNewFile();
				}   
		        outFile = new PrintWriter(outputFile);
		 
		        while(inFile.hasNextLine()) {    	
		           	text = inFile.nextLine().trim();
		           	String[] input = new String[text.length()];
		            	
		            	if(text.contains(":")) {
		            		input = text.split(":");
		           		} 
		           		else {
		           			input[0] = text;
		           		}
		            		
		                switch(input[0]) {        
		                // Handling every single method utilizing the switch statements
		                
		                    // Handles if the input is wrong
		                	default :
		                		System.out.println("Error in line" + input[0]);  
		                		
		                	case "Insert": 
		                		if(input[1] == null) {
		               				outFile.println("Error in Line: Insert");
		               			} 
		               			else {
			               			try {
			               				boolean insert = implementedTree.insert(Integer.parseInt(input[1]));
				               			outFile.println(insert);
			               			} 
			               			catch (IllegalArgumentException ex){
			               				outFile.println("Error in insert: IllegalArgumentException raised");
			               			}
		               			}           			
		                	break;
		                			
		                	case "Delete": 
		               			try {
		               				boolean delete = implementedTree.delete(Integer.parseInt(input[1]));
			               			outFile.println(delete);
		               			} 
		               			catch (IllegalArgumentException ex){
		               				outFile.println("Error in insert: IllegalArgumentException raised");
		               			}
		               	    break;
		               	    
		                	case "FindMin": 
		               			int min = implementedTree.findMin();
		               			outFile.println(min);
		               			break;
		                		    
		                	case "FindMax": 
		                		int max = implementedTree.findMax();
		               			outFile.println(max);
		               			break;
		                			
		                			
		                	case "PrintTree": 
		                		String out = implementedTree.toString();
		               			outFile.println(out);
		               			break;
		               			
		               		case "Contains": 
		               			try {
		               				boolean has = implementedTree.contains(Integer.parseInt(input[1]));
			               			outFile.println(has);
		               			} 
		               			catch (Exception ex){
		               				outFile.println("Invalid input");
		               			}
		               			break;
		                			
		               		case "Height":
		               			int treeHeight = implementedTree.height();
		               			outFile.println(treeHeight);
		               			break;
		                			
		               		case "Size":
		               			int treeSize = implementedTree.size();
		               			outFile.println(treeSize);
		               			break;
		               			
		               		            		    
		                }
		            }   
		        
		            // Closing the files 
		            inFile.close();
	                outFile.close();
				} 
				catch(FileNotFoundException ex) { // This catch is from try block in the beginning
		            System.out.println( "Cannot open file " + inFile + " "); 
		            
		        } 
				catch(IOException ex) { // This catch is from try block in the beginning
		            System.out.println("Cannot read file '" + inFile + " ");                 
		        }
		} 
		
		else {
	       System.out.println("Please Give Valid Arguments");
		}
		
	}
}
