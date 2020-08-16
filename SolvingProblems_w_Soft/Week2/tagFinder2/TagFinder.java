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
    
    public void printAllGenes(String dna){
        
        String temp = findGene(dna);
        System.out.println(temp);
        while(temp != ""){
            int newposition = dna.indexOf(temp) + temp.length();
            dna = dna.substring(newposition);
            temp = findGene(dna);
            System.out.println(temp);
        }
    
    
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
            int postaa = findStopCodon(dna,start,"TAA")+3;
            int postag = findStopCodon(dna,start,"TAG")+3;
            int postga = findStopCodon(dna,start,"TGA")+3;
            int minpos =Math.min(Math.min(postaa,postag),postga);
            if(minpos==dna.length()){
            
            }
            else{
                Gene =  dna.substring(start, minpos);
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
            System.out.println("found " + result + " codon:"+
            d + " codon2:" + dg+ " codon3:"+ da);
            printAllGenes(s);
        }
    }
}
