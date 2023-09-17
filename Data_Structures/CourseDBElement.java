/**
 * @author Renzo Svartz
 */

//VALIDATION 

package Data_Structures;

/**
 * Class implementing a course that may be placed in a data base
 */
public class CourseDBElement 
{
	private String courseID;
	private int CRN;
	private int numOfCredits;
	private String roomNumber;
	private String instructorsName;
	
	/**
	 * Default Constructor
	 */
	public CourseDBElement()
	{
		
	}
	
	/**
	 * Parameterized Constructor
	 * @param courseID the course ID
	 * @param CRN the course registration number
	 * @param numOfCredits the number of credits
	 * @param roomNumber the room number
	 * @param instructorsName the instructors name
	 */
	public CourseDBElement(String courseID, int CRN, int numOfCredits, String roomNumber, String instructorsName)
	{	
			//verifyCourseID(courseID);
			this.courseID = courseID;
			
			verifyCRN(CRN);
			this.CRN = CRN;
			
			verifyNumOfCredits(numOfCredits);
			this.numOfCredits = numOfCredits;
			
			verifyRoomNumber(roomNumber);
			this.roomNumber = roomNumber;
			
			this.instructorsName = instructorsName;
	}

	/**
	 * Unused
	 * Method for verifying course ID
	 * @param courseID course ID
	 */
	private void verifyCourseID(String courseID) 
	{
		;
	}

	/**
	 * Method for verifying course registration number
	 * @param CRN course registration number
	 */
	private void verifyCRN(int CRN) 
	{
		if (CRN < 10000 || CRN > 99999)
		{
			throw new IllegalArgumentException("Illegal course registration number. Must be between 10000 and 99999.");
		}
	}

	/**
	 * Method for verifying number of credits
	 * @param numOfCredits number of credits
	 */
	private void verifyNumOfCredits(int numOfCredits)
	{
		if (numOfCredits < 1 || numOfCredits > 4)
		{
			throw new IllegalArgumentException("Illegal number of credits. Must be between 1 and 4.");
		}
	}

	/**
	 * Method for verifying room number
	 * @param roomNumber room number
	 */
	private void verifyRoomNumber(String roomNumber)
	{
		if (roomNumber.length() != 5 || !Character.isUpperCase(roomNumber.charAt(0)) || !Character.isUpperCase(roomNumber.charAt(1)) || !Character.isDigit(roomNumber.charAt(2)) || !Character.isDigit(roomNumber.charAt(3)) || !Character.isDigit(roomNumber.charAt(4)))
		{
			if (!roomNumber.equals("Distance-Learning"))
			{
				throw new IllegalArgumentException("Illegal room number. Must be 5 characters, two uppercase letters followed by 3 digits (i.e. AA123), or 'Distance-Learning'.");
			}
		}
	}
	
	/**
	 * Getter method for courseID
	 * @return courseID
	 */
	public String getCourseID()
	{
		return courseID;
	}
	
	/**
	 * Getter method for CRN
	 * @return CRN
	 */
	public int getCRN()
	{
		return CRN;
	}

	/**
	 * Getter method for numOfCredits
	 * @return numOfCredits
	 */
	public int getNumOfCredits()
	{
		return numOfCredits;
	}
	
	/**
	 * Getter method for roomNumber
	 * @return roomNumber
	 */
	public String getRoomNumber()
	{
		return roomNumber;
	}
	
	/**
	 * Getter method for instructorsName
	 * @return instructorsName
	 */
	public String getInstructorsName()
	{
		return instructorsName;
	}
	
	/**
	 * Setter method for courseID
	 * @param courseID the course ID
	 */
	public void setCourseID(String courseID)
	{
		this.courseID = courseID;
	}
	
	/**
	 * Setter method for CRN
	 * @param CRN the course registration number
	 */
	public void setCRN(int CRN)
	{
		this.CRN = CRN;
	}

	/**
	 * Setter method for courseID
	 * @param numOfCredits the number of credits
	 */
	public void setNumOfCredits(int numOfCredits)
	{
		this.numOfCredits = numOfCredits;
	}

	/**
	 * Setter method for roomNumber
	 * @param roomNumber the room number
	 */
	public void setRoomNumber(String roomNumber)
	{
		this.roomNumber = roomNumber;
	}
	
	/**
	 * Setter method for instructorsName
	 * @param instructorsName the instructors name
	 */
	public void setInstructorsName(String instructorsName)
	{
		this.instructorsName = instructorsName;
	}
	
	/**
	 * toString method
	 */
	public String toString()
	{
		return new String("Course:" + courseID + " CRN:" + CRN + " Credits:" + numOfCredits + " Instructor:" + instructorsName + " Room:" + roomNumber);
	}
}
