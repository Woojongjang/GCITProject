import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Main.java
 * @author Woo-Jong Jang <jangwoojong@gmail.com>
 * Created on Apr 20, 2017
 */

public class Main {
	static ConnectionHandler cn;
	static Scanner scan = new Scanner(System.in);
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws SQLException, ParseException {
		int userId = 0;
		int userType = 0;
		int option = 0;
		//int[] idResp = new int[2];
		boolean restart = true;
		int response;
		while(restart) {
			userType = response = mainMenu(option);
			switch (userType) {
				case 1:
					boolean adminMenuLoop = true;
					Administrator uAdmin = (Administrator) userLogin(userType);
					int adminId = uAdmin.getUserId();
					while(adminMenuLoop) {
						response = adminMainMenu(uAdmin);
						if(response == -1) {
							adminMenuLoop = false;
							break;
						}
						else {
							//response = adminManageMenu(response);
							switch(response) {
								case 1:
									boolean adminBookLoop = false;
									do {
										response = adminManageBook(response);
										HashMap<Integer, Books> bookList = new HashMap<Integer, Books>();
										int bookId = 0;
										switch(response) {
											case 1:
												response = insertBook(response);
												adminBookLoop = true;
												break;
											case 2:
												listBooks(response);
												adminBookLoop = true;
												break;
											case 3:
												bookList = listBooks(response);
												bookId = chooseBook(bookList);
												updateBook(bookId, bookList);
												adminBookLoop = true;
												break;
											case 4:
												bookList = listBooks(response);
												bookId = chooseBook(bookList);
												deleteBook(bookId, bookList);
												adminBookLoop = true;
												break;
											case 5:
												adminBookLoop = false;
												break;
											default:
												adminBookLoop = false;
												break;
										}
									} while(adminBookLoop);
									break;
								case 2:
									boolean adminAuthorLoop = false;
									do {
										response = adminManageAuthor(response);
										HashMap<Integer, Author> authorList = new HashMap<Integer, Author>();
										int authId = 0;
										switch(response) {
											case 1:
												response = insertAuthor(response);
												adminAuthorLoop = true;
												break;
											case 2:
												listAuthors(response);
												adminAuthorLoop = true;
												break;
											case 3:
												authorList = listAuthors(response);
												authId = chooseAuthor(authorList);
												updateAuthor(authId, authorList);
												adminAuthorLoop = true;
												break;
											case 4:
												authorList = listAuthors(response);
												authId = chooseAuthor(authorList);
												deleteAuthor(authId, authorList);
												adminAuthorLoop = true;
												break;
											case 5:
												adminAuthorLoop = false;
												break;
											default:
												adminAuthorLoop = false;
												break;
										}
									} while(adminAuthorLoop);
									break;
								case 3:
									boolean publisherLoop = false;
									do {
										response = adminManagePublisher(response);
										HashMap<Integer, Publisher> pubList = new HashMap<Integer, Publisher>();
										int pubId = 0;
										switch(response) {
											case 1:
												response = insertPublisher(response);
												publisherLoop = true;
												break;
											case 2:
												listPublishers(response);
												publisherLoop = true;
												break;
											case 3:
												pubList = listPublishers(response);
												pubId = choosePublisher(pubList);
												updatePublisher(pubId, pubList);
												publisherLoop = true;
												break;
											case 4:
												pubList = listPublishers(response);
												pubId = choosePublisher(pubList);
												deletePublisher(pubId, pubList);
												publisherLoop = true;
												break;
											case 5:
												publisherLoop = false;
												break;
											default:
												publisherLoop = false;
												break;
										}
									} while(publisherLoop);
									break;
								case 4:
									boolean adminBranchLoop = false;
									do {
										response = adminManageBranch(response);
										HashMap<Integer, LibraryBranches> branchList = new HashMap<Integer, LibraryBranches>();
										int branchId = 0;
										switch(response) {
											case 1:
												response = insertBranch(response);
												adminBranchLoop = true;
												break;
											case 2:
												showLibraries(userType);
												adminBranchLoop = true;
												break;
											case 3:
												branchList = showLibraries(userType);
												branchId = chooseBranch(branchList);
												branchUpdate(branchId, branchList);
												adminBranchLoop = true;
												break;
											case 4:
												branchList = showLibraries(userType);
												branchId = chooseBranch(branchList);
												deleteBranch(branchId, branchList);
												adminBranchLoop = true;
												break;
											case 5:
												adminBranchLoop = false;
												break;
											default:
												adminBranchLoop = false;
												break;
										}
									} while(adminBranchLoop);
									break;
								case 5:
									boolean adminBorrowLoop = false;
									do {
										response = adminManageBorrower(response);
										HashMap<Integer, Borrower> borrowerList = new HashMap<Integer, Borrower>();
										int borrowerId = 0;
										switch(response) {
											case 1:
												response = insertBorrower(response);
												adminBorrowLoop = true;
												break;
											case 2:
												listBorrowers(response);
												adminBorrowLoop = true;
												break;
											case 3:
												borrowerList = listBorrowers(response);
												borrowerId = chooseBorrower(borrowerList);
												updateBorrower(borrowerId, borrowerList);
												adminBorrowLoop = true;
												break;
											case 4:
												borrowerList = listBorrowers(response);
												borrowerId = chooseBorrower(borrowerList);
												deleteBorrower(borrowerId, borrowerList);
												adminBorrowLoop = true;
												break;
											case 5:
												borrowerList = listBorrowers(response);
												borrowerId = chooseBorrower(borrowerList);
												HashMap<int[], String[]> borrowerLoans = showUserBookLoans(borrowerId,userType);
												updateBorrowerLoans(borrowerId, borrowerLoans, adminId);
												adminBorrowLoop = true;
												break;
											case 6:
												adminBorrowLoop = false;
												break;
											default:
												adminBorrowLoop = false;
												break;
										}
									} while(adminBorrowLoop);
									break;
//								case 6:
//									boolean adminBookAuthorLoop = false;
//									do {
//										
//									}while(adminBookAuthorLoop);
//									break;
								default:
									break;
							}
							if(response == -1) {
								adminMenuLoop = true;
							}
						}
					}
					break;
				case 2:
					boolean librarianMenuLoop = true;
					Librarian lUser = (Librarian) userLogin(userType);
					userId = lUser.getUserId();
					while(librarianMenuLoop) {
						response = librarianMainMenu(lUser);
						switch(response) {
							case 1:
								boolean branchPickLoop = false;
								do {
									HashMap<Integer, LibraryBranches> libs = showLibraries(userId);
									response = librarianPickBranch(libs);
									int branchId = response;
									if(response == -1) {
										break;
									}
									else {
										boolean branchUpdateLoop = false;
										do {
											response = librarianBranchManage(branchId, libs);
											switch(response) {
												case 1: // update library info
													response = branchUpdate(branchId, libs);
//													if(response == -1) {
//														branchUpdateLoop = true;
//													}
//													else {
//														branchUpdateLoop = true;
//													}
													branchUpdateLoop = true;
													branchPickLoop = false;
													break;
												case 2: // add books count
													HashMap<Integer, Books> bookList = showLibraryBooks(branchId, 0);
													response = updateBookList(branchId, libs, bookList);
													branchPickLoop = false;
													branchUpdateLoop = true;
													break;
												case 3: // quit to branch choose menu
													branchPickLoop = true;
													branchUpdateLoop = false;
													break;
												default:
													break;
											}
										}while(branchUpdateLoop);
									}
								}while(branchPickLoop);
								break;
							case 2:
								librarianMenuLoop = false;
								break;
							default:
								break;
						}
					}
					break;
				case 3:
					boolean borrowMenuLoop = true;
					Borrower bUser = (Borrower) userLogin(userType);
					userId = bUser.getUserId();
					while(borrowMenuLoop) {
						response = borrowerMainMenu(bUser);
						switch (response) {
							case 1: // borrower checkout a book
								HashMap<Integer, LibraryBranches> libraries = showLibraries(userType);
								response = borrowPickBranch(userType, libraries);
								if(response == -1) {
									break;
								}
								else {
									boolean borrowLoop = false;
									int branch = response;
									do {
										response = borrowCheckout(branch, userId);
										if(response == 1) {
											borrowLoop = true;
										}
										else {
											borrowLoop = false;
										}
									}while(borrowLoop);
								}
								break;
							case 2: // borrower returning a book
								borrowerReturn(userId);
								break;
							case 3: // borrower quit to previous menu
								borrowMenuLoop = false;
								break;
							default:
								break;
						}
						// if check for log out
					}
					break;
				default:
					break;
			}
			System.out.println("Would you like to exit the program? (y/n):");
			while(scan.hasNext()) {
				String quitter = scan.next();
				scan.nextLine();
				if(quitter.equals("y")) {
					restart = false;
					break;
				}
				else if(quitter.equals("n")) {
					restart = true;
					break;
				}
				else {
					System.out.println("Please input 'y' or 'n':");
				}
			}
			//break; // break loop to main menu
		}
		scan.close();
		System.out.println("Program Closing.");
		System.exit(0);
	}
	
