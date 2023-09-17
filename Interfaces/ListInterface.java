/**
 * @author Renzo Svartz
 */

package Interfaces;

/**
 * An interface representing a list.
 * @param <T> a general parameter
 */
public interface ListInterface<T>
{
	/** Adds a new entry to the end of this list. 
	 * Entries currently in the list are unaffected.
	 * The list's size is increased by 1.
	 * @param newEntry The object to be added as a new entry.
	 */
	public void add(T newEntry);
	
	/** Adds a new entry at a specified position within this list.
	 * Entries originally at and above the specified position
	 * are at the next higher position within the list.
	 * The list's size is increased by 1.
	 * @param newPosition An integer that specifies the desired position of the new entry. 
	 * @param newEntry The object to be added as a new entry. 
	 * @throws IndexOutOfBoundsException if either newPosition < 1 or newPosition > getLength() + 1.
	 */
	public void add(int givenPosition, T newEntry);
	
	/** Removes the entry at a given position from this list.
	 * Entries originally at positions higher than the 
	 * given position are at the next lower position within the list,
	 * and the list's size is decreased by 1.
	 * @param givenPosition An integer that indicates the position of the entry to be removed.
	 * @return A reference to the removed entry.
	 * @throws IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T remove(int givenPosition);
	
	/** Retrieves the entry at a given position in this list.
	 * @param givenPosition An integer that indicates the position of the desired entry.
	 * @return A reference to the indicated entry.
	 * @throws IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T getEntry(int givenPosition);

	/** Replaces the entry at a given position in this list. 
	 * @param givenPosition An integer that indicates the position of the entry to be replaced.
	 * @param newEntry The object that will replace the entry at the position givenPosition.
	 * @return The original entry that was replaced.
	 * @throws IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T replace(int givenPosition, T newEntry);
	
	/** Sees whether this list contains a given entry.
	 * @param anEntry The object that is the desired entry.
	 * @return True if the list contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry);

	/** Retrieves all entries that are in this list in the order in which they occur in the list.
	 * @param tclass generic class type T of the array
	 * @return A newly allocated array of all the entries in the list. If the list is empty, the returned array is empty.
	 */
	public T[] toArray(Class<T> tclass);
	
	/** Gets the length of this list.
	 * @return The integer number of entries currently in the list.
	 */
	public int getLength();
	
	/** Removes all entries from this list.
	 */
	public void clear();
	
	/** Sees whether this list is empty.
	 * @return True if the list is empty, or false if not.
	 */
	public boolean isEmpty();
}
