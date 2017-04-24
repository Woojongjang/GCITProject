/**
 * Borrower.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 20, 2017
 */

public class Borrower extends User {
	private String address;
	private String phone;
	
	/**
	 * 
	 */
	protected Borrower() {
		super();
		this.address = "defaultAddress";
		this.phone = "defaultPhone";
		// TODO Auto-generated constructor stub
	}

	public Borrower(int cardNo, String name) {
		super(cardNo, name);
	}
	
	/**
	 * @param userId
	 * @param name
	 * @param userPass
	 */
	public Borrower(int cardNo, String name, String userPass) {
		super(cardNo, name, userPass);
	}
	
	public Borrower(String name, String address, String phone) {
		super(name);
		this.address = address;
		this.phone = phone;
	}
	
	public Borrower(int cardNo, String name, String address, String phone, String usrPass) {
		super(cardNo, name, usrPass);
		this.address = address;
		this.phone = phone;
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
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Borrower other = (Borrower) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Borrower [address=" + address + ", phone=" + phone + ", getUserId()=" + getUserId() + ", getName()="
				+ getName() + "]";
	}
	
	
	
}
