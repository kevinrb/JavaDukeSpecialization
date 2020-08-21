
/**
 * Write a description of Exporter here.
 * 
 * @author Kevin Rojas B 
 * @version 1.0
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Exporter {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryinfo(parser,"Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    
    public String countryinfo(CSVParser parser,String country){
        String ret="";
        for(CSVRecord record : parser){
            String pais = record.get("Country");
         
            if(pais.equals(country)){
                ret = record.get("Country") + ": " +
                record.get("Exports") +" "+ record.get("Value (dollars)");
                break;
            }
            else{
                ret = "NOT FOUND";
            }
        }
        return ret;
    }
    
    public void listExportersTwoProducts(CSVParser parser,String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.indexOf(exportItem1)!=-1 && export.indexOf(exportItem2)!=-1){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser,String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.indexOf(exportItem)!=-1){
                count +=1;
            }
        
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int len = amount.length();
        for(CSVRecord record : parser){
            int len2 = record.get("Value (dollars)").length();
            if(len2>len){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    
    }

}
