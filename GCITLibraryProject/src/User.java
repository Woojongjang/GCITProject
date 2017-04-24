/**
 * User.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 20, 2017
 */

public abstract class User {
	private int userId;
	private String name;
	private String userPass;
	
	/**
	 * 
	 */
	protected User() {
		userId = -1;
		name = "default";
		userPass = "default";
	}
	
	protected User(String name) {
		this.name = name;
		this.userPass = "default";
	}
	
	protected User(int userId, String name) {
		this.userId = userId;
		this.name = name;
		this.userPass = "NA";
	}
	
	/**
	 * @param userId
	 * @param name
	 * @param userPass
	 */
	protected User(int userId, String name, String userPass) {
		this.userId = userId;
		this.name = name;
		this.userPass = userPass;
	}
	/**
	 * @return the userId
	 */
	protected int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	protected void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the name
	 */
	protected String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the userPass
	 */
	protected String getUserPass() {
		return userPass;
	}
	/**
	 * @param userPass the userPass to set
	 */
	protected void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userPass == null) ? 0 : userPass.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userId != other.userId)
			return false;
		if (userPass == null) {
			if (other.userPass != null)
				return false;
		} else if (!userPass.equals(other.userPass))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", userPass=" + userPass + "]";
	}
	
	
}
