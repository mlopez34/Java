import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
   private int N = 0;
   private ArrayList<LineSegment> ls;
   
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
       if (points == null)
       {
           throw new java.lang.NullPointerException();
       }
       checkRepeats(points);
       ls = new ArrayList();
       for (int i = 0; i < points.length; i++)
       {
           validate(points[i]);
           for ( int j = i+1; j < points.length; j++)
           {
               validate(points[j]);
               for (int k = j+1; k < points.length; k++)
               {
                   validate(points[k]);
                   if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])){
                       
                       for (int l = k+1; l < points.length; l++)
                       {
                           validate(points[l]);
                           if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[l]))
                           {
                               //get the min and max pts in the line seg
                               
                               Point[] temp = new Point[] {points[i], points[j], points[k], points[l]};
                               
                               Arrays.sort(temp);
                               //check repeats
                               
                               Point min = temp[0];
                               Point max = temp[3];
                               LineSegment line = new LineSegment(min, max);
                               
                               ls.add(line);
                               //N = N+1;
                               
                           }
                       }
                   }
               }
           }
       }
       ArrayList<LineSegment> noDups = new ArrayList<LineSegment>();
       for (LineSegment segment : ls)
       {
           if (noDups.size() == 0)
           {
               noDups.add(segment);
               N = N+1;
           }
           else{
               Boolean found = false;
               for (LineSegment seg : noDups)
               {
                   if (seg.toString().equals(segment.toString()))
                   {
                       found = true;
                       break;
                   }
               }
               if (!found)
               {
                   noDups.add(segment);
                   N = N+1;
               }
           }
       }
       ls = noDups;
       //System.out.println(N);
   }
   private void checkRepeats(Point[] points)
   {
       Point[] copy = new Point[points.length];
       for (int i = 0; i < copy.length; i++)
       {
           copy[i] = points[i];
       }
       Arrays.sort(copy);
       for (int i = 0; i < copy.length-1; i++)
       {
           if (copy[i].compareTo(copy[i+1]) == 0)
           {
               throw new java.lang.IllegalArgumentException();
           }
       }
   }
   private void validate(Point p)
   {
       if (p == null)
       {
           throw new java.lang.NullPointerException();
       }
   }
   public int numberOfSegments()        // the number of line segments
   {
       return this.N;
   }
   public LineSegment[] segments()                // the line segments
   {
       LineSegment[] s = new LineSegment[N];
       
       s = ls.toArray(s);
       return s;
   }
   public static void main(String[] args) {

       // read the N points from a file
       In in = new In(args[0]);
       int N = in.readInt();
       Point[] points = new Point[N];
       for (int i = 0; i < N; i++) {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x, y);
       }
       
       // draw the points
       StdDraw.show(0);
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       for (Point p : points) {
           p.draw();
       }
       StdDraw.show();
       
       // print and draw the line segments
       BruteCollinearPoints collinear = new BruteCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
   }
}