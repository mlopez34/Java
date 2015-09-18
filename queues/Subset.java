import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Subset {
   public static void main(String[] args)
   {
       int k = Integer.parseInt(args[0]);
       RandomizedQueue rq = new RandomizedQueue();
       while(!StdIn.isEmpty())
       {
           String s = StdIn.readString();
           if (rq.size() == k && rq.size() > 0)
           {
               rq.dequeue();
           }
           rq.enqueue(s);
       }
       for (int i =0; i < k; i++)
       {
           StdOut.println(rq.dequeue());
       }
       
   }
}