package Beans;

public class Authors {
	int AuthorID;
	String AuthorName;
	public int getAuthorID() {
		return AuthorID;
	}
	public void setAuthorID(int authorID) {
		AuthorID = authorID;
	}
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		for (int i = 0; i < authorName.length(); i++) {
			if (authorName.charAt(i) == '\'') {
				authorName = authorName.substring(0, i) + authorName.substring(i + 1);
				i--;
			}
		}
		AuthorName = authorName;
	}
	
}