	private static int mainMenu(int userInput) throws SQLException {
		int option = 0;
		
		System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you");
		System.out.println("1) Administrator");
		System.out.println("2) Librarian");
		System.out.println("3) Borrower");
		System.out.println("(Type in the number 1, 2, or 3):");

		//Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(option > 3 || option < 1) {
					System.out.println("Please input an integer 1, 2, or 3.");
				}
				else {
					//userLogin(option);
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				System.out.println(alph + "is not valid. Please input an integer 1, 2, or 3.");
			}
		}
		
		return option;
	}
	
	private static User userLogin(int userInput) {
		int userId = 0;
		User currentUser = null;
		//Scanner scan = new Scanner(System.in);
		if(userInput == 3) {
			System.out.println(userInput + ": user is Borrower");
			currentUser = new Borrower();
			System.out.println("Hello Borrower, Please input your card number:");
			while(scan.hasNext()) {
				if(scan.hasNextInt()) {
					userId = scan.nextInt();
					scan.nextLine();
					currentUser.setUserId(userId);
					System.out.println("userid: " + userId);
					break;
				}
				else {
					String temp = scan.nextLine();
					if(temp.contains("exitProgram")) {
						break;
					}
					System.out.println(temp + " is not valid. Please input an integer.");
				}
			}
		}
		else if(userInput == 2) {
			System.out.println(userInput + ": user is Librarian");
			currentUser = new Librarian();
			System.out.println("Hello Librarian.");
//			while(scan.hasNext()) {
//				if(scan.hasNextInt()) {
//					userId = scan.nextInt();
//					scan.nextLine();
//					currentUser.setUserId(userId);
//					System.out.println("userid: " + userId);
//					break;
//				}
//				else {
//					String temp = scan.nextLine();
//					if(temp.contains("exitProgram")) {
//						break;
//					}
//					System.out.println(temp + " is not valid. Please input an integer.");
//				}
//			}
		}
		else if(userInput == 1) {
			System.out.println(userInput + ": user is Administrator");
			currentUser = new Administrator();
			System.out.println("Hello Administrator.");
		}
		else {
			return null;
		}
		
		return currentUser;
	}
	
	private static int librarianMainMenu(Librarian userInput) throws SQLException {
		//int ret[] = new int[2];
		int option = 0;
		int userId = userInput.getUserId();
		//Scanner scan = new Scanner(System.in);
		//System.out.println("User id: " + userId + " is a Borrower");
		cn = new ConnectionHandler(userId, "pass1");

		System.out.println("What would you like to do?");
		System.out.println("1) Enter Branch you manage");
		System.out.println("2) Quit to Previous (takes you to main menu)");
		System.out.println("(Type in the number 1 or 2):");

		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(option == 1) {
					System.out.println("1) Entering Branch Menu");
					//borrowCheckOut(userId);
					break;
				}
				else if(option == 2){
					System.out.println("2) Quitting to Previous (taking you to menu MAIN)");
					//mainMenu(option);
					break;
				}
				else {
					System.out.println("(Type in the number 1 or 2)");
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 or 2).");
			}
		}
		//ret[0] = userId;
		//ret[1] = option;
		//return ret;
		return option;
	}
	
	private static int librarianPickBranch(HashMap<Integer, LibraryBranches> libraries) throws SQLException {
		int option = 0;
		int prev = 0;
		System.out.println("Pick the Branch you want to maintain (input ID of the branch):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!libraries.containsKey(option) && option != 0) {
					System.out.println("Please input an Existing Library Branch.");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				System.out.println(alph + "is not valid. " + "Please input an integer.");
			}
		}
		if(option == prev) {
			return -1;
		}
		return option;
	}
	
	private static int librarianBranchManage(int branchId, HashMap<Integer, LibraryBranches> libraries) throws SQLException {
		int option = 0;
		System.out.println("Chose Branch ID: " + branchId + " Branch Name: " + libraries.get(branchId).getBranchName());
		System.out.println("1) Update the details of the Library");
		System.out.println("2) Add copies of Book to the Branch");
		System.out.println("3) Quit to previous (Branch Choosing Menu)");
		System.out.println("What would you like to do? (input 1 to 3):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(option > 3 || option < 1) {
					System.out.println("Please input an integer 1 to 3.");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				System.out.println(alph + "is not valid. " + "Please input an integer 1 to 3.");
			}
		}
		return option;
	}

	private static int updateBookList(int branchId, HashMap<Integer, LibraryBranches> libraries, HashMap<Integer, Books> libraryBookList) throws SQLException {
//		String name = null;
//		String address = null;
		int option = 0;
		int bookId = 0;
		int bSize = libraryBookList.size();
		int count = 0;
		int arith = -1;
		String input = null;
		System.out.println("You have chosen to update the book list in " + libraries.get(branchId).getBranchName()+" With Branch Id: " + branchId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		if(bSize != 0) {
			System.out.println("Please enter the Book ID to add to, OR input an ID not in the list to add a new Book to the Library:");
		}
		else {
			System.out.println("Library Book List is Empty. Enter '0':");
		}
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				if(bSize != 0) {
					bookId = scan.nextInt();
					scan.nextLine();
				}
				else{
					System.out.println("Library Book List is Empty. Enter '0':");
				}
				if(!libraryBookList.containsKey(bookId)) {
					if(bookId != 0) {
						System.out.println("Book ID: "+bookId+" is not in the Libary.");
					}
					System.out.println("Would you like to add a new Book to the Libary?");
					System.out.println("(input 'y' for yes,'n' for no, or 'quit' to cancel operation):");
					while(scan.hasNext()) {
						input = scan.next();
						scan.nextLine();
						if(input.equals("y")) {
							HashMap<Integer, Books> allBookList = listBooks(2);
							System.out.println("Please enter the Book ID to add newly to the Library:");
							while(scan.hasNext()) {
								if(scan.hasNextInt()) {
									bookId = scan.nextInt();
									scan.nextLine();
									if(allBookList.containsKey(bookId)) {
										break;
									}
									else {
										System.out.println("Please input an existing Book ID:");
									}
								}
								else {
									String alph = scan.nextLine();
									if(alph.equals("quit")) {
										return -1;
									}
									else {
										System.out.println(alph + "is not valid. Please input an integer.");
									}
								}
							}
							System.out.println("Please enter how many books:");
							while(scan.hasNext()) {
								if(scan.hasNextInt()) {
									count = scan.nextInt();
									scan.nextLine();
									if(count < 0) {
										System.out.println("Book Count must be greater than 0.");
									}
									else {
										break;
									}
									//bookCountMath
								}
								else {
									String alph = scan.nextLine();
									if(alph.equals("quit")) {
										return -1;
									}
									else {
										System.out.println(alph + "is not valid. Please input an integer.");
									}
								}
							}
							System.out.println("Branch ID: "+ branchId+" Adding newly Book ID: "+bookId +" amount: "+count);
							cn.bookCountMath(branchId, bookId, 1, count);
							return -1;
							// add count to booklist library
						}
						else if(input.equals("n")) {
							if(bSize != 0) {
								System.out.println("If not adding a New Book ID, please input an existing Book ID within the Library:");
							}
							else {
								System.out.println("Library book list is empty. Quitting out of Library Book List Update.");
								return -1;
							}
							break;
						}
						else if(input.equals("quit")){
							System.out.println("Quitting out of Library Book List Update.");
							return -1;
						}
						else {
							System.out.println("Please input 'y' for yes,'n' for no, or 'quit' to cancel operation:");
						}
					}
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.equals("quit")) {
					System.out.println("Quitting out of Library Book List Update");
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. " + "Please input an integer.");
				}
			}
		}
		System.out.println("Please enter how many books:");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				count = scan.nextInt();
				scan.nextLine();
				if(count < 0) {
					System.out.println("Book Count must be greater than 0.");
				}
				else {
					break;
				}
				//bookCountMath
			}
			else {
				String alph = scan.nextLine();
				if(alph.equals("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer.");
				}
			}
		}
		System.out.println("Input 1 to add to database or 0 to subtract from database:");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				arith = scan.nextInt();
				scan.nextLine();
				if(arith == 0 || arith == 1) {
					break;
				}
				else {
					System.out.println("Please input 1 or 0:");
				}
				//bookCountMath
			}
			else {
				String alph = scan.nextLine();
				if(alph.equals("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer 1 (add) or 0 (subtract).");
				}
			}
		}
		//cn.updateBranch(branchId, name, address);
		cn.bookCountMath(branchId, bookId, arith, count);
		return option;
	}
	
	private static int branchUpdate(int branchId, HashMap<Integer, LibraryBranches> libraries) throws SQLException {
		if(branchId == -1) {
			System.out.println("Quitting Branch Update.");
			return -1;
		}
		String name = null;
		String address = null;
		int option = 0;
		System.out.println("You have chosen to update the Branch with: " + branchId + " and Branch Name: " + libraries.get(branchId).getBranchName());
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter new branch name or enter N/A for no change:");
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("N/A")) {
				System.out.println("Chosen NOT to change name.");
				name = null;
				break;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Branch Name less than 45 characters.");
			}
			else if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Branch Update.");
				return -1;
			}
			else {
				System.out.println("Changing name.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new branch address or enter N/A for no change:");
		while(scan.hasNext()) {
			address = scan.nextLine();
			if(address.equals("N/A")) {
				System.out.println("Chosen NOT to change address.");
				address = null;
				break;
			}
			else if (address.length() > 45) {
				System.out.println("Please insert a Publisher Address less than 45 characters.");
			}
			else if(address.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Branch Update.");
				return -1;
			}
			else {
				System.out.println("Changing address.");
				option += 1;
				break;
			}
		}
		if(address != null) {
			libraries.get(branchId).setAddress(address);
		}
		if(name != null) {
			libraries.get(branchId).setBranchName(name);
		}
		if(address == null && name == null) {
			System.out.println("Chosen NOT to change Either.");
		}
		else {
			cn.updateBranch(branchId, name, address);			
		}
		return option;
	}

	private static int borrowerMainMenu(Borrower userInput) throws SQLException {
		//int ret[] = new int[2];
		int option = 0;
		int userId = userInput.getUserId();
		//Scanner scan = new Scanner(System.in);
		//System.out.println("User id: " + userId + " is a Borrower");
		cn = new ConnectionHandler(userId, "pass1");

		System.out.println("What would you like to do?");
		System.out.println("1) Check out a book");
		System.out.println("2) Return a Book");
		System.out.println("3) Quit to Previous (takes you to main menu)");
		System.out.println("(Type in the number 1, 2, or 3):");

		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(option == 1) {
					System.out.println("1) Checking out a book");
					//borrowCheckOut(userId);
					break;
				}
				else if(option == 2) {
					System.out.println("2) Returning a Book");
					break;
				}
				else if(option == 3){
					System.out.println("3) Quitting to Previous (should take you menu MAIN)");
					//mainMenu(option);
					break;
				}
				else {
					System.out.println("(Type in the number 1, 2, or 3)");
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 to 3).");
			}
		}
		//ret[0] = userId;
		//ret[1] = option;
		//return ret;
		return option;
	}
	
	private static HashMap<Integer, LibraryBranches> showLibraries(int userType) throws SQLException {
		//int option = 0;
		//int numOfOptions = 0;
		ArrayList<LibraryBranches> libraries = cn.getBranches();
		HashMap<Integer, LibraryBranches> hMap = new HashMap<Integer, LibraryBranches>();
		if(libraries == null) {
			System.out.println("List of libraries is empty");
			//numOfOptions = 1;
		}
		else {
			for(LibraryBranches entry : libraries) {
				int branchId = entry.getBranchId();
				String branchName = entry.getBranchName();
				hMap.put(branchId, entry);
				System.out.println(branchId + ") " + branchName + " - Branch Address: " + entry.getAddress());
			}
			if(userType != 1) {
				//numOfOptions = libraries.size()+1;
				System.out.println(0 +") Quit to previous ");
			}
		}
		return hMap;
	}

	private static int borrowPickBranch(int userInput, HashMap<Integer, LibraryBranches> libraries) throws SQLException {
		int option = 0;
		int prev = 0;
		//Scanner scan = new Scanner(System.in);
		//ConnectionHandler cn = new ConnectionHandler(userInput, "pass1");
		//ArrayList<LibraryBranches> libraries = cn.getBranches();
		//prev = showLibraries(userInput).size() + 1;
		System.out.println("Pick the Branch you want to check out from (input ID of the branch):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!libraries.containsKey(option) && option != 0) {
					System.out.println("Please input an Existing Library ID.");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				System.out.println(alph + "is not valid. Please input an integer.");
			}
		}
		
		//System.out.println("BookID	Title	Author	Num of Copies");
	    if(option == prev) {
	    	return -1;
	    }
