import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import java.lang.IllegalArgumentException;

public class PercolationStats {
   
   private double[] total;
   private int t;
   
   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
   {
       if (N <=0 || T <= 0)
       {
           throw new java.lang.IllegalArgumentException();
       }
       t = T;
       total = new double[T];
       // create a percolation test
       for (int i = 0; i < T; i++)
       {
           //StdOut.println("asfd" + i);
           double count = 0;
           Percolation p = new Percolation(N);
           while(!p.percolates())
           {
               // randomly open a site until it percolates
               int row = StdRandom.uniform(N)+1;
               int col = StdRandom.uniform(N)+1;
               if (!p.isOpen(row, col))
               {
                   p.open(row, col);
                   count++;
               }
           }
           // store the number of times it took to open a site for each percolation test
           //StdOut.println(count/(N*N));
           total[i] = count/(N*N);
       }
       //StdOut.println("done");
   }
   public double mean()                      // sample mean of percolation threshold
   {
       double m = StdStats.mean(total);
       return m;
   }
   public double stddev()                    // sample standard deviation of percolation threshold
   {
       if (t == 1)
       {
           return Double.NaN;
       }
       double s = StdStats.stddev(total);
       return s;
   }
   public double confidenceLo()              // low  endpoint of 95% confidence interval
   {
       double clo = mean() - ((1.98*stddev())/Math.sqrt(t));
       return clo;
   }
   public double confidenceHi()              // high endpoint of 95% confidence interval
   {
       double chi = mean() + ((1.98*stddev())/Math.sqrt(t));
       return chi;
   }

   public static void main(String[] args)    // test client (described below)
   {
       PercolationStats ps = new PercolationStats(200, 100);
       StdOut.println("mean                  = " + ps.mean());
       StdOut.println("stddev                  = " + ps.stddev());
       StdOut.println("95% confidence interval                  = " + ps.confidenceLo() + " " + ps.confidenceHi());
       
   }
}