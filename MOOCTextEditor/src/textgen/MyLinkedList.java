package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		E data = null;
		head = new LLNode(data);
		tail = new LLNode(data);
		
		head.next = tail;
		head.prev = null;
		tail.prev = head;
		tail.next = null;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		try {
			LLNode<E> newElement = new LLNode(element);
			
			newElement.next 	= tail;
			tail.prev.next 		= newElement;
			newElement.prev		= tail.prev;
			tail.prev 			= newElement;
			size+=1;
			
			return true;	
		}
		catch(OutOfMemoryError E) {
			return false;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> element;
			
		if((index < 0) || (index > (size-1))) {
			throw new IndexOutOfBoundsException();
		}
		else {
			element = head.next;
			while(index > 0) {
				element = element.next;
				index--;
			}
			return (element.data);
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		try {
			LLNode<E> itr;
			LLNode<E> temp = new LLNode(element);
			
			if((index < 0) || (index > (size)))
				throw new IndexOutOfBoundsException();
			else {
				itr = head.next;
				while(index > 0) {
					itr = itr.next;
					index--;
				}
			}
			temp.next 		= itr;
			temp.prev 		= itr.prev;
			itr.prev.next 	= temp;
			itr.prev 		= temp;
			size+=1;
		}
		catch(OutOfMemoryError E) {
			throw E;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		LLNode<E> itr;
		LLNode<E> temp;
		E element;
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			if((index < 0) || (index > (size-1)))
				throw new IndexOutOfBoundsException();
			else {
				itr = head.next;
				while(index > 0) {
					itr = itr.next;
					index--;
				}
			}
			
			temp = itr;
			itr.prev.next 	= itr.next;
			itr.next.prev 	= itr.prev;
			element 		= itr.data; 
			size-=1;
			
			return element;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		try {
			LLNode<E> itr;
			E e;

			if((index < 0) || (index > (size)))
				throw new IndexOutOfBoundsException();
			else {
				itr = head.next;
				while(index > 0) {
					itr = itr.next;
					index--;
				}
			}
			e 				= itr.data;
			itr.data		= element;
			
			return e;
		}
		catch(OutOfMemoryError E) {
			throw E;
		}
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
