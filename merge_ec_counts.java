//javac merge_ec_counts.java
//javac merge_ec_counts ~/globus/meta.txt

import java.io.*;
import java.util.*;

public class merge_ec_counts {
	
   public static void main(String args[]) {
   	try{
   	
   	
   		//step 1 read metafile
   		ArrayList<ArrayList<String>> meta = new ArrayList<ArrayList<String>>();
   		read_table(meta,args[0],true);
   		//print_2d_table(meta);

   		HashMap<String, ArrayList<String>> ECmap = new HashMap <String,ArrayList<String>>();
   		String filename;
   		
   		for (int i =0;i<meta.size();i++){
   			filename = "/Users/jinchoi/globus/"+meta.get(i).get(3)+".assembled.faa.EC.count.txt";
   			//System.out.println(filename);
   			AddHashMap_with_list(ECmap,filename,2,3,i);
   		}
   		
   		
   		//print map
   		PrintStream Result = new PrintStream("EC.all.count.txt");
   		Result.print("EC");
   		for (int i = 0;i<meta.size();i++){
   			Result.print("\t"+meta.get(i).get(1)+"_"+meta.get(i).get(2));
   		}
   		Result.println();
   		Set set = ECmap.entrySet();
	    Iterator i = set.iterator();
	    ArrayList<String> des;
	    while(i.hasNext()){
	    	Map.Entry me = (Map.Entry)i.next();
	    	Result.print(me.getKey()+"\t");
	    	des = ECmap.get(me.getKey());
	    	for(int j=0;j<des.size();j++){
	    		Result.print(des.get(j)+"\t");
	    	}
	    	Result.println();
	    	
	    }
   		


      }catch (Exception ex) {
			ex.printStackTrace();
		}//try
      
   }
   
   public static void AddHashMap_with_list (HashMap<String, ArrayList<String>> AllMap, String filename, int key, int value, int iter) {
   		try{
   			File productFile = new File(filename);
	    	Scanner productFile1 = new Scanner(productFile);
	    	ArrayList<String> des;
	    	while (productFile1.hasNextLine()){
	    		
	    		String line = productFile1.nextLine();
	    		String[] parts = line.split("\t");
	    		if(AllMap.containsKey(parts[key])){
					des = AllMap.get(parts[key]);
	    		}else{
	    			des = new ArrayList<String>();
	    			//System.out.println(parts[key]);
	    			for (int i=0;i<iter;i++){
	    				des.add("0");
	    			}
	    		}
	    		if(parts[value].length()>0){
	    			if(des.size() > iter){
	    				float tempfloat = Float.parseFloat(parts[value]) + Float.parseFloat(des.get(iter));
	    				des.remove(iter);
	    				des.add(Float.toString(tempfloat));
	    			}else{
	    				des.add(parts[value]);
	    			}
	    			AllMap.put(parts[key], des);
	    		}	
	    	}
	    	productFile1.close();
	    	
	    	//check zero
	    	Set set = AllMap.entrySet();
	    	Iterator i = set.iterator();
	    	while(i.hasNext()){
	    		Map.Entry me = (Map.Entry)i.next();
	    		des = AllMap.get(me.getKey());
	    		if(des.size()-1 != iter){
	    			des.add("0");
	    			//System.out.println(des.size());
	    			String keyname = me.getKey().toString();
	    			AllMap.put(keyname,des);
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
   
   public static void read_table (ArrayList<ArrayList<String>> list,String filename,boolean header) {
   		try{
   			File productFile = new File(filename);
   			ArrayList<String> temp;
	    	Scanner productFile1 = new Scanner(productFile);
	    	if(header){productFile1.nextLine();}
	    	while (productFile1.hasNextLine()){
	    		String line = productFile1.nextLine();
	    		String[] parts = line.split("\t"); 
	    		  	
	    		temp = new ArrayList<String>();	
	    		for (int i=0;i<parts.length;i++){
	    			temp.add(parts[i]);
	    			
	    		}
	    		list.add(temp);
	    	}
	    	productFile1.close();
	    
	    }catch (Exception ex) {
			ex.printStackTrace();
		}//try
   }
   
   public static void print_2d_table (ArrayList<ArrayList<String>> list) {
   		for (int i =0;i<list.size();i++){
   			for (int j=0;j<list.get(i).size();j++){
   				System.out.print(list.get(i).get(j)+"\t");
   			}
   			System.out.println();
   		}
   }
   
   
}