//	    else {
//	    	int reborrow = borrowCheckout(option);
////	    	if(reborrow == -2) {
////	    		return -2;
////	    	}
//	    }
	    //System.out.println("Pick the book you want to check out from (input ID of the book):");
		return option;
	}
	
	private static HashMap<Integer, Books> showLibraryBooks(int branch, int dontShowZero) throws SQLException {
		HashMap<Books, Integer> hMap = cn.getBranchBooks(branch, dontShowZero);
		HashMap<Integer, Books> hMapRet = new HashMap<Integer, Books>();
		Iterator<Entry<Books, Integer>> it = hMap.entrySet().iterator();
		if(!hMap.isEmpty()) {
			System.out.printf("%-5.30s  %-40.80s  %-20.70s  %-5.30s%n", "BookID", "Title", "Author", "Num of Copies");
	    	while (it.hasNext()) {
	            Map.Entry<Books, Integer> pair = it.next();
	            int bookId = pair.getKey().getBookId();
	            hMapRet.put(bookId, pair.getKey());
	            System.out.printf("%-6.30s  %-40.80s  %-20.70s  %-5.30s%n", bookId, pair.getKey().getTitle(), pair.getKey().getAuthor(), pair.getValue().intValue());
	            //System.out.println(pair.getKey().getBookId()+"	"+ pair.getKey().getTitle()+"	"+pair.getKey().getAuthor()+"	"+pair.getValue().intValue());
	            it.remove(); // avoids a ConcurrentModificationException
	        }			
		}
		else {
			System.out.println("Library is Empty.");
		}
		return hMapRet;
	}
	
	private static int borrowCheckout(int branch, int userId) throws SQLException {
		int option = 0;
		HashMap<Integer, Books> hMap = showLibraryBooks(branch, 1);
		int hSize = hMap.size();
		if(hSize == 0) {
			System.out.println("Quitting out of Book Check Out because Library is Empty.");
			return -1;
		}
		System.out.println("Pick the Book you want to check out (input ID of the book or 'quit' to go to borrower menu):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!hMap.containsKey(option)) {
					System.out.println("Please input an existing Book ID:");
				}
				else {
					//removeBook(branch, option);
					
					cn.bookCountMath(branch, option, 0, 1);
					System.out.println("Updated Library Books: branch: "+branch +" bookid " + option);
					//showLibraryBooks(branch, 1);
					//insert book loans table
					cn.insertBookLoan(branch, option, userId);
					
					System.out.println("Would you like to borrow more books from this branch? (y/n):");
					while(scan.hasNext()) {
						String more = (String) scan.next();
						if(more.equals("y")) {
							return 1;
						}
						else if(more.equals("n")) {
							return 0;
						}
						else {
							System.out.println("Please input 'y' or 'n':");
						}
					}
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer.");
				}
			}
		}
		return option;
	}
	
	private static HashMap<int[], String[]> showUserBookLoans(int userId, int userType) throws SQLException {
		//HashMap<Integer, HashMap<Integer, String[]>> bookDateLoaned = new HashMap<Integer, HashMap<Integer, String[]>>();
		HashMap<int[], String[]> bookBranchLoaned = new HashMap<int[], String[]>();
		
		ArrayList<BookLoan> bookLoanList = new ArrayList<BookLoan>();
		bookLoanList = cn.readBookLoan(userId, userType);
		//int numOfOptions = 0;
		if(!bookLoanList.isEmpty()) {
			System.out.printf("%-5.30s  %-39.80s  %-5.30s  %-20.30s  %-19.30s  %-10.30s%n", "Book ID", "Title", "Branch ID", "Branch Name", "Date Checked Out", "Due Date");
			for(BookLoan entry : bookLoanList) {
				int bookId = entry.getBook().getBookId();
				int branchId = entry.getBranch().getBranchId();
				//String dateChecked = entry.getDateOut();
	//			String[] dates = new String[2];
	//			dates[0] = entry.getDateOut();
	//			dates[1] = entry.getDateDue();
	//			int[] bookBranch = new int[2];
	//			bookBranch[0] = bookId;
	//			bookBranch[1] = branchId;
				if(entry.getDateIn() == null) {
					String[] dates = new String[2];
					dates[0] = entry.getDateOut();
					dates[1] = entry.getDateDue();
					int[] bookBranch = new int[2];
					bookBranch[0] = bookId;
					bookBranch[1] = branchId;
					System.out.printf("%-7.30s  %-39.80s  %-9.30s  %-20.30s  %-16.30s  %-10.30s%n", bookId, entry.getBook().getTitle(), branchId, entry.getBranch().getBranchName(), dates[0], dates[1]);
					bookBranchLoaned.put(bookBranch, dates);
				}
				//System.out.printf("%-7.30s  %-39.80s  %-9.30s  %-20.30s  %-16.30s  %-10.30s%n", bookId, entry.getBook().getTitle(), branchId, entry.getBranch().getBranchName(), dates[0], dates[1]);
				//System.out.println(entry.toString());
				//bookBranchLoaned.put(bookBranch, dates);
				//bookDateLoaned.put(bookId, bookBranchLoaned);
			}
		}
		else {
			System.out.println("User Book Loans List is EMPTY");
		}
		return bookBranchLoaned;
	}
	
	private static int borrowerReturn(int userId) throws SQLException {
		HashMap<int[], String[]> bookDateLoaned = showUserBookLoans(userId, 3);
//		Set<Entry<Integer, HashMap<Integer, Date>>> hMapIt = bookBranchLoaned.entrySet();
//		for (Entry<Integer, HashMap<Integer, Date>> entry: hMapIt) {
//			
//			System.out.print("BranchId's: "+entry.getKey());
//			System.out.println(" BookId's and Date: "+entry.getValue().toString());
//		}
		int bookId = 0;
		int branchId = 0;
		String[] dates = new String[2];
		int[] bookBranch = new int[2];
		//boolean retry = false;
		boolean contains = false;
		dates[0] = "emptyDate";
		dates[1] = "emptyDate";
//		do {
//			
//		}while(retry);
		System.out.println("Which book would you like to return? (input Book ID or 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				bookId = scan.nextInt();
				scan.nextLine();
				bookBranch[0] = bookId;
				for(Entry<int[], String[]> entry : bookDateLoaned.entrySet()) {
					if(entry.getKey()[0] == bookId) {
						contains = true;
						break;
					}
				}
				if(contains) {
					contains = false;
					break;
				}
				else {
					System.out.println("BookId: "+bookBranch[0]+" That Book Id does not exist from borrowed list.");
				}
				//scan.nextLine();
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return 0;
				}
				System.out.println("Please input an integer:");
			}
		}
		System.out.println("Which library is it from? (input Library Branch ID or 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				branchId = scan.nextInt();
				bookBranch[1] = branchId;
				scan.nextLine();
				for(Entry<int[], String[]> entry : bookDateLoaned.entrySet()) {
					if(entry.getKey()[0] == bookId) {
						if(entry.getKey()[1] == branchId) {
							contains = true;
							dates[0] = entry.getValue()[0];
							dates[1] = entry.getValue()[1];
							break;
						}
					}
				}
				if(!contains) {
					System.out.println("BookId: "+bookBranch[0] +", BranchId: "+bookBranch[1]+" That Book Id from Branch Id does not exist from borrowed list.");
					System.out.println("Which library is it from? (input Library Branch ID or 'quit' to cancel operation):");
					//retry = true;
					//break;
				}
				else {
					System.out.println("Checked-out Date: "+dates[0]);
					System.out.println("Due Date        : "+dates[1]);
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return 0;
				}
				System.out.println("Please input an integer:");
			}
		}
		
		// call connectionHandler return book function
		Date dateNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		String now = ft.format(dateNow);
		System.out.println("Date Now        : "+ now);
		cn.bookCountMath(branchId, bookId, 1, 1);
		cn.updateBookLoan(bookId, branchId, userId, dates[0], null, now);
		return 0;
	}

	private static int adminMainMenu(Administrator userInput) throws SQLException {
		int option = 0;
		int userId = userInput.getUserId();
		//Scanner scan = new Scanner(System.in);
		//System.out.println("User id: " + userId + " is a Borrower");
		cn = new ConnectionHandler(userId, "pass1");

		System.out.println("What would you like to do?");
		System.out.println("1) Manage Books");
		System.out.println("2) Manage Authors");
		System.out.println("3) Manage Publishers");
		System.out.println("4) Manage Library Branches");
		System.out.println("5) Manage Borrowers");
		System.out.println("6) Quit to Previous (takes you to main menu)");
		System.out.println("(Type in the number 1 to 6):");

		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				switch(option) {
					case 1:
						System.out.println("1) Entering Books Menu");
						return option;
					case 2:
						System.out.println("2) Entering Authors Menu");
						return option;
					case 3:
						System.out.println("3) Entering Publishers Menu");
						return option;
					case 4:
						System.out.println("4) Entering Library Branch Menu");
						return option;
					case 5:
						System.out.println("5) Entering Borrowers Menu");
						return option;
					case 6:
						System.out.println("6) Quitting to Previous (taking you to menu MAIN)");
						return -1;
					default:
						System.out.println("(Type in the number 1 to 6)");
						break;
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 to 2).");
			}
		}
		return option;
	}
	
	private static int adminManagePublisher(int userInput) throws SQLException {
		int option = 0;
		System.out.println("What Would You Like To Do?");
		System.out.println("1) Insert a New Publisher");
		System.out.println("2) List All Publishers");
		System.out.println("3) Update Publisher Info");
		System.out.println("4) Delete a Publisher");
		System.out.println("5) Quit to Previous (takes you to Admin Main Menu)");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				switch(option) {
					case 1:
						System.out.println("1) Inserting a New Publisher");
						//insertPublisher(option);
						return option;
					case 2:
						System.out.println("2) Listing Publishers");
						//listPublishers(option);
						return option;
					case 3:
						System.out.println("3) Updating Publisher Info");
						return option;
					case 4:
						System.out.println("4) Deleting a Publisher");
						return option;
					case 5:
						System.out.println("5) Quit to Previous (taking you to Admin Main Menu)");
						return -1;
					default:
						System.out.println("(Type in the number 1 to 5)");
						break;
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 to 5).");
			}
		}
		return option;
	}
	
	private static int adminManageBook(int userInput) throws SQLException {
		int option = 0;
		System.out.println("What Would You Like To Do?");
		System.out.println("1) Insert a New Book");
		System.out.println("2) List All Books");
		System.out.println("3) Update Book Info");
		System.out.println("4) Delete a Book");
		System.out.println("5) Quit to Previous (takes you to Admin Main Menu)");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				switch(option) {
					case 1:
						System.out.println("1) Inserting a New Book");
						//insertPublisher(option);
						return option;
					case 2:
						System.out.println("2) Listing Books");
						//listPublishers(option);
						return option;
					case 3:
						System.out.println("3) Updating Book Info");
						return option;
					case 4:
						System.out.println("4) Deleting a Book");
						return option;
					case 5:
						System.out.println("5) Quit to Previous (taking you to Admin Main Menu)");
						return -1;
					default:
						System.out.println("(Type in the number 1 to 5)");
						break;
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 to 5).");
			}
		}
		return option;
	}
	
	private static int adminManageAuthor(int userInput) throws SQLException {
		int option = 0;
		System.out.println("What Would You Like To Do?");
		System.out.println("1) Insert a New Author");
		System.out.println("2) List All Authors");
		System.out.println("3) Update Author Info");
		System.out.println("4) Delete a Author");
		System.out.println("5) Quit to Previous (takes you to Admin Main Menu)");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				switch(option) {
					case 1:
						System.out.println("1) Inserting a New Author");
						//insertPublisher(option);
						return option;
					case 2:
						System.out.println("2) Listing Authors");
						//listPublishers(option);
						return option;
					case 3:
						System.out.println("3) Updating Author Info");
						return option;
					case 4:
						System.out.println("4) Deleting a Publisher");
						return option;
					case 5:
						System.out.println("5) Quit to Previous (taking you to Admin Main Menu)");
						return -1;
					default:
						System.out.println("(Type in the number 1 to 5)");
						break;
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 to 5).");
			}
		}
		return option;
	}

	private static int adminManageBranch(int userInput) throws SQLException {
		int option = 0;
		System.out.println("What Would You Like To Do?");
		System.out.println("1) Insert a New Library Branch");
		System.out.println("2) List All Library Branches");
		System.out.println("3) Update Library Branch Info");
		System.out.println("4) Delete a Library Branch");
		System.out.println("5) Quit to Previous (takes you to Admin Main Menu)");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				switch(option) {
					case 1:
						System.out.println("1) Inserting a New Library Branch");
						//insertPublisher(option);
						return option;
					case 2:
						System.out.println("2) Listing Library Branches");
						//listPublishers(option);
						return option;
					case 3:
						System.out.println("3) Updating Library Branch Info");
						return option;
					case 4:
						System.out.println("4) Deleting a Library Branch");
						return option;
					case 5:
						System.out.println("5) Quit to Previous (taking you to Admin Main Menu)");
						return -1;
					default:
						System.out.println("(Type in the number 1 to 5)");
						break;
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 to 5).");
			}
		}
		return option;
	}

	private static int adminManageBorrower(int userInput) throws SQLException {
		int option = 0;
		System.out.println("What Would You Like To Do?");
		System.out.println("1) Insert a New Borrower");
		System.out.println("2) List All Borrowers");
		System.out.println("3) Update Borrower Info");
		System.out.println("4) Delete a Borrower");
		System.out.println("5) Override Book's Due Date");
		System.out.println("6) Quit to Previous (takes you to Admin Main Menu)");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				switch(option) {
					case 1:
						System.out.println("1) Inserting a New Borrower");
						//insertPublisher(option);
						return option;
					case 2:
						System.out.println("2) Listing Borrowers");
						//listPublishers(option);
						return option;
					case 3:
						System.out.println("3) Updating Borrower Info");
						return option;
					case 4:
						System.out.println("4) Deleting a Borrower");
						return option;
					case 5:
						System.out.println("5) Overriding a Book's Due Date");
						return option;
					case 6:
						System.out.println("6) Quit to Previous (taking you to Admin Main Menu)");
						return -1;
					default:
						System.out.println("(Type in the number 1 to 6)");
						break;
				}
			}
			else {
				String temp = scan.nextLine();
				if(temp.contains("exitProgram")) {
					return -2;
				}
				System.out.println(temp + " is not valid. Please input an integer (1 to 6).");
			}
		}
		return option;
	}
	
	private static int insertPublisher(int userInput) throws SQLException {
		System.out.println("You have chosen to insert a New Publisher");
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter a new Publisher Name:");
		int option = 0;
		String name = null;
		String address = null;
		String phone = null;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Publisher Insert.");
				return -1;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Publisher Name less than 45 characters.");
			}
			else {
				System.out.println("Inserting a new Publisher Name.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Publisher address or enter N/A for no address:");
		while(scan.hasNext()) {
			address = scan.nextLine();
			if(address.equals("N/A")) {
				System.out.println("Chosen NOT to input address.");
				address = null;
				break;
			}
			else if (address.length() > 45) {
				System.out.println("Please insert a Publisher Address less than 45 characters.");
			}
			else if(address.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Publisher Insert.");
				return -1;
			}
			else {
				System.out.println("Inserting a new Publisher Address.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Publisher phone or enter N/A for no phone:");
		while(scan.hasNext()) {
			phone = scan.nextLine();
			if(phone.equals("N/A")) {
				System.out.println("Chosen NOT to input phone.");
				phone = null;
				break;
			}
			else if (phone.length() > 45) {
				System.out.println("Please insert a Publisher Phone less than 45 characters.");
			}
			else if(phone.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Publisher Insert.");
				return -1;
			}
			else {
				System.out.println("Inserting a new Publisher Phone.");
				option += 1;
				break;
			}
		}
//		if(address != null) {
//			libraries.get(branchId).setAddress(address);
//		}
//		if(name != null) {
//			libraries.get(branchId).setBranchName(name);
//		}
//		if(address == null && name == null) {
//			System.out.println("Chosen NOT to change Either.");
//		}
		if(address != null && phone != null) {
			System.out.println("Inserting Publisher: " + name + ", "+address+", "+phone);
		}
		else if(phone != null) {
			System.out.println("Inserting Publisher: " + name + ", null,"+phone);
		}
		else {
			System.out.println("Inserting Publisher: " + name + ", "+address+", null");
		}
		cn.insertPublisher(name, address, phone);
		return option;
	}
	
	private static HashMap<Integer, Publisher> listPublishers(int userInput) throws SQLException {
		ArrayList<Publisher> pubArray = cn.readPublishers();
		HashMap<Integer, Publisher> pubList = new HashMap<Integer, Publisher>();
		System.out.printf("%-10.30s  %-26.80s  %-50.70s  %-5.30s%n", "PublisherID", "Name", "Address", "Phone Number");
		for(Publisher entry : pubArray) {
			int pubId = entry.getPubId();
			pubList.put(pubId, entry);
			System.out.printf("%-10.30s  %-26.80s  %-50.70s  %-5.30s%n", pubId, entry.getName(), entry.getAddress(), entry.getPhone());
		}
		return pubList;
	}
	
	private static int updatePublisher(int pubId, HashMap<Integer, Publisher> pubList) throws SQLException {
		if(pubId == -1) {
			System.out.println("Quitting Publisher Update.");
			return -1;
		}
		System.out.println("You have chosen to update a Publisher ID: "+pubId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter new Publisher Name or enter N/A for not changing name:");
		int option = 0;
		String name = null;
		String address = null;
		String phone = null;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("N/A")) {
				System.out.println("Chosen NOT to change name.");
				name = null;
				break;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Publisher Name less than 45 characters.");
			}
			else if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Publisher Update.");
				return -1;
			}
			else {
				System.out.println("Changing Publisher Name.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Publisher address or enter N/A for not changing address:");
		while(scan.hasNext()) {
			address = scan.nextLine();
			if(address.equals("N/A")) {
				System.out.println("Chosen NOT to change address.");
				address = null;
				break;
			}
			else if (address.length() > 45) {
				System.out.println("Please insert a Publisher Address less than 45 characters.");
			}
			else if(address.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Publisher Update.");
				return -1;
			}
			else {
				System.out.println("Changing Publisher Address.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Publisher phone or enter N/A for not changing phone:");
		while(scan.hasNext()) {
			phone = scan.nextLine();
			if(phone.equals("N/A")) {
				System.out.println("Chosen NOT to change phone.");
				phone = null;
				break;
			}
			else if (phone.length() > 45) {
				System.out.println("Please insert a Publisher Phone less than 45 characters.");
			}
			else if(phone.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Publisher Update.");
				return -1;
			}
			else {
				System.out.println("Changing a Publisher Phone.");
				option += 1;
				break;
			}
		}
		if(name != null || address != null || phone != null) {
			System.out.println("Changing Publisher ID: "+pubId);
		}
		else {
			System.out.println("NOT Changing Publisher ID: "+pubId);
		}
		if(name != null) {
			pubList.get(pubId).setName(name);
			System.out.println("Changing Publisher Name to: "+name);
		}
		if(address != null) {
			pubList.get(pubId).setAddress(address);;
			System.out.println("Changing Publisher Address to: "+address);
		}
		if(phone != null) {
			pubList.get(pubId).setPhone(phone);;
			System.out.println("Changing Publisher Phone to: "+phone);
		}
		cn.updatePublisher(pubId, name, address, phone);
		return option;
	}
	
	private static int choosePublisher(HashMap<Integer, Publisher> pubList) throws SQLException {
		int option = 0;
		//int pSize = pubList.size();
		System.out.println("Please Choose a Publisher Id (or input 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!pubList.containsKey(option)) {
					System.out.println("Please input an existing Publisher ID:");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer.");
				}
			}
		}
		return option;
	}
	
	private static int deletePublisher(int pubId, HashMap<Integer, Publisher> pubList) throws SQLException {
		int option = 0 ;
		if(pubId == -1) {
			System.out.println("Quitting Publisher Delete.");
			return -1;
		}
		System.out.println("You have chosen to delete Publisher ID: "+pubId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter 'y' to confirm deleting Publisher:");
		String confirm;
		while(scan.hasNext()) {
			confirm = scan.next();
			scan.nextLine();
			if(confirm.equals("y")) {
				cn.deletePublisher(pubId);
				System.out.println("Publisher Deleted.");
				option = 1;
				break;
			}
			else if(confirm.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Publisher Delete.");
				return -1;
			}
			else {
				System.out.println("Please enter 'y' or 'quit'.");
				option = 0;
			}
		}
		return option;
	}
	
	private static int insertBook(int userInput) throws SQLException {
		System.out.println("You have chosen to insert a New Book");
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter a new Book Name:");
		int option = 0;
		String name = null;
		String pubInput = null;
		int pubId = 0;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Book Insert.");
				return -1;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Book Name less than 45 characters.");
			}
			else {
				System.out.println("Inserting a new Book Name.");
				option += 1;
				break;
			}
		}
		HashMap<Integer, Publisher> pubHash = listPublishers(userInput);
		System.out.println("Please enter the Book's Publisher ID (input '0'or 'N/A' if you don't know or want to):");
		while(scan.hasNext()) {
			//pubInput = scan.nextLine();
			//pubId = Integer.parseInt(pubInput);
			if(scan.hasNextInt()) {
				pubId = scan.nextInt();
				if(pubId == 0) {
					System.out.println("Chosen NOT to input Publisher ID.");
					break;
				}
				else {
					if(pubHash.containsKey(pubId)) {
						System.out.println("Inserting a new Publisher ID.");
						option += 1;
						break;
					}
					else {
						System.out.println("Please insert a new Existing Publisher ID.");
					}
				}
			}
			else {
				pubInput = scan.nextLine();
				if(pubInput.equals("quit")) {
					//scan.nextLine();
					pubId = 0;
					System.out.println("Quitting Book Insert.");
					return -1;
				}
				else if (pubInput.equals("N/A")){
					pubId = 0;
					System.out.println("Chosen NOT to input Publisher ID.");
					break;
				}
				else {
					System.out.println(pubInput + " Not a valid input. Please input Publisher ID, '0', or 'N/A'.");
				}
			}
		}
		if(name != null && pubId != 0) {
			System.out.println("Inserting Book: " + name + ", "+pubId);
		}
		else if(name != null) {
			System.out.println("Inserting Book: " + name + ", Publisher "+pubId+" as null");
		}
		else {
			System.out.println("Wrong Book Input: Name Is Null");
		}
		cn.insertBooks(name, pubId);
		return option;
	}
	
	private static HashMap<Integer, Books> listBooks(int userInput) throws SQLException {
		ArrayList<Books> bookArray = cn.readBooks();
		HashMap<Integer, Books> bookList = new HashMap<Integer, Books>();
		System.out.printf("%-10.30s  %-50.70s  %-5.30s%n", "Book ID", "Title", "Publisher ID");
		for(Books entry : bookArray) {
			int bkId = entry.getBookId();
			bookList.put(bkId, entry);
			System.out.printf("%-10.30s  %-50.70s  %-5.30s%n", bkId, entry.getTitle(), entry.getPubId());
		}
		return bookList;
	}
	
	private static int updateBook(int bookId, HashMap<Integer, Books> bookList) throws SQLException {
		if(bookId == -1) {
			System.out.println("Quitting Book Update.");
			return -1;
		}
		System.out.println("You have chosen to update a Book ID: "+bookId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter new Book Name or enter N/A for not changing name:");
		int option = 0;
		String name = null;
		String pubInput = null;
		int pubId = -1;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("N/A")) {
				System.out.println("Chosen NOT to change name.");
				name = null;
				break;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Book Name less than 45 characters.");
			}
			else if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Book Update.");
				return -1;
			}
			else {
				System.out.println("Changing Book Name.");
				option += 1;
				break;
			}
		}
		HashMap<Integer, Publisher> pubHash = listPublishers(bookId);
		System.out.println("Please enter new Book's Publisher ID. Input '0' to clear out publisher ID (make value null)");
		System.out.println("Or 'N/A' to NOT change Publisher ID:");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				pubId = scan.nextInt();
				if(pubId == 0) {
					System.out.println("Chosen clear out Publisher ID (make null).");
					break;
				}
				else {
					if(pubHash.containsKey(pubId)) {
						System.out.println("Inserting a new Publisher ID.");
						option += 1;
						break;
					}
					else {
						System.out.println("Please insert a new Existing Publisher ID.");
					}
				}
			}
			else {
				pubInput = scan.nextLine();
				if(pubInput.equals("quit")) {
					//scan.nextLine();
					pubId = 0;
					System.out.println("Quitting Book Insert.");
					return -1;
				}
				else if (pubInput.equals("N/A")){
					pubId = -1;
					System.out.println("Chosen NOT to change Publisher ID.");
					break;
				}
				else {
					System.out.println(pubInput + " Not a valid input. Please input Publisher ID, '0', or 'N/A'.");
				}
			}
		}
		System.out.println("Changing Book ID: "+bookId);
		if(name != null && pubId != -1) {
			bookList.get(bookId).setTitle(name);
			bookList.get(bookId).setPubId(pubId);
			System.out.println("Changing to: " + name + ", "+pubId);
		}
		else if(name != null) {
			bookList.get(bookId).setTitle(name);
			System.out.println("Inserting: "+ name + ", "+pubId+" as zero");
		}
		else if (name == null && pubId != -1){
			bookList.get(bookId).setPubId(pubId);
			System.out.println("Changing publisher Id: "+pubId);
		}
		else {
			System.out.println("Changing Neither Name Nor Pub ID.");
		}
		cn.updateBooks(bookId, name, pubId);
		System.out.println("Would you like to change the Author for this Book? (input 'y' or 'n'):");
		while(scan.hasNext()) {
			String quitter = scan.next();
			scan.nextLine();
			if(quitter.equals("y")) {
				System.out.println("Changing Book Author.");
				HashMap<Integer, Author> authorList = listAuthors(1);
				System.out.println("Please choose an Author ID to change to:");
				while(scan.hasNext()) {
					if(scan.hasNextInt()) {
						int authorId = scan.nextInt();
						scan.nextLine();
						if(!authorList.containsKey(authorId)) {
							System.out.println("Please choose an Existing Author ID from the list:");
						}
						else {
							System.out.println("Updating Book Id: "+bookId +" To match with Author Id: "+ authorId);
							cn.updateBookAuthor(bookId, authorId);
							break;
						}
					}
					else {
						String alph = scan.nextLine();
						System.out.println(alph + " is an invalid input. Please input an integer.");						
					}
				}
				break;
			}
			else if(quitter.equals("n")) {
				System.out.println("Not Changing Book Author.");
				break;
			}
			else {
				System.out.println("Please input 'y' or 'n':");
			}
		}
		return option;
	}
	
	private static int chooseBook(HashMap<Integer, Books> bookList) throws SQLException {
		int option = 0;
		//int bSize = bookList.size();
		System.out.println("Please Choose a Book Id (or input 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!bookList.containsKey(option)) {
					System.out.println("Please input an existing Book ID:");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer.");
				}
			}
		}
		return option;
	}
	
	private static int deleteBook(int bookId, HashMap<Integer, Books> bookList) throws SQLException {
		int option = 0 ;
		if(bookId == -1) {
			System.out.println("Quitting Book Delete.");
			return -1;
		}
		System.out.println("You have chosen to delete Book ID: "+bookId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter 'y' to confirm deleting Book:");
		String confirm;
		while(scan.hasNext()) {
			confirm = scan.next();
			scan.nextLine();
			if(confirm.equals("y")) {
				cn.deleteBooks(bookId);
				System.out.println("Book Deleted.");
				option = 1;
				break;
			}
			else if(confirm.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Book Delete.");
				return -1;
			}
			else {
				System.out.println("Please enter 'y' or 'quit'.");
				option = 0;
			}
		}
		return option;
	}

	private static int insertAuthor(int userInput) throws SQLException {
		System.out.println("You have chosen to insert a New Author");
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter a new Author Name:");
		int option = 0;
		String name = null;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Author Insert.");
				return -1;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert an Author Name less than 45 characters.");
			}
			else {
				System.out.println("Inserting a new Author Name.");
				option += 1;
				break;
			}
		}
		System.out.println("Inserting Author: " + name);
		cn.insertAuthor(name);
		return option;
	}
	
	private static HashMap<Integer, Author> listAuthors(int userInput) throws SQLException {
		ArrayList<Author> authorArray = cn.readAuthors();
		HashMap<Integer, Author> authorList = new HashMap<Integer, Author>();
		System.out.printf("%-10.30s  %-50.70s%n", "Author ID", "Name");
		for(Author entry : authorArray) {
			int authId = entry.getAuthId();
			authorList.put(authId, entry);
			System.out.printf("%-10.30s  %-50.70s%n", authId, entry.getAuthName());
		}
		return authorList;
	}
	
	private static int updateAuthor(int authId, HashMap<Integer, Author> authorList) throws SQLException {
		if(authId == -1) {
			System.out.println("Quitting Author Update.");
			return -1;
		}
		System.out.println("You have chosen to update a Author ID: "+authId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter new Author Name or enter N/A for not changing name:");
		int option = 0;
		String name = null;
//		String pubInput = null;
//		int pubId = -1;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("N/A")) {
				System.out.println("Chosen NOT to change name.");
				name = null;
				break;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert an Author Name less than 45 characters.");
			}
			else if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Author Update.");
				return -1;
			}
			else {
				System.out.println("Changing Author Name.");
				option += 1;
				break;
			}
		}
		System.out.println("Changing Author ID: "+authId);
		if(name != null) {
			authorList.get(authId).setAuthName(name);
			System.out.println("Changing to: " + name);
		}
		else {
			System.out.println("Name Is Null. Not Changing Author.");
		}
		cn.updateAuthor(authId, name);
		return option;
	}
	
	private static int chooseAuthor(HashMap<Integer, Author> authorList) throws SQLException {
		int option = 0;
		//int bSize = bookList.size();
		System.out.println("Please Choose an Author Id (or input 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!authorList.containsKey(option)) {
					System.out.println("Please input an existing Author ID:");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer.");
				}
			}
		}
		return option;
	}
	
	private static int deleteAuthor(int authId, HashMap<Integer, Author> authorList) throws SQLException {
		if(authId == -1) {
			System.out.println("Quitting Author Delete.");
			return -1;
		}
		int option = 0 ;
		System.out.println("You have chosen to delete Author ID: "+authId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter 'y' to confirm deleting Author:");
		String confirm;
		while(scan.hasNext()) {
			confirm = scan.next();
			scan.nextLine();
			if(confirm.equals("y")) {
				cn.deleteAuthor(authId);
				System.out.println("Author Deleted.");
				option = 1;
				break;
			}
			else if(confirm.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Author Delete.");
				return -1;
			}
			else {
				System.out.println("Please enter 'y' or 'quit'.");
				option = 0;
			}
		}
		return option;
	}

	private static int insertBranch(int userInput) throws SQLException {
		System.out.println("You have chosen to insert a New Library Branch");
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter a new Library Branch Name:");
		int option = 0;
		String name = null;
		String address = null;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Library Branch Insert.");
				return -1;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Branch Name less than 45 characters.");
			}
			else {
				System.out.println("Inserting a new Library Branch Name.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter the Library Branch's Address or 'N/A' if you don't wish to:");
		while(scan.hasNext()) {
			address = scan.nextLine();
			//scan.nextLine();
			if(address.equals("N/A")) {
				System.out.println("Chosen NOT to input Library Branch's Address.");
				break;
			}
			else if (address.length() > 45) {
				System.out.println("Please insert a Branch Address less than 45 characters.");
			}
			else if(address.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Library Branch Insert.");
				return -1;
			}
			else {
				System.out.println("Inserting a new Library Branch Address.");
				option += 1;
				break;
			}
		}
		if(address != null) {
			System.out.println("Inserting Branch: " + name + ", "+address);			
		}
		else {
			System.out.println("Inserting Branch: " + name + ", NoAddress");
		}
		cn.insertBranch(name, address);
		return option;
	}
	
	private static int deleteBranch(int branchId, HashMap<Integer, LibraryBranches> libraries) throws SQLException {
		if(branchId == -1) {
			System.out.println("Quitting Branch Delete.");
			return -1;
		}
		int option = 0 ;
		System.out.println("You have chosen to delete the Branch with: " + branchId + " and Branch Name: " + libraries.get(branchId).getBranchName());
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter 'y' to confirm deleting Branch:");
		String confirm;
		while(scan.hasNext()) {
			confirm = scan.next();
			scan.nextLine();
			if(confirm.equals("y")) {
				cn.deleteBranch(branchId);
				System.out.println("Branch Deleted.");
				option = 1;
				break;
			}
			else if(confirm.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Branch Delete.");
				return -1;
			}
			else {
				System.out.println("Please enter 'y' or 'quit'.");
				option = 0;
			}
		}
		return option;
	}
	
	private static int chooseBranch(HashMap<Integer, LibraryBranches> branchList) throws SQLException {
		int option = 0;
		//int bSize = bookList.size();
		System.out.println("Please Choose a Branch Id (or input 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!branchList.containsKey(option)) {
					System.out.println("Please input an existing Branch ID:");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer.");
				}
			}
		}
		return option;
	}
	
	private static HashMap<Integer, Borrower> listBorrowers(int userInput) throws SQLException {
		ArrayList<Borrower> borrowArray = cn.readBorrowers();
		HashMap<Integer, Borrower> borrowerList = new HashMap<Integer, Borrower>();
		System.out.printf("%-10.30s  %-26.80s  %-50.70s  %-5.30s%n", "BorrowerId", "Name", "Address", "Phone Number");
		for(Borrower entry : borrowArray) {
			int borrowId = entry.getUserId();
			borrowerList.put(borrowId, entry);
			System.out.printf("%-10.30s  %-26.80s  %-50.70s  %-5.30s%n", borrowId, entry.getName(), entry.getAddress(), entry.getPhone());
		}
		return borrowerList;
	}
	
	private static int updateBorrower(int borrowerId, HashMap<Integer, Borrower> borrowerList) throws SQLException {
		if(borrowerId == -1) {
			System.out.println("Quitting Borrower Update.");
			return -1;
		}
		System.out.println("You have chosen to update a Borrower ID: "+borrowerId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter new Borrower Name or enter N/A for not changing name:");
		int option = 0;
		String name = null;
		String address = null;
		String phone = null;
		String usrPass = null;
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("N/A")) {
				System.out.println("Chosen NOT to change name.");
				name = null;
				break;
			}
			else if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Borrower Update.");
				return -1;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Borrower Name less than 45 characters.");
			}
			else {
				System.out.println("Changing Borrower Name.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Borrower address or enter N/A for not changing address:");
		while(scan.hasNext()) {
			address = scan.nextLine();
			if(address.equals("N/A")) {
				System.out.println("Chosen NOT to change address.");
				address = null;
				break;
			}
			else if(address.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Borrower Update.");
				return -1;
			}
			else if (address.length() > 45) {
				System.out.println("Please insert a Borrower Address less than 45 characters.");
			}
			else {
				System.out.println("Changing Borrower Address.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Borrower phone or enter N/A for not changing phone:");
		while(scan.hasNext()) {
			phone = scan.nextLine();
			if(phone.equals("N/A")) {
				System.out.println("Chosen NOT to change phone.");
				phone = null;
				break;
			}
			else if(phone.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Borrower Update.");
				return -1;
			}
			else if (phone.length() > 45) {
				System.out.println("Please insert a Borrower Phone less than 45 characters.");
			}
			else {
				System.out.println("Changing a Borrower Phone.");
				option += 1;
				break;
			}
		}
		System.out.println("Changing Borrower ID: "+borrowerId);
		if(name != null) {
			borrowerList.get(borrowerId).setName(name);
			System.out.println("Changing Borrower Name to: "+name);
		}
		if(address != null) {
			borrowerList.get(borrowerId).setAddress(address);;
			System.out.println("Changing Borrower Address to: "+address);
		}
		if(phone != null) {
			borrowerList.get(borrowerId).setPhone(phone);;
			System.out.println("Changing Borrower Phone to: "+phone);
		}
		cn.updateBorrower(borrowerId, name, address, phone, usrPass); // impelment usrPass change if allow admin to change passwords
		return option;
	}
	
	private static int insertBorrower(int userInput) throws SQLException {
		System.out.println("You have chosen to insert a New Borrower");
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter a new Borrower Name:");
		int option = 0;
		String name = null;
		String address = null;
		String phone = null;
		String usrPass = "NoPass";
		while(scan.hasNext()) {
			name = scan.nextLine();
			if(name.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Borrower Insert.");
				return -1;
			}
			else if (name.length() > 45) {
				System.out.println("Please insert a Borrower Name less than 45 characters.");
			}
			else {
				System.out.println("Inserting a new Borrower Name.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Borrower address or enter N/A for no address:");
		while(scan.hasNext()) {
			address = scan.nextLine();
			if(address.equals("N/A")) {
				System.out.println("Chosen NOT to input address.");
				address = null;
				break;
			}
			else if(address.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Borrower Insert.");
				return -1;
			}
			else if (address.length() > 45) {
				System.out.println("Please insert a Borrower Address less than 45 characters.");
			}
			else {
				System.out.println("Inserting a new Borrower Address.");
				option += 1;
				break;
			}
		}
		System.out.println("Please enter new Borrower phone or enter N/A for no phone:");
		while(scan.hasNext()) {
			phone = scan.nextLine();
			if(phone.equals("N/A")) {
				System.out.println("Chosen NOT to input phone.");
				phone = null;
				break;
			}
			else if(phone.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Borrower Insert.");
				return -1;
			}
			else if (phone.length() > 45) {
				System.out.println("Please insert a Borrower Phone less than 45 characters.");
			}
			else {
				System.out.println("Inserting a new Borrower Phone.");
				option += 1;
				break;
			}
		}
		if(address != null && phone != null) {
			System.out.println("Inserting Borrower: " + name + ", "+address+", "+phone);
		}
		else if(phone != null) {
			System.out.println("Inserting Borrower: " + name + ", null,"+phone);
		}
		else if (address != null){
			System.out.println("Inserting Borrower: " + name + ", "+address+", null");
		}
		else {
			System.out.println("Inserting Borrower: " + name + ", null, null");
		}
		cn.insertBorrower(name, address, phone, usrPass); // implement usrPass input for future
		return option;
	}
	
	private static int chooseBorrower(HashMap<Integer, Borrower> borrowerList) throws SQLException {
		int option = 0;
		//int pSize = pubList.size();
		System.out.println("Please Choose a Borrower Id (or input 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				option = scan.nextInt();
				scan.nextLine();
				if(!borrowerList.containsKey(option)) {
					System.out.println("Please input an existing Borrower ID:");
				}
				else {
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					return -1;
				}
				else {
					System.out.println(alph + "is not valid. Please input an integer.");
				}
			}
		}
		return option;
	}
	
	private static int deleteBorrower(int borrowerId, HashMap<Integer, Borrower> borrowerList) throws SQLException {
		if(borrowerId == -1) {
			System.out.println("Quitting Borrower Delete.");
			return -1;
		}
		int option = 0 ;
		System.out.println("You have chosen to delete Borrower ID: "+borrowerId);
		System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter 'y' to confirm deleting Borrower:");
		String confirm;
		while(scan.hasNext()) {
			confirm = scan.next();
			scan.nextLine();
			if(confirm.equals("y")) {
				cn.deleteBorrower(borrowerId);
				System.out.println("Borrower Deleted.");
				option = 1;
				break;
			}
			else if(confirm.equals("quit")) {
				//scan.nextLine();
				System.out.println("Quitting Borrower Delete.");
				return -1;
			}
			else {
				System.out.println("Please enter 'y' or 'quit'.");
				option = 0;
			}
		}
		return option;
	}
	
	private static int updateBorrowerLoans(int borrowerId, HashMap<int[], String[]> loanList, int userId) throws SQLException, ParseException {
		if(borrowerId == -1) {
			System.out.println("Quitting Borrower Due Date Update.");
			return -1;
		}
		if(loanList.isEmpty()) {
			System.out.println("Quitting Since Borrower has no Books Out.");
			return -1;
		}
		int option = 0;
		int bookId = 0;
		int branchId = 0;
		String[] dates = new String[2];
		int[] bookBranch = new int[2];
		boolean contains = false;
		dates[0] = "emptyDate";
		dates[1] = "emptyDate";
		String dueDateOver = null;
		System.out.println("Updating Books Loans of Borrower id: "+borrowerId);
		System.out.println("Which Book ID would you like to change Due Date on? (input Book ID or 'quit' to cancel operation):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				bookId = scan.nextInt();
				scan.nextLine();
				bookBranch[0] = bookId;
				for(Entry<int[], String[]> entry : loanList.entrySet()) {
					if(entry.getKey()[0] == bookId) {
						contains = true;
						break;
					}
				}
				if(contains) {
					contains = false;
					break;
				}
				else {
					System.out.println("BookId: "+bookBranch[0]+" That Book Id does not exist from borrowed list.");
				}
				//scan.nextLine();
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					System.out.println("Quitting Borrower Due Date Update.");
					return -1;
				}
				System.out.println("Please input an integer:");
			}
		}
		System.out.println("Which library is it from? (input Library Branch ID):");
		while(scan.hasNext()) {
			if(scan.hasNextInt()) {
				branchId = scan.nextInt();
				bookBranch[1] = branchId;
				scan.nextLine();
				for(Entry<int[], String[]> entry : loanList.entrySet()) {
					if(entry.getKey()[0] == bookId) {
						if(entry.getKey()[1] == branchId) {
							contains = true;
							dates[0] = entry.getValue()[0];
							dates[1] = entry.getValue()[1];
							break;
						}
					}
				}
				if(!contains) {
					System.out.println("BookId: "+bookBranch[0] +", BranchId: "+bookBranch[1]+" That Book Id from Branch Id does not exist from borrowed list.");
					System.out.println("Which library is it from? (input Library Branch ID):");
					//break;
				}
				else {
					System.out.println("Checked-out Date: "+dates[0]);
					System.out.println("Due Date        : "+dates[1]);
					break;
				}
			}
			else {
				String alph = scan.nextLine();
				if(alph.contains("quit")) {
					System.out.println("Quitting Borrower Due Date Update.");
					return -1;
				}
				System.out.println("Please input an integer:");
			}
		}
		Pattern datePattern = Pattern.compile("[0-9]{4}[-][0-9]{2}[-][0-9]{2} [0-9]{2}[:][0-9]{2}[:][0-9]{2}");
		System.out.println("Input the new Due Date with the format (yyyy-MM-dd hh:mm:ss):");
		while(scan.hasNext()) {
			dueDateOver = scan.nextLine();
			dueDateOver = dueDateOver.replaceAll("\\\\n", "");
			if(!datePattern.matcher(dueDateOver).matches()) {
				System.out.println("Please input the dates EXACTLY with the following format (yyyy-MM-dd hh:mm:ss):");
			}
			else if(dueDateOver.equals("quit")) {
				System.out.println("Quitting Borrower Due Date Update.");
				return -1;
			}
			else{
				break;
			}
		}
		SimpleDateFormat inputFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		Date newDueDateFormatted = inputFormat.parse(dueDateOver);
		// call connectionHandler return book function
		Date dateNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String now = ft.format(dateNow);
		System.out.println("Date Now        : "+ now);
		System.out.println("Due Date Changed: "+ dueDateOver);
		String newDate = ft.format(newDueDateFormatted);
		cn.updateBookLoan(bookId, branchId, borrowerId, dates[0], newDate, null);
		
		return option;
	}
	
//	private class Pair {
//		private int first;
//		private String second;
//		/**
//		 * @param first
//		 * @param second
//		 */
//		protected Pair(int first, String second) {
//			super();
//			this.first = first;
//			this.second = second;
//		}
//		/**
//		 * @return the first
//		 */
//		protected int getFirst() {
//			return first;
//		}
//		/**
//		 * @param first the first to set
//		 */
//		protected void setFirst(int first) {
//			this.first = first;
//		}
//		/**
//		 * @return the second
//		 */
//		protected String getSecond() {
//			return second;
//		}
//		/**
//		 * @param second the second to set
//		 */
//		protected void setSecond(String second) {
//			this.second = second;
//		}
//		/* (non-Javadoc)
//		 * @see java.lang.Object#hashCode()
//		 */
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + getOuterType().hashCode();
//			result = prime * result + first;
//			result = prime * result + ((second == null) ? 0 : second.hashCode());
//			return result;
//		}
//		/* (non-Javadoc)
//		 * @see java.lang.Object#equals(java.lang.Object)
//		 */
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			Pair other = (Pair) obj;
//			if (!getOuterType().equals(other.getOuterType()))
//				return false;
//			if (first != other.first)
//				return false;
//			if (second == null) {
//				if (other.second != null)
//					return false;
//			} else if (!second.equals(other.second))
//				return false;
//			return true;
//		}
//		private Main getOuterType() {
//			return Main.this;
//		}
//	}
	
}
