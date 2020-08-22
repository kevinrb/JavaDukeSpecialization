/**
 * Print out total number of babies born, as well as for each gender,
 * in a given CSV file of baby name data.
 * @author Kevin Rojas B 
 * @version 1.0
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class babynames {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBirthNames = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalBirthNames +=1;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames += 1;
            }
            else {
                totalGirls += numBorn;
                totalGirlNames += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total names = " + totalBirthNames);
        System.out.println("female names = " + totalGirlNames);
        System.out.println("male names = " + totalBoyNames);
    }
    
    public void test(){
        //FileResource fr = new FileResource();
        //totalBirths(fr);
        System.out.println(getRank(1960,"Emily","F"));
        System.out.println(getRank(1971,"Frank","M"));
        System.out.println(getName(1980,350,"F"));
        System.out.println(getName(1982,450,"M"));
        whatIsNameInYear("Susan",1972,2014,"F");
        whatIsNameInYear("Owen",1974,2014,"M");
        //System.out.println(yearOfHighestRank("Genevieve","F"));
        //System.out.println(yearOfHighestRank("Mich","M"));
        System.out.println(getAverageRank("Susan","F"));
        System.out.println(getAverageRank("Robert","M"));
        System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    
    public int getRank(int year,String name,String gender){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rank=0;
        int tag=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                rank+=1;
                if(rec.get(0).equals(name)){
                    tag=1;
                    break;
                }
            }
        }
        if(tag==0){
            rank=-1;
        }
        
        return rank;
    }
    
    public String getName(int year,int rank,String gender) {
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rankc=0;
        String name="NO NAME";
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                rankc+=1;
                if(rankc==rank){
                    name=rec.get(0);
                    break;
                }
            }
        }
        
        return name;
    }
    
    public void whatIsNameInYear(String name, int year,int newYear,String gender){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rankc=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                rankc+=1;
                if(rec.get(0).equals(name)){
                    
                    break;
                }
            }
        }
        
        
        String newname = getName(newYear,rankc,gender);
        
        System.out.println(name+" born in "+ year + " would be "+ newname + " if she was born in "+newYear);
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int maxpos=100000;
        int yearhigh=-1;
        for(File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int pos = getRank(year,name,gender);
            if(pos>0 && pos<maxpos){ 
                maxpos=pos;
                yearhigh = year;
            }
            
            
        
        }
    
        return yearhigh;
    }
    
    public float getAverageRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        float total=0;
        float sum=0;
        float avg=-1;
        for(File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int pos = getRank(year,name,gender);
            if(pos>0){ 
                total +=1;
                sum +=pos ;
            }
            if(total>0){
                avg= sum/total;
            }
            
        }
    
        return avg;
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        int rankname=getRank(year,name,gender);
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rank=0;
        int totalbirths=0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                rank+=1;
                
                if(rank>=rankname){
                    break;
                }
                totalbirths+=Integer.parseInt(rec.get(2));
            }
        }

        
        return totalbirths;
    }
}
