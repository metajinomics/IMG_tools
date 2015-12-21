
// java get_ec_count 68483.assembled.faa.ec rnaseq_expression.txt.RPKM.txt
// 
// for x in ~/globus/*/*.assembled.faa.EC ;do java get_ec_count $x ${x%/*}/rnaseq_expression.txt.RPKM.txt;done
import java.io.*;
import java.util.*;

public class get_ec_count {
	
   public static void main(String args[]) {
   	try{
   	
   		//step 1 read ec in hash map
   		HashMap<String, String> ECmap = new HashMap <String,String>();
   		
   		AddHashMap_one_string(ECmap,args[0],0,2);
   		
   		System.out.println(ECmap.size());
   		
   		//step 2 get ID, ec#, count
   		PrintStream Result = new PrintStream(args[0]+".count.txt");
   		File productFile = new File(args[1]);
	    	Scanner productFile1 = new Scanner(productFile);
	    	while (productFile1.hasNextLine()){
	    		
	    		String line = productFile1.nextLine();
	    		String[] parts = line.split("\t");
	    		if(ECmap.containsKey(parts[0])){
	    			Result.println(parts[0]+"\t"+parts[1]+"\t"+ECmap.get(parts[0])+"\t"+parts[3]);
	    		}
	    			
	    	}
	    productFile1.close();
   		
   		

      }catch (Exception ex) {
			ex.printStackTrace();
		}//try
      
   }
   
   public static void AddHashMap (HashMap<String, HashMap<String,String>> AllMap, String filename, int key, int value, String keyname) {
   		try{
   			File productFile = new File(filename);
	    	Scanner productFile1 = new Scanner(productFile);
	    	while (productFile1.hasNextLine()){
	    		HashMap<String,String> des;
	    		String line = productFile1.nextLine();
	    		String[] parts = line.split("\t");
	    		if(AllMap.containsKey(parts[key])){
					des = AllMap.get(parts[key]);
	    		}else{
	    			des = new HashMap<String,String>();
	    		}
	    		if(parts[value].length()>0){
	    			des.put(keyname,parts[value]);
	    			AllMap.put(parts[key], des);
	    		}	
	    	}
	    	productFile1.close();
	    
	    }catch (Exception ex) {
			ex.printStackTrace();
		}//try
   }
   
   public static void AddHashMap_one_string (HashMap<String, String> AllMap, String filename, int key, int value) {
   		try{
   			File productFile = new File(filename);
	    	Scanner productFile1 = new Scanner(productFile);
	    	while (productFile1.hasNextLine()){
	    		String line = productFile1.nextLine();
	    		String[] parts = line.split("\t");   		
	    		AllMap.put(parts[key],parts[value]);
	    	}
	    	productFile1.close();
	    
	    }catch (Exception ex) {
			ex.printStackTrace();
		}//try
   }
   
   
}