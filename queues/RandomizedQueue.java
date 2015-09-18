import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
   
   private Item[] randQ;
   private int N;
    
   public RandomizedQueue()                 // construct an empty randomized queue
   {
       this.N = 0;
       this.randQ = (Item[])new Object[1];
   }
   public boolean isEmpty()                 // is the queue empty?
   {
       return this.N == 0;
   }
   public int size()                        // return the number of items on the queue
   {
       return this.N;
   }
   public void enqueue(Item item)           // add the item
   {
       if (item == null)
       {
           throw new java.lang.NullPointerException();
       }
       if (size() == randQ.length)
       {
           //  resize to twice as big
           Item[] newQ = (Item[])new Object[N*2];
           for (int i = 0; i < randQ.length; i++)
           {
               newQ[i] = randQ[i];
           }
           randQ = newQ;
       }
       randQ[N] = item;
       N++;
   }
   public Item dequeue()                    // remove and return a random item
   {
       if (isEmpty())
       {
           throw new java.util.NoSuchElementException();
       }
       int ran = StdRandom.uniform(N);
       Item tmp = randQ[ran];
       randQ[ran] = randQ[N-1];
       randQ[N-1] = tmp;
       randQ[N-1] = null;
       if (size() == randQ.length/4)
       {
           //  reduce to half the size
           Item[] newQ = (Item[])new Object[randQ.length/2];
           
           for (int i =0; i < newQ.length; i++)
           {
                newQ[i] = randQ[i];
           }
           randQ = newQ;
       }
       N--;
       return tmp;
   }
   
   public Item sample()                     // return (but do not remove) a random item
   {
       if (isEmpty()){
           
           throw new java.util.NoSuchElementException();
       }
       int ran = StdRandom.uniform(N);
       return randQ[ran];
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
       return new RandomIterator();
   }
   
   private class RandomIterator implements Iterator<Item>{
       private Item[] shuffledQ;
       private int i;
       public RandomIterator()
       {
           shuffledQ = (Item[])new Object[N];
           for (int k = 0; k < N; k++)
           {
               shuffledQ[k] = randQ[k];
           }
           StdRandom.shuffle(shuffledQ);
           int i = 0;
       }
       public boolean hasNext()
       {
           return i < N;
       }
       public void remove()
       {
           throw new java.lang.UnsupportedOperationException();
       }
       public Item next()
       {
           if (!hasNext())
           {
               throw new java.util.NoSuchElementException();
           }
           return shuffledQ[i++];
       }
   }
   
   public static void main(String[] args)   // unit testing
   {
       RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
       rq.enqueue(0);
       rq.enqueue(1);
       rq.enqueue(2);
       rq.enqueue(3);
       rq.enqueue(4);
       rq.enqueue(5);
       rq.enqueue(6);
       rq.enqueue(7);
       rq.enqueue(8);
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       StdOut.println(rq.dequeue());
       
   }
}