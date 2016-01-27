// java subset_ec_interested EC.all.count.txt ec_interested.txt ec_count_subset.txt

import java.io.*;
import java.util.*;

public class subset_ec_interested {
    public static void main(String args[]){
	if(args.length != 3){
	    System.err.println("usage: java subset_ec_interested full_table_file ec_interested output_ec_table");
	    System.exit(0);
	}
	//read interested
	File idFile = new File(args[1]);
	//put into set
	Set<String> ec_id = new HashSet<String>();
	try{
	    Scanner idFile_scan = new Scanner(idFile);
	    while(idFile_scan.hasNextLine()){
		ec_id.add(idFile_scan.nextLine());
	    }
	}catch (FileNotFoundException e){
	    e.printStackTrace();
	}
	//System.out.println(ec_id);
	//read all count
	//write only interested
	File seqFile = new File(args[0]);
	try{
	    PrintStream resultOut = new PrintStream(args[2]);
	    Scanner seqFile_scan = new Scanner(seqFile);
	    resultOut.println(seqFile_scan.nextLine());
	    while (seqFile_scan.hasNextLine()){
		String line = seqFile_scan.nextLine();
		String[] parts = line.split("\t");
		if (ec_id.contains(parts[0])){
		    resultOut.println(line);
		}
	    }
	}catch (FileNotFoundException e){
	    e.printStackTrace();
	}
    }
}