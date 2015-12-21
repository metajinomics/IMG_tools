// java MergeIMG rnaseq_expression.txt 68483.assembled.faa.product.names 68483.assembled.faa.phylodist 68483.assembled.faa.ec
import java.io.*;
import java.util.*;

public class MergeIMG {
	
   public static void main(String args[]) {
   	try{
   		HashMap<String, HashMap<String,String>> AllMap = new HashMap<String, HashMap<String,String>>();
   		
   		//System.out.println("read file1");
   		// AllMap, filename, key=0, value=1, keyname=product
   		//AddHashMap(AllMap,"rnaseq_expression.txt",0,7,"count");
    	AddHashMap(AllMap,args[0],0,7,"count");
	    //System.out.println("read file2");
   		//AddHashMap(AllMap,"68483.assembled.faa.product.names",0,1,"product");
		AddHashMap(AllMap,args[1],0,1,"product");
	    //System.out.println("read file3");
	    //AddHashMap(AllMap,"68483.assembled.faa.phylodist",0,4,"phylo");
		AddHashMap(AllMap,args[2],0,4,"phylo");
	    //System.out.println("read file4");
	    //AddHashMap(AllMap,"68483.assembled.faa.ec",0,2,"ec");
	    AddHashMap(AllMap,args[3],0,2,"ec");
	    HashSet<String> ECset = new HashSet<String>();
	    File productFile = new File("ecList.txt");
	    Scanner productFile1 = new Scanner(productFile);
	    while (productFile1.hasNextLine()){
	    	ECset.add(productFile1.nextLine());	
	    }
	    productFile1.close();

	    //System.out.println(AllMap.get("Ga0068821_1000031"));
	    //System.out.println("Write result file");
	    // Get a set of the entries
      	Set set = AllMap.entrySet();
      	// Get an iterator
      	Iterator i = set.iterator();
      	// Display elements
      	//String ResultFile="result.txt";
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
      	
      	
		
      
	    /*
	    
	   ArrayList<String> des = AllMap.get("Ga0068821_1000046");
	   des.add("ex");
	   AllMap.put("Ga0068821_1000011",des);
	   
	   Set read = AllMap.entrySet();
		Iterator iw = read.iterator();
      // Display elements
      //while(iw.hasNext()) {
        // Map.Entry me = (Map.Entry)iw.next();
         //System.out.print(me.getKey() + ": ");
         //System.out.println(me.getValue());
      //}
      System.out.println(AllMap.get("Ga0068821_1000011"));
      // Create a hash map
      HashMap hm = new HashMap();
      // Put elements to the map
      hm.put("Zara", new Double(3434.34));
      hm.put("Mahnaz", new Double(123.22));
      hm.put("Ayan", new Double(1378.00));
      hm.put("Daisy", new Double(99.22));
      hm.put("Qadir", new Double(-19.08));
      
      // Get a set of the entries
      Set set = hm.entrySet();
      // Get an iterator
      Iterator i = set.iterator();
      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
      }
      System.out.println();
      // Deposit 1000 into Zara's account
      double balance = ((Double)hm.get("Zara")).doubleValue();
      hm.put("Zara", new Double(balance + 1000));
      System.out.println("Zara's new balance: " +
      hm.get("Zara"));
      
      */
      
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