/**
 *
 * @author Kevin Rojas B
 */

import edu.duke.*;
import java.io.File;

public class PerimeterRunner {
    public double getLargestX(Shape s) {
        double maxX = 0.0;
        for (Point pts : s.getPoints()) {
            int x = pts.getX();
            if(x>maxX){
                maxX = x;
            }
        }

        return maxX;
    }
    public double getLargestSide(Shape s) {
        double maxlen = 0.0;

        Point prevPt = s.getLastPoint();
        for (Point pts : s.getPoints()) {
            double currDist = prevPt.distance(pts);
            if(currDist>maxlen){
                maxlen = currDist;
            }
            prevPt = pts;
        }

        return maxlen;
    }
    public double getAverageLength(Shape s) {
        double totalpoints = 0.0;
        double total = 0.0;        
        Point prevPt = s.getLastPoint();
        for (Point pts : s.getPoints()) {
            double currDist = prevPt.distance(pts);
            total += currDist;
            totalpoints += 1;
            prevPt = pts;
        }
        double avg = total/totalpoints;
        return avg;
    }
    public int getNumPoints(Shape s) {
        int totalpoints = 0;
        for (Point pts : s.getPoints()) {
            totalpoints += 1;
        }
        return totalpoints;
    }
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int countpoints = getNumPoints(s);
        double avglen = getAverageLength(s);
        double maxlen = getLargestSide(s);
        double maxX = getLargestX(s);
        
        System.out.println("perimeter = " + length);
        System.out.println("points = " + countpoints);
        System.out.println("average length = " + avglen);
        System.out.println("max length = " + maxlen);
        System.out.println("max X = " + maxX);
    }
    
    public double getLargestPerimeterMultipleFiles(){
        DirectoryResource dr = new DirectoryResource();
        double maxPeri = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if(peri>maxPeri){
                maxPeri = peri;
            }
        }
        return maxPeri;
    }
    
    public void testPerimeterMultipleFiles(){
        double maxperi = getLargestPerimeterMultipleFiles();
        System.out.println("max Perimeter = " + maxperi);
    }
    
    public void testFileWithLargestPerimeter(){
        File maxperi = getFileWithLargestPerimeter();
        System.out.println("File = " + maxperi);
    }
    
    public File getFileWithLargestPerimeter(){
        DirectoryResource dr = new DirectoryResource();
        double maxPeri = 0;
        File file  = new File("test.txt");
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if(peri>maxPeri){
                maxPeri = peri;
                file = f;
            }
        }
        return file; 
    
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
