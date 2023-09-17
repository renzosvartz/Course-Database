/**
 * @author Renzo Svartz
 */

package Data_Structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Interfaces.CourseDBManagerInterface;

/**
 * Class implementing a course database manager
 */
public class CourseDBManager implements CourseDBManagerInterface
{
	private CourseDBStructure dbstructure;
	
	public CourseDBManager()
	{
		dbstructure = new CourseDBStructure();
	}
	
	/**
	 * Create and add an entry using CourseDBStructure's add method.
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor)
	{
		CourseDBElement newCourse = new CourseDBElement(id, crn, credits, roomNum, instructor);
		dbstructure.add(newCourse);
	}

	/**
	 * Get an entry using CourseDBStructure's get method.
	 * @throws IOException 
	 */
	public CourseDBElement get(int crn) throws IOException
	{
		return dbstructure.get(crn);
	}

	/**
	 * Read a given file of courses and add them to the DB.
	 * @param input file to be read
	 */
	public void readFile(File input) throws FileNotFoundException 
	{
		Scanner inputFile = new Scanner(input);
		
		while (inputFile.hasNext())
		{
			if (inputFile.hasNext("#"))
			{
				//System.out.println("skipped.");
				inputFile.nextLine();
			}
			else
			{	
				try
				{
					String course[] = inputFile.nextLine().split("\\s+");
					
					String courseID = course[0];
					
					//Integer.valueOf could throw an exception
					int CRN = Integer.valueOf(course[1]);
					int numOfCredits = Integer.valueOf(course[2]);
					
					String roomNumber = course[3];
					String instructorsName = course[4];
					
					for (int i = 5; i < course.length; i++)
					{
						instructorsName = instructorsName.concat(" " + course[i]);
					}
					
					//Object could throw an exception and not be constructed
					CourseDBElement newCourse = new CourseDBElement(courseID, CRN, numOfCredits, roomNumber, instructorsName);
					
					dbstructure.add(newCourse);
				}
				
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * Return an ArrayList of the courses in the order of CRN.
	 */
	public ArrayList<String> showAll() 
	{
		return dbstructure.showAll();
	}

}
