

/**
 * BookLoan.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 22, 2017
 */

public class BookLoan {
	private Books book;
	private LibraryBranches branch;
	private User loanUser;
	private String dateOut;
	private String dateDue;
	private String dateIn;
	/**
	 * @param book
	 * @param branch
	 * @param loanUser
	 * @param dateOut
	 * @param dateDue
	 * @param dateIn
	 */
	protected BookLoan(Books book, LibraryBranches branch, User loanUser, String dateOut, String dateDue, String dateIn) {
		super();
		this.book = book;
		this.branch = branch;
		this.loanUser = loanUser;
		this.dateOut = dateOut;
		this.dateDue = dateDue;
		this.dateIn = dateIn;
	}
	/**
	 * @return the book
	 */
	protected Books getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	protected void setBook(Books book) {
		this.book = book;
	}
	/**
	 * @return the branch
	 */
	protected LibraryBranches getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	protected void setBranch(LibraryBranches branch) {
		this.branch = branch;
	}
	/**
	 * @return the loanUser
	 */
	protected User getLoanUser() {
		return loanUser;
	}
	/**
	 * @param loanUser the loanUser to set
	 */
	protected void setLoanUser(User loanUser) {
		this.loanUser = loanUser;
	}
	/**
	 * @return the dateOut
	 */
	protected String getDateOut() {
		return dateOut;
	}
	/**
	 * @param dateOut the dateOut to set
	 */
	protected void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	/**
	 * @return the dateDue
	 */
	protected String getDateDue() {
		return dateDue;
	}
	/**
	 * @param dateDue the dateDue to set
	 */
	protected void setDateDue(String dateDue) {
		this.dateDue = dateDue;
	}
	/**
	 * @return the dateIn
	 */
	protected String getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	protected void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((dateDue == null) ? 0 : dateDue.hashCode());
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + ((loanUser == null) ? 0 : loanUser.hashCode());
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
		BookLoan other = (BookLoan) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (dateDue == null) {
			if (other.dateDue != null)
				return false;
		} else if (!dateDue.equals(other.dateDue))
			return false;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (loanUser == null) {
			if (other.loanUser != null)
				return false;
		} else if (!loanUser.equals(other.loanUser))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookLoan [book=" + book + ", branch=" + branch + ", loanUser=" + loanUser + ", dateOut=" + dateOut
				+ ", dateDue=" + dateDue + ", dateIn=" + dateIn + "]";
	}
	
}
