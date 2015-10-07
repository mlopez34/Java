import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {
   
   private final int x;     // x-coordinate of this point
   private final int y;     // y-coordinate of this point
   
   public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
   public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }                             // draws this point
   public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
                  // draws the line segment from this point to that point
   public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }                         // string representation

   public int compareTo(Point that) 
   {
       // compare two points by y-coordinates, breaking ties by x-coordinates
       if (this.y > that.y)
       {
           return 1;
       }
       else if (this.y < that.y)
       {
           return -1;
       }
       else if (this.y == that.y)
       {
           if (this.x > that.x)
           {
               return 1;
           }
           else if (this.x < that.x)
           {
               return -1;
           }
           else{
               return 0;
           }
       }
       return 0;
       
   }
   public double slopeTo(Point that)
   {
       if (that == null)
       {
           throw new java.lang.NullPointerException();
       }
       // the slope between this point and that point
       if (this.x == that.x && this.y != that.y)
       {
           return Double.POSITIVE_INFINITY;
       }
       if (this.y == that.y && this.x != that.x)
       {
           return +0;
       }
       if (this.y == that.y && this.x == that.x)
       {
           return Double.NEGATIVE_INFINITY;
       }
       double slope = (double)(that.y - this.y)/(double)(that.x - this.x);
       
       return slope;
   }
   
   public Comparator<Point> slopeOrder()
   {
       // compare two points by slopes they make with this point
       return new SLOPEORDER();
   }
   private class SLOPEORDER implements  Comparator<Point>{
       
       public int compare(Point p1, Point p2)
       {
           Point p = new Point(x,y);
           double d = p.slopeTo(p1) - p.slopeTo(p2);
           if (d > 0)
           {
               return 1;
           }
           else if (d < 0)
           {
               return -1;
           }
           return 0;
       }
   }
   
}