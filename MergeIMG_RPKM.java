// java MergeIMG_RPKM rnaseq_expression.txt.RPKM.txt 68483.assembled.faa.product.names 68483.assembled.faa.phylodist 68483.assembled.faa.ec
import java.io.*;
import java.util.*;

public class MergeIMG_RPKM {
	
   public static void main(String args[]) {
   	try{
   		HashMap<String, HashMap<String,String>> AllMap = new HashMap<String, HashMap<String,String>>();
   	
    	AddHashMap(AllMap,args[0],0,3,"count");

		AddHashMap(AllMap,args[1],0,1,"product");

		AddHashMap(AllMap,args[2],0,4,"phylo");

	    AddHashMap(AllMap,args[3],0,2,"ec");
	    HashSet<String> ECset = new HashSet<String>();
	    File productFile = new File("ecList.txt");
	    Scanner productFile1 = new Scanner(productFile);
	    while (productFile1.hasNextLine()){
	    	ECset.add(productFile1.nextLine());	
	    }
	    productFile1.close();

	    // Get a set of the entries
      	Set set = AllMap.entrySet();
      	// Get an iterator
      	Iterator i = set.iterator();
      	// Display elements

      	String ResultFile=args[2]+".out.txt";
		PrintStream Result = new PrintStream(ResultFile);
      	while(i.hasNext()) {
        	Map.Entry me = (Map.Entry)i.next();
        	HashMap<String,String> inMap = AllMap.get(me.getKey());
        	if(ECset.contains(inMap.get("ec"))){
        	Result.print(me.getKey()+"\t");
        	Result.println(inMap.get("product")+"\t"+inMap.get("ec")+"\t"+inMap.get("phylo")+"\t"+inMap.get("count"));
        	}
      	}

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
}