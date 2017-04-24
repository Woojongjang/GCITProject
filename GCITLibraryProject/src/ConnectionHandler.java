
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
/**
 * ConnectionHandler.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 21, 2017
 */

public class ConnectionHandler {

	public static String driver = "com.mysql.jdbc.driver";
	public static String url = "jdbc:mysql://localhost/library?autoReconnect=true&useSSL=false";
	public static String user = "root";
	public static String password = "cseGCIT2017";
	private int userType;
	private String userPass;
	
	/**
	 * @param userType
	 */
	protected ConnectionHandler(int userType, String passInput) {
		this.userType = userType;
		this.setUserPass(passInput);
	}
	
	/**
	 * @return the userType
	 */
	protected int getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	protected void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @throws SQLException 
	 * 
	 */
	public void ConnectionStart() throws SQLException {
		System.out.println(this.printUserType());
		Connection conn = null;
		String query = "";
		PreparedStatement pstmt = null;
		
//		Scanner scan = new Scanner(System.in);
//		String authorName = scan.nextLine();
		try {
		    //Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			
//		    stmt = conn.createStatement();
//		    query = "select * from tbl_author where authorName like '%"+authorName+"%'";
//		    stmt.executeQuery(query);
		    
		    query = "select * from tbl_author where authorName like ?;";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, "Stephen King");
		    // Do something with the Connection
		    
		    //ResultSet rs = stmt.executeQuery(query);
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()) {
		    	System.out.println("Author ID: " + rs.getInt("authorId"));
		    	System.out.println("Author Name: " + rs.getString("authorName"));
		    }
		    System.out.println("finished fetching");
//		    query = "update tbl_author set authorName = ? where authorId = ?";
//		    pstmt = conn.prepareStatement(query);
//		    pstmt.setString(1, authorName);
//		    pstmt.setInt(2, 201);
//		    pstmt.executeUpdate();
		    conn.commit();
		    System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
			//scan.close();
		}
	}

	public ArrayList<LibraryBranches> getBranches() throws SQLException {
		Connection conn = null;
		String query = "";
		PreparedStatement pstmt = null;
		ArrayList<LibraryBranches> libraries = new ArrayList<LibraryBranches>();
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
		    query = "select * from tbl_library_branch;";
		    pstmt = conn.prepareStatement(query);
		    //conn.commit();
		    ResultSet rs = pstmt.executeQuery();
		    //System.out.println("Pick the Branch you want to check out from (input ID of branch):");
		    while(rs.next()) {
		    	LibraryBranches library = new LibraryBranches(rs.getInt("branchId"),rs.getString("branchName"), rs.getString("branchAddress"));
		    	libraries.add(library);
//		    	System.out.println();
//		    	System.out.print("Branch ID: " + rs.getInt("branchId"));
//		    	System.out.print(" - Branch Name: " + rs.getString("branchName"));
//		    	System.out.print(" - Branch Address: " + rs.getString("branchAddress"));
		    }
		    
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return libraries;
	}

	public HashMap<Books, Integer> getBranchBooks(int branchId, int dontShowZero) throws SQLException {
		ArrayList<Books> bookList = new ArrayList<Books>();
		HashMap<Books, Integer> hMap = new HashMap<Books, Integer>();
		Connection conn = null;
		String query = "";
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
		    query = "select b.bookId, b.title, au.authorName, bc.noOfCopies "
		    		+ "from tbl_book b "
		    		+ "inner join tbl_book_authors ba on ba.bookId = b.bookId "
		    		+ "inner join tbl_author au on au.authorId = ba.authorId "
		    		+ "inner join tbl_book_copies bc on bc.bookId = b.bookId "
		    		+ "inner join tbl_library_branch lb on lb.branchId = bc.branchId "
		    		+ "where lb.branchId = " + branchId + " and bc.noOfCopies >= "+ dontShowZero +";";
		    pstmt = conn.prepareStatement(query);
		    //conn.commit();
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()) {
		    	Books book = new Books(rs.getInt("bookId"), rs.getString("b.title"), rs.getString("authorName"));
		    	bookList.add(book);
		    	hMap.put(book, rs.getInt("noOfCopies"));
		    }
		    
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		
		return hMap;
	}
	
	/*
	 * @param branchId - the branch to add/subtract books to/from
	 * @param arithmetic - 1 for addition, 0 for subtraction
	 * @param bookNum - number of books to add or subtract
	 */
	public void bookCountMath(int branchId, int bookId, int arithmetic, int bookNum) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call book_copies_math(?, ?, ?, ?)}");
		    cStmt.setInt(1, bookId);
		    cStmt.setInt(2, branchId);
		    cStmt.setInt(3, arithmetic);
		    cStmt.setInt(4, bookNum);
		    cStmt.executeQuery();
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
	}
	
	public void insertBookLoan(int branchId, int bookId, int cardNum) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call insert_book_loans(?, ?, ?, ?)}");
		    cStmt.setInt(1, bookId);
		    cStmt.setInt(2, branchId);
		    cStmt.setInt(3, cardNum);
		    cStmt.setString(4, ft.format(dNow));
		    cStmt.executeQuery();
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
	}
	
	public ArrayList<BookLoan> readBookLoan(int cardNum, int userType) throws SQLException {
		//HashMap<Books,Date> innerMap = new HashMap<Books,Date>();
		//HashMap<Integer, HashMap<Books,Date>> hMap = new HashMap<Integer, HashMap<Books,Date>>();
		ArrayList<BookLoan> bookLoanList = new ArrayList<BookLoan>();
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			//#bookNum int(11), branchNum int(11), cardNum int(11),dateChecked datetime, dateDue datetime)
			cStmt = conn.prepareCall("{call read_tbl_book_loans(?,?,?,?,?)}");
		    //cStmt.setInt(1, cardNum);
			// potentially check for userType calling this function to limit input parameters so not all values are returned
			cStmt.setNull(1, java.sql.Types.NULL);
			cStmt.setNull(2, java.sql.Types.NULL);
			cStmt.setInt(3, cardNum);
			cStmt.setNull(4, java.sql.Types.NULL);
			cStmt.setNull(5, java.sql.Types.NULL);
			
		    ResultSet rs = cStmt.executeQuery();		    
		    while(rs.next()) {
		    	Books loanedBook = new Books(rs.getInt("bookId"), rs.getString("BookName"));
		    	LibraryBranches branch = new LibraryBranches(rs.getInt("branchId"),rs.getString("branchName"));
		    	// if statement using userType to create respective user obj
		    	Borrower userLoan = new Borrower(rs.getInt("cardNo"), rs.getString("userName"));
		    	BookLoan loaned = new BookLoan(loanedBook, branch, userLoan, rs.getString("dateOut"), rs.getString("dueDate"), rs.getString("dateIn"));
		    	bookLoanList.add(loaned);
//		    	Books book = new Books(rs.getInt("bookId"), rs.getString("b.title"), rs.getString("authorName"));
//		    	bookList.add(book);
//		    	hMap.put(book, rs.getInt("noOfCopies"));
		    	//System.out.println(rs.getInt("bookId") + " + " + rs.getString("BookName") + " + " + rs.getString("userName"));
		    }
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return bookLoanList;
	}
	
	public int updateBookLoan(int bookId, int branchId, int cardNo, String dateOut, String dueDate, String dateIn) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call update_tbl_book_loans(?, ?, ?, ?, ?, ?)}");
