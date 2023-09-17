/**
 * @author Renzo Svartz
 */
package Data_Structures;

import java.util.NoSuchElementException;

import Interfaces.ListInterface;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Class implementing a linked list
 * @param <T> generic parameter
 */
public class LList<T> implements ListInterface<T>, Iterable<T>
{
	private Node<T> firstNode;
	private Node<T> lastNode;
	private int numOfEntries;
	
	/**
	 * Default Constructor
	 */
	public LList()
	{
		firstNode = null;
		lastNode = null;
		numOfEntries = 0;
	}
	
	/**
	 * Parameterized Constructor
	 * @param newEntry first entry in linked list
	 */
	public LList(Node<T> newEntry)
	{
		firstNode = newEntry;
		lastNode = newEntry;
		numOfEntries = 1;
	}
	
	/**
	 * Method locates and returns the node at a given index
	 * @param givenPosition index of the node
	 * @return the node at the given position
	 */
	private Node<T> getNodeAt(int givenPosition)
	{
		Node<T> currentNode = firstNode;

		for (int i = 0; i < givenPosition; i++)
		{
			currentNode = currentNode.getNextNode();
		}
			
		return currentNode;
	}
	
	/** Adds a new entry to the end of this list. 
	 * Entries currently in the list are unaffected.
	 * The list's size is increased by 1.
	 * @param newEntry The object to be added as a new entry.
	 */
	public void add(T newEntry)
	{
		Node<T> newNode = new Node<T>(newEntry);
		
		if(isEmpty())
		{
			firstNode = newNode;
		}
		else
		{
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode; //lastNode always has to be set to newNode when adding to the back
		numOfEntries++;
	}
	
	/** Adds a new entry at a specified position within this list.
	 * Entries originally at and above the specified position
	 * are at the next higher position within the list.
	 * The list's size is increased by 1.
	 * @param newPosition An integer that specifies the desired position of the new entry. 
	 * @param newEntry The object to be added as a new entry. 
	 * @throws IndexOutOfBoundsException if either newPosition < 1 or newPosition > getLength() + 1.
	 */
	public void add(int givenPosition, T newEntry)
	{
		if (givenPosition >= 0 && givenPosition <= numOfEntries)
		{
			Node<T> newNode = new Node<T>(newEntry);
			
			if(isEmpty())
			{
				firstNode = newNode;
				lastNode = newNode;
			}
			else if (givenPosition == 0)
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else if (givenPosition == numOfEntries)
			{
				lastNode.setNextNode(newNode);
				lastNode = newNode;
			}
			else
			{
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeAfter = getNodeAt(givenPosition + 1);
				nodeBefore.setNextNode(newNode);
				newNode.setNextNode(nodeAfter);
			}
			numOfEntries++;
		}
		else
		{
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
		}
	}
	
	/** Removes the entry at a given position from this list.
	 * Entries originally at positions higher than the 
	 * given position are at the next lower position within the list,
	 * and the list's size is decreased by 1.
	 * @param givenPosition An integer that indicates the position of the entry to be removed.
	 * @return A reference to the removed entry.
	 * @throws IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T remove(int givenPosition)
	{
		if (givenPosition >= 0 && givenPosition <= numOfEntries - 1)
		{
			T data;
			if (givenPosition == 0)
			{
				data = firstNode.getData();
				firstNode = firstNode.getNextNode();
			}
			else
			{
				Node<T> nodeToRemove = getNodeAt(givenPosition);
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeAfter = getNodeAt(givenPosition + 1);
				data = nodeToRemove.getData();
				nodeBefore.setNextNode(nodeAfter);
				if (givenPosition == numOfEntries - 1)
				{
					lastNode = nodeBefore; //check boundary conditions
				}
			}
			numOfEntries--;
			return data;
		}
		else
		{
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		}
	}
	
	/** Retrieves the entry at a given position in this list.
	 * @param givenPosition An integer that indicates the position of the desired entry.
	 * @return A reference to the indicated entry.
	 * @throws IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T getEntry(int givenPosition)
	{
		if (givenPosition >= 0 && givenPosition < numOfEntries)
		{
			Node<T> currentNode = firstNode;
			for (int i = 0; i < givenPosition; i++)
			{
				currentNode = currentNode.getNextNode();
			}
			return currentNode.getData();
		}
		else
		{
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
		}
	}
	
	/** Replaces the entry at a given position in this list. 
	 * @param givenPosition An integer that indicates the position of the entry to be replaced.
	 * @param newEntry The object that will replace the entry at the position givenPosition.
	 * @return The original entry that was replaced.
	 * @throws IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T replace(int givenPosition, T newEntry)
	{
		if (givenPosition >= 0 && givenPosition <= numOfEntries - 1)
		{
			Node<T> currentNode = firstNode;
			for (int i = 0; i < givenPosition; i++)
			{
				currentNode = currentNode.getNextNode();
			}
			T data = currentNode.getData();
			currentNode.setData(newEntry);
			return data;
		}
		else
		{
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
		}
	}
	
	/** Sees whether this list contains a given entry.
	 * @param anEntry The object that is the desired entry.
	 * @return True if the list contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry)
	{
		boolean status = false;
		Node<T> currentNode = firstNode;

		for (int i = 0; i < numOfEntries; i++)
		{
			if (anEntry.equals(currentNode.getData()))
			{
				status = true;
				break;
			}
			currentNode = currentNode.getNextNode();
		}
		
		return status;
	}
	
	/** Retrieves all entries that are in this list in the order in which they occur in the list.
	 * @return A newly allocated array of all the entries in the list. If the list is empty, the returned array is empty.
	 */
	public T[] toArray(Class<T> tclass)
	{	
		T[] copy = (T[]) Array.newInstance(tclass, numOfEntries);
		Node<T> currentNode = firstNode;
		
		for (int i = 0; i < numOfEntries; i++)
		{
			copy[i] = currentNode.getData();
			currentNode = currentNode.getNextNode();
		}
		
		return copy;
	}
	
	/** Gets the length of this list.
	 * @return The integer number of entries currently in the list.
	 */
	public int getLength()
	{
		return numOfEntries;
	}

	/** Removes all entries from this list.
	 */
	public void clear()
	{
		firstNode = lastNode =null;
		numOfEntries = 0;
	}

	/** Sees whether this list is empty.
	 * @return True if the list is empty, or false if not.
	 */
	public boolean isEmpty() 
	{
		return numOfEntries == 0;
	}
	
	/**
	 * Creates an iterator for the linked list
	 */
	public Iterator<T> iterator()
	{
		return new InnerIterator();
	}
	
	/**
	 * Inner class implementing an iterator
	 */
	private class InnerIterator implements Iterator<T>
	{
		private Node<T> currentNode;
		private int nextPosition;
		private boolean wasNextCalled;
		
		/**
		 * Default Constuctor
		 */
		private InnerIterator()
		{
			currentNode = firstNode;
			nextPosition = 0;
			wasNextCalled = false;
		}
		
		/** Detects whether this iterator has completed its traversal 
		 * and gone beyond the last entry in the collection of data.
		 * @return True if the iterator has another entry to return.
		 */
		public boolean hasNext()
		{
			return currentNode != null;
		}
		
		/** Retrieves the next entry in the collection and 
		 * advances this iterator by one position.
		 * @return A reference to the next entry in the iteration, if one exists.
		 * @throws NoSuchElementException if the iterator had reached the end already,
		 * that is, if hasNext() is false.
		 */
		public T next()
		{
			if (hasNext())
			{
				T data = currentNode.getData();
				currentNode = currentNode.getNextNode();
				nextPosition++;
				wasNextCalled = true;
				return data;
			}
			else
			{
				throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
			}
		}
		
		/** Removes from the collection of data the last entry that next() returned. 
		 * A subsequent call to next() will behave as it would have before the removal. 
		 * Precondition: next() has been called, and remove() has not been called since then. 
		 * The collection has not been altered during the iteration except by calls to this method. 
		 * @throws IllegalStateException if next() has not been called, 
		 * or if remove() was called already after the last call to next(). 
		 * @throws UnsupportedOperationException if the iterator does not permit a remove operation. 
		 */
		public void remove()
		{
			if (wasNextCalled)
			{
				LList.this.remove(--nextPosition);
				wasNextCalled = false;
			}
			else
			{
				throw new IllegalStateException("Illegal call to remove(); next() was not called.");
			}
		}
	}
	
	/**
	 * Creates ListIterator for the linked list
	 */
	public ListIterator<T> listiterator()
	{
		return new InnerListIterator();
	}
	
	/**
	 * Inner ListIterator class for the linked list
	 */
	private class InnerListIterator implements ListIterator<T>
	{
		private enum Move {NEXT, PREVIOUS};
		
		private Node<T> currentNode;
		private int nextPosition;
		private boolean isRemoveOrSetLegal;
		private Move lastMove;
		
		/**
		 * Default Constructor
		 */
		private InnerListIterator()
		{
			currentNode = firstNode;
			nextPosition = 0;
			isRemoveOrSetLegal = false;
			lastMove = null;
		}
		
		/** Detects whether this iterator has completed its traversal 
		 * and gone beyond the last entry in the collection of data.
		 * @return True if the iterator has another entry to return.
		 */
		public boolean hasNext()
		{
			return currentNode != null;
		}
		
		/** Retrieves the next entry in the collection and 
		 * advances this iterator by one position.
		 * @return A reference to the next entry in the iteration, if one exists.
		 * @throws NoSuchElementException if the iterator had reached the end already,
		 * that is, if hasNext() is false.
		 */
		public T next()
		{
			if (hasNext())
			{
				isRemoveOrSetLegal = true;
				lastMove = Move.NEXT;
				T data = currentNode.getData();
				currentNode = currentNode.getNextNode();
				nextPosition++;
				return data;
			}
			else
			{
				throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
			}
		}
		
		/** Removes from the list the last entry that either next() or previous() has returned.
		 * Precondition: next() or previous() has been called, but the iterator's 
		 * remove() or add() method has not been called since then. 
		 * That is, you can call remove() only once per call to next() or previous(). 
		 * The list has not been altered during the iteration 
		 * except by calls to the iterator's remove(), add(), or set() methods. 
		 * @throws UnsupportedOperationException if the iterator does not permit a remove operation.
		 */
		public void remove()
		{
			if (isRemoveOrSetLegal)
			{
				if (lastMove.equals(Move.NEXT))
				{
					LList.this.remove(--nextPosition);
				}
				else if (lastMove.equals(Move.PREVIOUS))
				{
					LList.this.remove(nextPosition);
				}
				isRemoveOrSetLegal = false;
			}
			else
			{
				throw new IllegalStateException("Illegal call to remove(); next() or previous() was not called, or add() or remove() called since then.");
			}
		}
		
		/** Detects whether this iterator has gone before the first entry in the list.
		 * @return True if the iterator has another entry to visit when traversing the list backward;
		 * otherwise returns false.
		 */
		public boolean hasPrevious()
		{
			return (nextPosition > 0 && nextPosition <= numOfEntries);
		}
		
		/** Retrieves the previous entry in the list and moves this iterator back by one position.
		 * @return A reference to the previous entry in the iteration, if one exists.
		 * @throws NoSuchElementException if the iterator has no previous entry, that is,
		 * if hasPrevious() is false.
		 */
		public T previous()
		{
			if (hasPrevious())
			{
				isRemoveOrSetLegal = true;
				lastMove = Move.PREVIOUS;
				return LList.this.getEntry(--nextPosition);
			}
			else
			{
				throw new NoSuchElementException("Illegal call to previous(); iterator is at beginning of list.");
			}
		}
		
		/** Gets the index of the next entry. 
		 * @return The index of the list entry that a subsequent call to next() would return.
		 * If next() would not return an entry because the iterator is at the end of the list,
		 * returns the size of the list. 
		 * Note that the iterator numbers the list entries from 0 instead of 1.
		 * @throws UnsupportedOperationException if the iterator does not permit an add operation.
		 */
		public int nextIndex()
		{
			if (hasNext())
			{
				return nextPosition;
			}
			else
			{
				return numOfEntries;
			}
		}
		
		/** Gets the index of the previous entry. 
		 * @return The index of the list entry that a subsequent call to previous() would return.
		 * If previous() would not return an entry because the iterator is at the beginning of the list,
		 * returns -1. 
		 * Note that the iterator numbers the list entries from 0 instead of 1.
		 * @throws UnsupportedOperationException if the iterator does not permit an add operation.
		 */
		public int previousIndex()
		{
			if (hasPrevious())
			{
				return nextPosition - 1;
			}
			else
			{
				return -1;
			}
		}
		
		/** Adds an entry to the list just before the entry, if any, 
		 * that next() would have returned before the addition. 
		 * This addition is just after the entry, if any, 
		 * that previous() would have returned.
		 * After the addition, a call to previous() will return the new entry,
		 * but a call to next() will behave as it would have before the addition. 
		 * Further, the addition increases by 1 the values
		 * that nextIndex() and previousIndex() will return. 
		 * @throws UnsupportedOperationException if the iterator does not permit an add operation.
		 */
		public void add(T newEntry)
		{
			LList.this.add(nextPosition++, newEntry);
			isRemoveOrSetLegal = false;
		}
		
		/** Replaces the last entry in the list that either next() or previous() has returned.
		 * Precondition: next() or previous() has been called, but the iterator's
		 * remove() or add() method has not been called since then. 
		 * @param newEntry An object that is the replacement entry. 
		 * @throws UnsupportedOperationException if the iterator does not permit a set operation.
		 */
		public void set(T newEntry)
		{
			if (isRemoveOrSetLegal)
			{
				if (lastMove.equals(Move.NEXT))
				{
					LList.this.replace(nextPosition - 1, newEntry);
				}
				else if (lastMove.equals(Move.PREVIOUS))
				{
					LList.this.replace(nextPosition, newEntry);
				}
				isRemoveOrSetLegal = false;
			}
			else
			{
				throw new IllegalStateException("Illegal call to set(); next() or previous() was not called, or add() or remove() called since then.");
			}
		}
	}
}
