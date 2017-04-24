/**
 * Publishers.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 23, 2017
 */

public class Publisher {
	private int pubId;
	private String name;
	private String address;
	private String phone;
	/**
	 * @param pubId
	 * @param name
	 */
	protected Publisher() {
		super();
		this.pubId = 0;
		this.name = "defaultName";
		this.address = "defaultAddress";
		this.phone = "defaultPhone";
	}
	
	/**
	 * @param pubId
	 * @param name
	 * @param address
	 * @param phone
	 */
	protected Publisher(int pubId, String name, String address, String phone) {
		super();
		this.pubId = pubId;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * @return the pubId
	 */
	protected int getPubId() {
		return pubId;
	}
	/**
	 * @param pubId the pubId to set
	 */
	protected void setPubId(int pubId) {
		this.pubId = pubId;
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
	 * @return the address
	 */
	protected String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	protected void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	protected String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	protected void setPhone(String phone) {
		this.phone = phone;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pubId;
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
		Publisher other = (Publisher) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pubId != other.pubId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Publisher [pubId=" + pubId + ", name=" + name + "]";
	}
	
	
}
