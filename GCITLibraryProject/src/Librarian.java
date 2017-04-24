/**
 * Librarian.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 22, 2017
 */

public class Librarian extends User {
	/**
	 * 
	 */
	public Librarian() {
		super(0,"default");
	}

	/**
	 * @param userId
	 * @param name
	 */
	public Librarian(int userId, String name) {
		super(userId, name);
	}

	/**
	 * @param userId
	 * @param name
	 * @param userPass
	 */
	public Librarian(int userId, String name, String userPass) {
		super(userId, name, userPass);
	}
	
}
