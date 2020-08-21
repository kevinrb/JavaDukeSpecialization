
/**
 * @author Kevin Rojas Bohorquez
 * @version 1.0
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        float min=1000;
        CSVRecord minrec= null;
        for(CSVRecord record : parser){
            float tem = Float.parseFloat(record.get("TemperatureF"));
            if(tem!=-9999 && tem<min){
                minrec = record;
                min=tem;
            }
        }
        return minrec;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord tem = coldestHourInFile(parser);
        System.out.println(tem.get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        float Min = 1000;
        String fmin= "";
        for(File f : dr.selectedFiles()) {
            FileResource fr= new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord cold = coldestHourInFile(parser);
            if(Min == 1000){
                Min = Float.parseFloat(cold.get("TemperatureF"));
                fmin= f.getName();
            
            }
            else if(Min > Float.parseFloat(cold.get("TemperatureF"))){
                Min = Float.parseFloat(cold.get("TemperatureF"));
                fmin=f.getName();
            }
        }
        return fmin;
    }
    
    public void testFileWithColdestTemperature(){
        String max=fileWithColdestTemperature();
        System.out.println(max);
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        float min=1000;
        CSVRecord minrec= null;
        for(CSVRecord record : parser){
            String humstr =record.get("Humidity");
            if(!humstr.equals("N/A")){
                float hum = Float.parseFloat(humstr);
                if( hum<min){
                    minrec = record;
                    min=hum;
                }
            }      
        }
        return minrec;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Result");
        System.out.println(csv.get("Humidity")+"---"+csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        float Min = 1000;
        CSVRecord fmin= null;
        for(File f : dr.selectedFiles()) {
            FileResource fr= new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowhum = lowestHumidityInFile(parser);
            if(Min == 1000){
                Min = Float.parseFloat(lowhum.get("Humidity"));
                fmin= lowhum;
            
            }
            else if(Min > Float.parseFloat(lowhum.get("Humidity"))){
                Min = Float.parseFloat(lowhum.get("Humidity"));
                fmin=lowhum;
            }
        }
        return fmin;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowmany= lowestHumidityInManyFiles();
        System.out.println(lowmany.get("Humidity")+"---"+lowmany.get("DateUTC"));
    }
    
    public double averageTemperatureInFile (CSVParser parser){
        float sum=0;
        float total=0;
        for(CSVRecord record : parser){
            float tem = Float.parseFloat(record.get("TemperatureF"));
            if(tem!=-9999 ){
                sum += tem;
                total +=1;
            }
        }
        
        return sum/total;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double avg= averageTemperatureInFile(parser);
        System.out.println(avg);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        float sum=0;
        float total=0;
        for(CSVRecord record : parser){
            float tem = Float.parseFloat(record.get("TemperatureF"));
            float hum = Float.parseFloat(record.get("Humidity"));
            if(tem!=-9999 && hum>=value ){
                sum += tem;
                total +=1;
            }
        }
        
        return sum/total;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double avg= averageTemperatureWithHighHumidityInFile(parser,80);
        System.out.println(avg);
    }

}
