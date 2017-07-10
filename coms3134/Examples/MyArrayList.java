/**
 * Data Structures in Java 
 * COMS W3134, Columbia University - Fall 2016
 * 
 * MyArrayList implements a generic array list. 
 * Adapted from Weiss, Data Structures and Algorithm Analysis in Java. 3rd ed. 
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/MyLinkedList.java
 */

import java.util.Iterator;

public class MyArrayList<AnyType> implements List<AnyType>, Iterator<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    
    private AnyType [ ] theItems;
    private int theSize;

    /**
     * Construct an empty ArrayList.
     */
    public MyArrayList() {
        clear();
    }
   
    /**
     * Change the size of this list to zero.
     */
    @Override
    public void clear( ) {
        theSize = 0;
        ensureCapacity( DEFAULT_CAPACITY );
    }

    /**
     * @return the number of items in this list. 
     */
    @Override
    public int size( ) {
        return theSize;
    }
    
    /**
     * @return true if this list is empty.
     */ 
    @Override
    public boolean isEmpty( ) {
        return size( ) == 0;
    }
    
    /**
     * @return the item at position idx.
     * @param idx the index to search in.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    @Override
    public AnyType get( int idx ) {
        if( idx < 0 || idx >= size() )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        return theItems[ idx ];    
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    @Override
    public AnyType set( int idx, AnyType newVal ) {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        AnyType old = theItems[ idx ];    
        theItems[ idx ] = newVal;
        
        return old;    
    }

    /**
    * Adds an item to this list, at the end.
    * @param x any object.
    * @return true.
    */
    public boolean add( AnyType x ) {
    add( size(), x );
       return true;            
    }

    /**
    * Adds an item to this list, at the specified index.
    * @param x any object.
    * @return true.
    */
    @Override
    public void add( int idx, AnyType x ) {
       if( theItems.length == size( ) )
           ensureCapacity( size() * 2 + 1 );

       for( int i = theSize; i > idx; i-- )
           theItems[ i ] = theItems[ i - 1 ];

       theItems[ idx ] = x;
       theSize++;  
    }
 
    /**
    * Removes an item from this list.
    * @param idx the index of the object.
    * @return the item was removed from the list.
    */
    @Override
    public AnyType remove( int idx ) {
       AnyType removedItem = theItems[ idx ];
       
       for( int i = idx; i < size( ) - 1; i++ )
           theItems[ i ] = theItems[ i + 1 ];
       theSize--;    
       
       return removedItem;
    }


    /**
    * Returns a String representation of this list.
    */
    @Override
    public String toString( ) {
       StringBuilder sb = new StringBuilder( "[ " );
      
       for(int i=0; i < theSize; i++) 
           sb.append( theItems[i] + " " );
       sb.append( "]" );

       return new String(sb);
    }

    public int find(AnyType x) {
        for (int i=0; i < theSize; i++)
            if (theItems[i].equals(x)) 
                return i;
        return -1;
    }


    private void ensureCapacity( int newCapacity ) {
        if( newCapacity < theSize )
            return;

        AnyType [ ] old = theItems;
        theItems = (AnyType []) new Object[ newCapacity ];
        for( int i = 0; i < size( ); i++ )
            theItems[ i ] = old[ i ];
    }
  

    // Implement the iterator() method required by the Iterable interface
    public Iterator<AnyType> iterator() {
        Iterator<AnyType> iter =  MyArrayListIterator(); 
        return iter;
    }
 

    // Private inner class. Instances of this class have access to the surrounding MyArrayList 
    // instance that created them. 
    private class MyArrayListIterator implements Iterator<AnyType> {

        private int current_position;

        public MyArrayListIterator() {
            current_position = 0;
        }

        public boolean hasNext() {
            return current_position < theSize;
        }
        
        public AnyType next() {
            AnyType result = get(current_position);
            current_position++; 
            return result; 
        }
        
        public void remove() {
            
        }
    }


    public static void main(String[] args) {

        List<Integer> l = new MyArrayList<>(); 
        l.add(0,1);
        l.add(0,2);
        l.add(0,3);
        l.add(0,4);
        l.add(0,5);

        System.out.println(l.toString());

        //Iterator<Integer> iter = l.iterator();
        //while (iter.hasNext()) {
        //    System.out.println(iter.next());
        //}
        for (Integer i : l)
            System.out.println(i);

    }

}
