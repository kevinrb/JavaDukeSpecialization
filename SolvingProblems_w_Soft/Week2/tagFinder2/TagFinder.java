/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    
    public StorageResource getAllGenes(String dna){
        String temp = "";
        StorageResource sr = new StorageResource();
        do {
            temp = findGene(dna);
            int newposition = dna.indexOf(temp) + temp.length();
            dna = dna.substring(newposition);
            if(temp!=""){
                sr.add(temp);
            }
        }while(temp != "");
        return sr;
    }

    public float cgRatio(String dna){
        dna = dna.toLowerCase();
        
        int Camt=0;
        int currIndexC= dna.indexOf("c");
        while(true) {
                    if (currIndexC==-1)
                    {break;}
                    Camt= Camt+1;
                    currIndexC= dna.indexOf("c",currIndexC+1);
        }
         int currIndexG= dna.indexOf("g");
        while(true) {
                    if (currIndexG==-1)
                    {break;}
                    Camt= Camt+1;
                    currIndexG= dna.indexOf("g",currIndexG+1);
        }
        
        float total = dna.length();
        float div= Camt/total;
        return div;
    
    
    }
    public int cgrmas035(String dna){
        dna = dna.toLowerCase();
        String temp = "";
        int count = 0;
        //System.out.println(temp);
        do{
            temp = findGene(dna);
            int newposition = dna.indexOf(temp) + temp.length();
            dna = dna.substring(newposition);
            if(cgRatio(temp)>0.35){
                count += 1;
            }
            //System.out.println(temp +"-----"+count);
        }while(temp != "");
        return count;
    
    }
    
    public int countCTG(String dna){
        dna = dna.toLowerCase();
        int Css=dna.split("ctg").length-1;
        return Css;
    
    }
    
    public int countGenes(String dna){
        dna = dna.toLowerCase();
        String temp = "";
        int count = -1;
        //System.out.println(temp);
        do{
            temp = findGene(dna);
            int newposition = dna.indexOf(temp) + temp.length();
            dna = dna.substring(newposition);
            
                count += 1;
           // System.out.println(temp+"--+"+dna.length());
            //System.out.println(temp +"-----"+count);
        }while(temp != "");
        return count;
    
    }
    
    public int maxGen(String dna){
        dna = dna.toLowerCase();
        String temp = "";
        int max = 0;
        //System.out.println(temp);
        do{
            temp = findGene(dna);
            int newposition = dna.indexOf(temp) + temp.length();
            dna = dna.substring(newposition);
            
            if(temp.length()>max){
                max = temp.length();
            }
            
            //System.out.println(temp +"-----"+count);
        }while(temp != "");
        return max;
    
    }
    
    public int genmas60(String dna){
        dna = dna.toLowerCase();
        String temp = "";
        int count = 0;
        //System.out.println(temp);
        do{
            temp = findGene(dna);
            int newposition = dna.indexOf(temp) + temp.length();
            dna = dna.substring(newposition);
            if(temp.length()>60){
                count += 1;
            }
            //System.out.println(temp +"-----"+count);
        }while(temp != "");
        return count;
    
    }
    
    public int howMany(String stringa,String stringb){
        int position = stringb.indexOf(stringa);
        int count = -1;
        while(position != -1){
            int newpos = position + stringa.length();
            stringb = stringb.substring(newpos);
            count += 1;
            position = stringb.indexOf(stringa);
        }
        return count;
    }
    
    public void printAllGenes(String dna){
        dna = dna.toLowerCase();
        String temp = "";
        do {
            temp = findGene(dna);
            int newposition = dna.indexOf(temp) + temp.length();
            dna = dna.substring(newposition);

            
        }while(temp != "");
    
    
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        dna = dna.toLowerCase();
        stopCodon = stopCodon.toLowerCase();
        int indexCodon = dna.length();
        int indextem = dna.indexOf(stopCodon,startIndex+3);
        
        while(indextem != -1){
            if(indextem != -1 && (indextem-startIndex)%3==0){
                indexCodon = indextem;
                break;
            }else{
                indextem = dna.indexOf(stopCodon,indextem+3);
            }
        }
        return indexCodon;
    }
    
    public String findGene(String dna){
        String Gene = "";
        dna = dna.toLowerCase();
        int start = dna.indexOf("atg");
        if (start == -1){}
        else
        {
            int postaa = findStopCodon(dna,start,"TAA");
            int postag = findStopCodon(dna,start,"TAG");
            int postga = findStopCodon(dna,start,"TGA");
            int minpos =Math.min(Math.min(postaa,postag),postga);
            if(minpos==dna.length()){
            
            }
            else{
                Gene =  dna.substring(start, minpos+3);
            }
        }
        return Gene;
    }
    
    public String findProtein(String dna) {
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public void testing() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        int start = a.indexOf("atg");
        String gene = findGene(a);
        int d = findStopCodon(a,start,"TAA");
        int dg = findStopCodon(a,start,"TAG");
        int da = findStopCodon(a,start,"TGA");
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length() + " codon:"+
            d + " codon2:" + dg+ " codon3:"+ da + " Gene:"+ gene );
            printAllGenes(a);
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }
    
    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            int start = s.indexOf("atg");
            int d = findStopCodon(s,start,"TAA");
            int dg = findStopCodon(s,start,"TAG");
            int da = findStopCodon(s,start,"TGA");
            //System.out.println("found " + result + " codon:"+
            //d + " codon2:" + dg+ " codon3:"+ da);
            printAllGenes(s);
            int c = countGenes(s);
            System.out.println(c);
            int cc = genmas60(s);
            System.out.println(cc);
            float dd = cgrmas035(s);
            System.out.println(dd);          
            int pp = countCTG(s);
            System.out.println(pp);
            int mm = maxGen(s);
            System.out.println(mm);
            
        }
    }
}
