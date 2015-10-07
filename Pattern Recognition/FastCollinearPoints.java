import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
   private int N = 0;
   private ArrayList<LineSegment> ls;
   
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   {
       if (points == null)
       {
           throw new java.lang.NullPointerException();
       }
       ls = new ArrayList();
       for (int i = 0; i < points.length; i++)
       {
           validate(points[i]);
           
           Point[] toSort = new Point[points.length-1];
           int toSortIndex = 0;
           
           for (int j = 0; j < points.length; j++)
           {
               validate(points[j]);
               if (i != j)
               {
                   //StdOut.println( i + " " + j);
                   toSort[toSortIndex] = points[j];
                   toSortIndex++;
               }
           }
           Arrays.sort(toSort, points[i].slopeOrder());
           
           int index = 0;
           double currentSlope = points[i].slopeTo(toSort[index]);
           
           while(index < toSort.length){
               currentSlope = points[i].slopeTo(toSort[index]);
               int count = 0;
               int initial = index;
               while(index < toSort.length && points[i].slopeTo(toSort[index]) == currentSlope){
                   count++;
                   index++;
               }
               if (count >= 3)
               {
                   Point[] pointsInLine = new Point[count+1];
                   pointsInLine[0] = points[i];
                   for (int k = 0; k < count; k++)
                   {
                       pointsInLine[k+1] = toSort[initial+k];
                   }
                   Arrays.sort(pointsInLine);
                   LineSegment line = new LineSegment(pointsInLine[0], pointsInLine[pointsInLine.length-1]);
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
               index++;
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
       FastCollinearPoints collinear = new FastCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
   }
}