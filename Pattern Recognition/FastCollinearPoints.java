import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;

public class FastCollinearPoints {
   private int N = 0;
   private ArrayList<LineSegment> ls;
   private HashMap<Double, ArrayList<Point>> map;
   private Point[] points2;
   
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   {
       points2 = new Point[points.length];
       System.arraycopy(points, 0, points2, 0, points2.length);
       
       if (points == null)
       {
           throw new java.lang.NullPointerException();
       }
       checkRepeats(points);
       ls = new ArrayList();
       map = new HashMap<Double, ArrayList<Point>>();
       for (int i = 0; i < points2.length; i++)
       {
           validate(points2);
           Boolean done = false;
           Arrays.sort(points2, points[i].slopeOrder());
           int index = 0;
           
           while(index < points2.length){
               double currentSlope = points[i].slopeTo(points2[index]);
               int count = 0;
               int initial = index;
               boolean skipped = false;
               while(index < points2.length && points[i].slopeTo(points2[index]) == currentSlope)
               {
                   count++;
                   index++;
                   skipped = true;
               }
               if (count >= 3)
               {
                   Point[] pointsInLine = new Point[count+1];
                   pointsInLine[0] = points[i];
                   for (int k = 0; k < count; k++)
                   {
                       pointsInLine[k+1] = points2[initial+k];
                   }
                   Arrays.sort(pointsInLine);
                   LineSegment line = new LineSegment(pointsInLine[0], pointsInLine[pointsInLine.length-1]);
                   
                   if (!map.containsKey(currentSlope))
                   {
                       //point is not even in a segment check if 
                       ls.add(line);
                       ArrayList<Point> temp = new ArrayList<Point>();
                       temp.add(pointsInLine[pointsInLine.length-1]);
                       map.put(currentSlope, temp);
                       N++;
                   }
                   else if ( map.containsKey(currentSlope))
                   {
                       ArrayList<Point> temp = map.get(currentSlope);
                       if (temp.contains(pointsInLine[pointsInLine.length-1]))
                       {
                           continue;
                       }
                       ls.add(line);
                       N++;
                       temp.add(pointsInLine[pointsInLine.length-1]);
                       map.put(currentSlope, temp);
                   }
                   if (pointsInLine.length >= points.length)
                   {
                       //Nx1 grid or 1xN grid
                       done = true;
                       break;
                   }
               }
               if(!skipped){
                   index++;
               }
           }
           if (done)
           {
               break;
           }
       }
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
   private void validate(Point[] p)
   {
       for (int i = 0; i < p.length-1; i++)
       {
           if (p == null)
           {
               throw new java.lang.IllegalArgumentException();
           }
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