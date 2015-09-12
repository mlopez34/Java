
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
   private WeightedQuickUnionUF wuf;
   private int n;
   private Boolean[] grid;
   
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   {
       if (N <= 0)
       {
           throw new java.lang.IllegalArgumentException();
       }
       n = N;
       wuf = new WeightedQuickUnionUF(N*N+2);
       grid = new Boolean[N*N+2];
       for (int i = 0; i < grid.length; i++)
       {
           grid[i] = false;
           //StdOut.println(grid[i]);
       }
       //  open the virtual top and virtual bottom
       grid[0] = true;
       grid[(N*N)+1] = true;
   }
   
   private void validate(int i, int j)
   {
       if (i <= 0 || i > n || j <= 0 || j > n)
       {
           StdOut.println(i +" " + j);
           throw new IndexOutOfBoundsException("i or j are out of bounds");
       }
       return;
   }
   
   private int convertToOneDim(int i, int j)
   {
       //  returns the index of the selected cell
       return (n * (i - 1)) + j;
   }
   
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   {
       //StdOut.println(i +" " + j);
       validate(i, j);
       int openCell = convertToOneDim(i, j);
       //  connect the site in the wuf
       
       if (!grid[openCell])
       {
           grid[openCell] = true;
           if (openCell > 0 && openCell <= n)     //  top row
           {
               //  connect the site to virtual top
               //StdOut.println("checking top" + openCell);
               wuf.union(0, openCell);
           }
           if (openCell > ((n-1)*n) && openCell <= n*n)
           {
               //  connect the site to a virtual bottom
               wuf.union(openCell, (n*n)+1);
           }
           // connect to any nearby sites
           if (i > 1 && isOpen(i-1,j))
           {
               // check top
               wuf.union(openCell, openCell-n);
           }
           if (i <= n-1 && isOpen(i+1, j))
           {
               // check bottom
               //StdOut.println("checking bot" + openCell);
               wuf.union(openCell, openCell+n);
           }
           if (j > 1 && isOpen(i, j-1))
           {
               // check left
               wuf.union(openCell, openCell-1);
           }
           if (j <= n-1 &&  isOpen(i, j+1))
           {
               // check right
               wuf.union(openCell, openCell+1);
           }
       }
   }
   
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
       validate(i, j);
       int checkCell = convertToOneDim(i, j);
       return grid[checkCell];
   }
   
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
       // is the site connected to the top cell
       validate(i, j);
       int checkCell = convertToOneDim(i, j);
       //return true;
       return wuf.connected(0, checkCell);
       
   }
   
   public boolean percolates()             // does the system percolate?
   {
       return wuf.connected(0, (n*n)+1);
   }

   public static void main(String[] args)  // test client (optional)
   {
       Percolation p = new Percolation(2);
       //StdOut.println(p.percolates());
       p.open(2, 2);
       StdOut.println(p.isFull(2, 2));
       StdOut.println(p.percolates());
       p.open(1, 2);
       StdOut.println(p.isFull(1, 2));
       StdOut.println(p.percolates());
       //StdOut.println(p.isFull(3, 1));
       //StdOut.println(p.percolates());
   }
}