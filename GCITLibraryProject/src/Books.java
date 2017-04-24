import java.util.Arrays;

/**
 * Books.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 20, 2017
 */

public class Books {
	private int bookId;
	private int pubId;
	private int authNum;
	private String title;
	private String author;
	private String[] authorList;
	
	public Books(int bookId, String title) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = "NA";
		this.authNum = 1;
	}
	
	public Books(int bookId, String title, int pubId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubId = pubId;
		this.author = "NA";
		this.authNum = 1;
	}
	/**
	 * @param bookId
	 * @param title
	 * @param author
	 */
	public Books(int bookId, String title, String author) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.authNum = 1;
	}
	
	/**
	 * @return the pubId
	 */
	public int getPubId() {
		return pubId;
	}
	/**
	 * @param pubId the pubId to set
	 */
	public void setPubId(int pubId) {
		this.pubId = pubId;
	}
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the author list
	 */
	public String[] getAuthorList() {
		return this.authorList;
	}
	/**
	 * @param author the author to add to array of authors
	 */
	public void addAuthor(String author) {
		String[] temp = new String[this.authNum+1];
		for(int i = 0; i < this.authNum; i++) {
			temp[i] = this.authorList[i];
		}
		temp[this.authNum] = author;
		this.authorList = temp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Books other = (Books) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookId != other.bookId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", pubId=" + pubId + ", authNum=" + authNum + ", title=" + title
				+ ", author=" + author + ", authorList=" + Arrays.toString(authorList) + "]";
	}
	
}