//			in bookNum int(11),in branchNum int(11),in cardNum int(11),in dateChecked datetime, in dateDue datetime, in dateReturn datetime
			cStmt.setInt(1, bookId);
		    cStmt.setInt(2, branchId);
		    cStmt.setInt(3, cardNo);
		    // passing in null for dateOut will update all book loans rows containing previous three values
		    if(dateOut != null) {
		    	cStmt.setString(4, dateOut);
			}
		    else {
		    	cStmt.setNull(4, java.sql.Types.NULL);
			}
		    if(dueDate != null) {
		    	cStmt.setString(5, dueDate);
			}
		    else {
		    	cStmt.setNull(5, java.sql.Types.NULL);
			}
			if(dateIn != null) {
				cStmt.setString(6, dateIn);
			}
			else {
				cStmt.setNull(6, java.sql.Types.NULL);
			}
//			cStmt.setString(4, ft.format(dateOut));
//		    cStmt.setString(5, ft.format(dueDate));
//		    cStmt.setString(6, ft.format(dateIn));
		    cStmt.executeQuery();
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 0;
	}
	
	public int insertBranch(String bName, String bAddress) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call insert_tbl_library_branch(?, ?)}");
			if(bName != null) {
				cStmt.setString(1, bName);
			}
			else {
				cStmt.setNull(1, java.sql.Types.NULL);
			}
			if(bAddress != null) {
				cStmt.setString(2, bAddress);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
		    cStmt.executeQuery();
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 0;
	}

	public int updateBranch(int branchId, String bName, String bAddress) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call update_tbl_library_branch(?, ?, ?)}");
		    cStmt.setInt(1, branchId);
			if(bName != null) {
				cStmt.setString(2, bName);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			if(bAddress != null) {
				cStmt.setString(3, bAddress);
			}
			else {
				cStmt.setNull(3, java.sql.Types.NULL);
			}
		    cStmt.executeQuery();
		    conn.commit();
		    //System.out.println("finished committing");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 0;
	}
	
	public int deleteBranch(int branchId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call delete_tbl_library_branch(?)}");
			cStmt.setInt(1, branchId);
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public ArrayList<Books> readBooks() throws SQLException {
		ArrayList<Books> bookList = new ArrayList<Books>();
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call read_tbl_book()}");
		    ResultSet rs = cStmt.executeQuery();		    
		    while(rs.next()) {
		    	Books books = new Books(rs.getInt("bookId"), rs.getString("title"), rs.getInt("pubId"));
		    	bookList.add(books);
		    }
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return bookList;
	}
	
	public int updateBooks(int bookId, String title, int pubId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call update_tbl_book(?, ?, ?)}");
			cStmt.setInt(1, bookId);
			if(title != null) {
				cStmt.setString(2, title);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			if(pubId != 0 && pubId > 0) {
				cStmt.setInt(3, pubId);
			}
			else if (pubId < 0) {
				cStmt.setNull(3, java.sql.Types.NULL);
			}
			else {
				cStmt.setInt(3, 0);
			}
//			cStmt.setString(2, title);
//			cStmt.setInt(3, pubId);
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public int insertBooks(String title, int pubId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call insert_tbl_book(?, ?)}");
			cStmt.setString(1, title);
			if(pubId == 0) {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			else {
				cStmt.setInt(2, pubId);
			}
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public int deleteBooks(int bookId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call delete_tbl_book(?)}");
			cStmt.setInt(1, bookId);
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public ArrayList<Publisher> readPublishers() throws SQLException {
		ArrayList<Publisher> pubList = new ArrayList<Publisher>();
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call read_tbl_publisher()}");
		    ResultSet rs = cStmt.executeQuery();		    
		    while(rs.next()) {
		    	Publisher pubs = new Publisher(rs.getInt("publisherId"), rs.getString("publisherName"), rs.getString("publisherAddress"), rs.getString("publisherPhone"));
		    	pubList.add(pubs);
		    }
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return pubList;
	}
	
	public int updatePublisher(int pubId, String pubName, String pubAddress, String pubPhone) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call update_tbl_publisher(?, ?, ?, ?)}");
			cStmt.setInt(1, pubId);
			if(pubName != null) {
				cStmt.setString(2, pubName);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			if(pubAddress != null) {
				cStmt.setString(3, pubAddress);
			}
			else {
				cStmt.setNull(3, java.sql.Types.NULL);
			}
			if(pubPhone != null) {
				cStmt.setString(4, pubPhone);
			}
			else {
				cStmt.setNull(4, java.sql.Types.NULL);
			}
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public int insertPublisher(String pubName, String pubAddress, String pubPhone) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call insert_tbl_publisher(?, ?, ?)}");
			if(pubName != null) {
				cStmt.setString(1, pubName);
			}
			else {
				cStmt.setNull(1, java.sql.Types.NULL);
			}
			if(pubAddress != null) {
				cStmt.setString(2, pubAddress);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			if(pubPhone != null) {
				cStmt.setString(3, pubPhone);
			}
			else {
				cStmt.setNull(3, java.sql.Types.NULL);
			}
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}

	public int deletePublisher(int pubId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call delete_tbl_publisher(?)}");
			cStmt.setInt(1, pubId);
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}

	public ArrayList<Author> readAuthors() throws SQLException {
		ArrayList<Author> authorList = new ArrayList<Author>();
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call read_tbl_author()}");
		    ResultSet rs = cStmt.executeQuery();		    
		    while(rs.next()) {
		    	Author auths = new Author(rs.getInt("authorId"), rs.getString("authorName"));
		    	authorList.add(auths);
		    }
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return authorList;
	}
	
	public int insertAuthor(String authName) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call insert_tbl_author(?)}");
			if(authName != null) {
				cStmt.setString(1, authName);
			}
			else {
				cStmt.setNull(1, java.sql.Types.NULL);
			}
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}

	public int updateAuthor(int authId, String authName) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call update_tbl_author(?, ?)}");
			cStmt.setInt(1, authId);
			if(authName != null) {
				cStmt.setString(2, authName);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}

	public int deleteAuthor(int authId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call delete_tbl_author(?)}");
			cStmt.setInt(1, authId);
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public ArrayList<Borrower> readBorrowers() throws SQLException {
		ArrayList<Borrower> borrowList = new ArrayList<Borrower>();
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call read_tbl_borrower()}");
		    ResultSet rs = cStmt.executeQuery();		    
		    while(rs.next()) {
		    	Borrower borrow = new Borrower(rs.getInt("cardNo"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("usrPass"));
		    	borrowList.add(borrow);
		    }
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return borrowList;
	}
	
	public int insertBorrower(String borrowName, String borrowAddress, String borrowPhone, String usrPass) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call insert_tbl_borrower(?, ?, ?, ?)}");
			cStmt.setString(1, borrowName);
			if(borrowAddress != null) {
				cStmt.setString(2, borrowAddress);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			if(borrowPhone != null) {
				cStmt.setString(3, borrowPhone);
			}
			else {
				cStmt.setNull(3, java.sql.Types.NULL);
			}
			if(usrPass != null) {
				cStmt.setString(4, usrPass);
			}
			else {
				cStmt.setNull(4, java.sql.Types.NULL);
			}
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}

	public int updateBorrower(int borrowId, String borrowName, String borrowAddress, String borrowPhone, String usrPass) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call update_tbl_borrower(?, ?, ?, ?, ?)}");
			cStmt.setInt(1, borrowId);
			if(borrowName != null) {
				cStmt.setString(2, borrowName);
			}
			else {
				cStmt.setNull(2, java.sql.Types.NULL);
			}
			if(borrowAddress != null) {
				cStmt.setString(3, borrowAddress);
			}
			else {
				cStmt.setNull(3, java.sql.Types.NULL);
			}
			if(borrowPhone != null) {
				cStmt.setString(4, borrowPhone);
			}
			else {
				cStmt.setNull(4, java.sql.Types.NULL);
			}
			if(usrPass != null) {
				cStmt.setString(5, usrPass);
			}
			else {
				cStmt.setNull(5, java.sql.Types.NULL);
			}
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public int deleteBorrower(int borrowId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call delete_tbl_borrower(?)}");
			cStmt.setInt(1, borrowId);
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	public int updateBookAuthor(int bookId, int authorId) throws SQLException {
		Connection conn = null;
		CallableStatement cStmt = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(Boolean.FALSE);
			cStmt = conn.prepareCall("{call update_book_author(?, ?)}");
			cStmt.setInt(1, bookId);
			cStmt.setInt(2, authorId);
			cStmt.executeQuery();
		    conn.commit();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    conn.rollback();
		} finally {
			if(cStmt != null) {
				cStmt.close();
			}
			if(conn != null) {
				conn.close();				
			}
		}
		return 1;
	}
	
	private String printUserType() {
		if(this.userType == 3) {
			return "User type is: " + this.userType + ", a Borrower";
		}
		else if(this.userType == 2) {
			return "User type is: " + this.userType + ", a Librarian";
		}
		else {
			return "User type is: " + this.userType + ", an Administrator";
		}
	}

	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}

	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

}
