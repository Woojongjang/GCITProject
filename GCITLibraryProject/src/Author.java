/**
 * Author.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 23, 2017
 */

public class Author {
	private int authId;
	private String authName;
	/**
	 * @param authId
	 * @param authName
	 */
	protected Author(int authId, String authName) {
		super();
		this.authId = authId;
		this.authName = authName;
	}
	/**
	 * @return the authId
	 */
	protected int getAuthId() {
		return authId;
	}
	/**
	 * @param authId the authId to set
	 */
	protected void setAuthId(int authId) {
		this.authId = authId;
	}
	/**
	 * @return the authName
	 */
	protected String getAuthName() {
		return authName;
	}
	/**
	 * @param authName the authName to set
	 */
	protected void setAuthName(String authName) {
		this.authName = authName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authId;
		result = prime * result + ((authName == null) ? 0 : authName.hashCode());
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
		Author other = (Author) obj;
		if (authId != other.authId)
			return false;
		if (authName == null) {
			if (other.authName != null)
				return false;
		} else if (!authName.equals(other.authName))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Author [authId=" + authId + ", authName=" + authName + "]";
	}
	
}
