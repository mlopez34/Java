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
       ls = new ArrayList();
       for (int i = 0; i < points.length; i++)
       {
           if (points[i] == null)
           {
               throw new java.lang.NullPointerException();
           }
           for ( int j = i+1; j < points.length; j++)
           {
               if (points[j] == null)
               {
                   throw new java.lang.NullPointerException();
               }
               for (int k = j+1; k < points.length; k++)
               {
                   if (points[k] == null)
                   {
                       throw new java.lang.NullPointerException();
                   }
                   if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])){
                       
                       for (int l = k+1; l < points.length; l++)
                       {
                           if (points[l] == null)
                           {
                               throw new java.lang.NullPointerException();
                           }
                           if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[l]))
                           {
                               
                               //get the min and max pts in the line seg
                               
                               Point[] temp = new Point[] {points[i], points[j], points[k], points[l]};
                               //StdOut.println(temp[0] + "->" + temp[1] + "->" + temp[2]+ "->" + temp[3] + " slope i to k " + 
                                              //points[i].slopeTo(points[k]) + " slope i to l " + points[i].slopeTo(points[l]));
                               Arrays.sort(temp);
                               //StdOut.println("sorted " + temp[0] + "->" + temp[1] + "->" + temp[2]+ "->" + temp[3]);
                               Point min = temp[0];
                               Point max = temp[3];
                               LineSegment line = new LineSegment(min, max);
                               Boolean found = false;
                               if (ls.size() == 0)
                               {
                                   ls.add(line);
                                   N = N+1;
                               }
                               else{
                                   for (LineSegment seg : ls)
                                   {
                                       if (seg.toString().equals(line.toString()))
                                       {
                                           found = true;
                                           break;
                                       }
                                   }
                                   if (!found)
                                   {
                                       ls.add(line);
                                       N = N+1;
                                   }
                               }
                               
                           }
                       }
                   }
               }
           }
       }
       //System.out.println(N);
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