import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private class Node{
        private Item item;
        private Node next;
        private Node prev;
        
        public Node(Item i, Node n, Node p)
        {
            this.item = i;
            this.next = n;
            this.prev = p;
        }
    }
    
   private Node first;
   private Node last;
   private int N;
   
   public Deque()                           // construct an empty deque
   {
       this.N = 0;
   }
   public boolean isEmpty()                 // is the deque empty?
   {
       return this.N == 0;
   }
   public int size()                        // return the number of items on the deque
   {
       return this.N;
   }
   public void addFirst(Item item)          // add the item to the front
   {
       if (item == null)
       {
           throw new java.lang.NullPointerException();
       }
       Node newFirst = new Node(item, null, null);
       if (first == null)
       {
           first = newFirst;
           last = newFirst;
       }
       else{
           Node temp = first;
           first = newFirst;
           newFirst.next = temp;
           temp.prev = first;
       }
       N++;
   }
   public void addLast(Item item)           // add the item to the end
   {
       if (item == null)
       {
           throw new java.lang.NullPointerException();
       }
       Node newLast = new Node(item, null, null);
       if (last == null)
       {
           last = newLast;
           first = newLast;
       }
       else{
           last.next = newLast;
           newLast.prev = last;
           last = newLast;
       }
       N++;
       
   }
   public Item removeFirst()                // remove and return the item from the front
   {
       Node tmp;
       if (isEmpty())
       {
           throw new java.util.NoSuchElementException();
       }
       if (size() == 1)
       {
           tmp = first;
           first = null;
           last = null;
       }
       else{
           tmp = first;
           first = first.next;
           first.prev = null;
       }
       N--;
       return tmp.item;
       
   }
   public Item removeLast()                 // remove and return the item from the end
   {
       Node tmp;
       if (isEmpty())
       {
           throw new java.util.NoSuchElementException();
       }
       if (size() == 1)
       {
           tmp = last;
           first = null;
           last = null;
       }
       else{
           tmp = last;
           last = last.prev;
           last.next = null;
       }
       N--;
       return tmp.item;
   }
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
       //StdOut.println("here");
       return new DequeIterator(first);
   }
   
   private class DequeIterator implements Iterator<Item>{
       private Node current;
       
       public DequeIterator(Node n)
       {
           this.current = n;
       }
       public boolean hasNext()
       {
           return current != null;
       }
       public void remove()
       {
           throw new java.lang.UnsupportedOperationException();
       }
       public Item next()
       {
           //StdOut.println("here");
           if (!hasNext())
           {
               //StdOut.println("here2");
               throw new java.util.NoSuchElementException();
           }
           Item tmp = (Item)current.item;
           current = current.next;
           return tmp;
       }
   }
   public static void main(String[] args)   // unit testing
   {
       
   }
   
}