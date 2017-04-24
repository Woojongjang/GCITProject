/**
 * Administrator.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 23, 2017
 */

public class Administrator extends User {

	/**
	 * 
	 */
	public Administrator() {
		super(0,"default");
	}

	/**
	 * @param userId
	 * @param name
	 */
	public Administrator(int userId, String name) {
		super(userId, name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userId
	 * @param name
	 * @param userPass
	 */
	public Administrator(int userId, String name, String userPass) {
		super(userId, name, userPass);
		// TODO Auto-generated constructor stub
	}

}
