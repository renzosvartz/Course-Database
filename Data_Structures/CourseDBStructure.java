/**
 * @author Renzo Svartz
 */

package Data_Structures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Interfaces.CourseDBStructureInterface;

/**
 * Class implementing a course database structure that holds a hashed array of buckets.
 * The buckets contain courses (database elements)
 */
public class CourseDBStructure implements CourseDBStructureInterface
{
	public LList<CourseDBElement>[] hashTable;
	private int numOfBuckets;
	//private int numOfEntries;
	//private double loadFactor;
	private boolean integrityOK;
	private final static int DEFAULT_CAPACITY = 29;
	private final static int MAX_CAPACITY = 10000;
	//private final static double MAX_LOAD_FACTOR = 0.5;
	
	/**
	 * Default Constructor
	 */
	public CourseDBStructure()
	{
		this(DEFAULT_CAPACITY);
	}

	public CourseDBStructure(String str, int tableSize)
	{
		this(tableSize);
	}
	
	/**
	 * Parameterized Constructor
	 * @param tableSize size of hash table
	 */
	public CourseDBStructure(int tableSize)
	{
		integrityOK = false;
		tableSize = checkCapacity(tableSize);
		
		hashTable = (LList<CourseDBElement>[]) new LList[tableSize];
		numOfBuckets = 0;
		//numOfEntries = 0;
		//loadFactor = 0;
		integrityOK = true;
	}
	
	/**
	 * Adds a new element CourseDBElement to the Hashed Array of Linked Lists (Buckets)
	 * @param element the course to add
	 */
	public void add(CourseDBElement element) 
	{
		checkIntegrity();
		
		int hashIndex = getHashIndex(element.getCRN());
		
		if (hashTable[hashIndex] == null)
		{
			hashTable[hashIndex] = new LList<CourseDBElement>(new Node<CourseDBElement>(element));
			numOfBuckets++;
		}
		else
		{
			hashTable[hashIndex].add(element);
		}
		//numOfEntries++;
		//loadFactor = numOfEntries / numOfBuckets;
	}

	/**
	 * Generates hash index for a CRN
	 * @param CRN course registration number
	 * @return hash index for CRN
	 */
	private int getHashIndex(Integer CRN) 
	{
		int hashIndex = CRN.hashCode() % hashTable.length;
		//System.out.println("Hash Index for " + CRN + " is " + hashIndex + " out of " + getTableSize());
		
		if (hashIndex < 0)
		{
			hashIndex += hashTable.length;
			//System.out.println("Fixed Index: " + CRN + " in " + hashIndex);
		}
		
		return hashIndex;
	}
	
	/** 
	* Returns either null or the course with a given crn
	* @param crn of the element (to be returned)
	* @throws IOException 
	*/
	public CourseDBElement get(int crn) throws IOException 
	{
		checkIntegrity();
		
		CourseDBElement result = null;
		
		int hashIndex = getHashIndex(crn);
		
		for (int i = 0; i < hashTable[hashIndex].getLength(); i++)
		{
			if (hashTable[hashIndex].getEntry(i).getCRN() == crn)
			{
				result = hashTable[hashIndex].getEntry(i);
				break;
			}
		}
		
		return result;
	}

	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	public int getTableSize() 
	{
		return hashTable.length;
	}
	
	/**
	 * Method checks the proposed capacity
	 * @param capacity capacity of the array
	 * @return next prime number from capacity
	 */
	private int checkCapacity(int capacity) 
	{
		if (capacity > MAX_CAPACITY)
		{
			throw new IllegalStateException("Attempt to create a array whose " + "capacity exeeds allowed " + "maximum of " + MAX_CAPACITY);
		}
		else
		{
			//return getNextPrime(capacity);
			return capacity;
		}
	}
	
	/**
	 * Checks the integrity of the object
	 */
	private void checkIntegrity()
	{
		if (!integrityOK)
		{
			throw new SecurityException("LList object is corrupt.");
		}
	}
	
	/**
	 * Unused
	 * Generates the next prime number given an int
	 * @param num an int
	 * @return the next prime number
	 */
	private int getNextPrime(int num) 
	{
	      num++;
	      
	      for (int i = 2; i < num; i++) 
	      {
	         if (num % i == 0) 
	         {
	            num++;
	            i=2;
	         } 
	         else 
	         {
	            continue;
	         }
	      }
	      
	      return num;
	   }

	/**
	 * Return an ArrayList of the courses in the order of CRN.
	 */
	public ArrayList<String> showAll()
	{
		ArrayList<CourseDBElement> courses = new ArrayList<CourseDBElement>();
		
		for (int i = 0; i < hashTable.length; i++)
		{
			if (hashTable[i] != null && !hashTable[i].isEmpty())
			{
				courses.addAll(Arrays.asList(hashTable[i].toArray(CourseDBElement.class)));
			}
		}
		
		courses.sort((o1, o2) -> Integer.compare(o1.getCRN(), o2.getCRN())); // == 0 ? :
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < courses.size(); i++)
		{
			result.add(courses.get(i).toString());
		}
		
		return result;
	}
}
