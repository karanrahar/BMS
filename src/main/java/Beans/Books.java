package Beans;

public class Books {
	String BookName;
	int BookID,Price,Year,AuthorID;
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		for (int i = 0; i < bookName.length(); i++) {
			if (bookName.charAt(i)=='\'') {
				bookName=bookName.substring(0,i)+bookName.substring(i+1);
				i--;
			}
		}
		BookName = bookName;
	}
	public int getBookID() {
		return BookID;
	}
	public void setBookID(int bookID) {
		BookID = bookID;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public int getAuthorID() {
		return AuthorID;
	}
	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}
	
}
