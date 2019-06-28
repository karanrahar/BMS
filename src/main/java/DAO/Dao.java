package DAO;

import org.springframework.jdbc.core.JdbcTemplate;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import Beans.Authors;
import Beans.Books;
import Beans.Pair;
import Beans.Users;

public class Dao {
	JdbcTemplate template;

	// PAIR MAPPER
	class PairMapper implements RowMapper<Pair> {
		public Pair mapRow(ResultSet rs, int arg) throws SQLException {
			Books b = new Books();
			Authors a = new Authors();
			Pair p = new Pair();

			b.setBookName(rs.getString(1));
			b.setPrice(rs.getInt(2));
			b.setYear(rs.getInt(3));

			a.setAuthorName(rs.getString(4));

			p.setA(a);
			p.setB(b);

			System.out.println(rs.getString(1) + " " + rs.getString(4));
			return p;
		}
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// FOR USERS
	public int saveUser(Users p) {
		String sql = "insert into BMSUsers(email,name,isAdmin,password) values('" + p.getEmail() + "','" + p.getName()
				+ "','" + p.getIsAdmin() + "','" + p.getPassword() + "')";
		return template.update(sql);
	}

	public int changeUserPassword(Users p) {
		String sql = "update BMSUsers set password='" + p.getPassword() + "' where email=" + p.getEmail() + "";
		return template.update(sql);
	}

	public int deleteUser(String email) {
		String sql = "delete from BMSUsers where email='" + email + "'";
		return template.update(sql);
	}

	public Users getUsersDetails(String email) {
		String sql = "select * from BMSUsers where email = ?";
		return template.queryForObject(sql, new Object[] { email }, new BeanPropertyRowMapper<Users>(Users.class));
	}

	public List<Users> getAllUsers() {
		return template.query("select * from BMSUsers where Email <> 'root' and Email IS NOT NULL ",
				new RowMapper<Users>() {
					public Users mapRow(ResultSet rs, int row) throws SQLException {
						Users u = new Users();
						u.setEmail(rs.getString(1));
						u.setName(rs.getString(2));
						u.setIsAdmin(rs.getBoolean(3));
						u.setPassword(rs.getString(4));
						return u;
					}
				});
	}

	// FOR BOOKOS

	public int saveBook(Books p) {
		String sql = "insert into BMSBooks(BookName, Price, Year, AuthorID) values('" + p.getBookName() + "','"
				+ p.getPrice() + "','" + p.getYear() + "','" + p.getAuthorID() + "')";
		return template.update(sql);
	}

	public int changeBookName(Books p) {
		String sql = "update BMSBooks set BookName ='" + p.getBookName() + "' where BookID =" + p.getBookID() + "";
		return template.update(sql);
	}

	public int changeBookPrice(Books p) {
		String sql = "update BMSBooks set Price ='" + p.getPrice() + "' where BookID =" + p.getBookID() + "";
		return template.update(sql);
	}

	public int changeBookYear(Books p) {
		String sql = "update BMSBooks set Year ='" + p.getYear() + "' where BookID =" + p.getBookID() + "";
		return template.update(sql);
	}

	public int changeBookAuthor(Books p) {
		String sql = "update BMSBooks set AuthorID ='" + p.getAuthorID() + "' where BookID =" + p.getBookID() + "";
		return template.update(sql);
	}

	public int deleteBook(int id) {
		String sql = "delete from BMSBooks where BookID =" + id + "";
		return template.update(sql);
	}

	public Books getBookDetails(String name) {
		String sql = "select * from BMSBooks where BookName=?";
		return template.queryForObject(sql, new Object[] { name }, new BeanPropertyRowMapper<Books>(Books.class));
	}

	public List<Books> getAllBooks() {
		return template.query("select * from BMSBooks", new RowMapper<Books>() {
			public Books mapRow(ResultSet rs, int row) throws SQLException {
				Books b = new Books();
				b.setBookName(rs.getString(1));
				b.setBookID(rs.getInt(2));
				b.setPrice(rs.getInt(3));
				b.setYear(rs.getInt(4));
				b.setAuthorID(rs.getInt(5));
				return b;
			}
		});
	}

	// FOR AUTHORS

	public int saveAuthor(Authors p) {
		String sql = "insert into BMSAuthors(AuthorName) values('" + p.getAuthorName() + "')";
		return template.update(sql);
	}

	public int changeAuthorName(Authors p) {
		String sql = "update BMSAuthors set AuthorName='" + p.getAuthorName() + "' where AuthorID =" + p.getAuthorID()
				+ "";
		return template.update(sql);
	}

	public int deleteAuthor(int id) {
		String sql = "delete from BMSAuthors where AuthorID=" + id + "";
		return template.update(sql);
	}

	public Authors getAuthorDetails(String name) throws Exception {
		String sql = "select * from BMSAuthors where AuthorName=?";
		return template.queryForObject(sql, new Object[] { name }, new BeanPropertyRowMapper<Authors>(Authors.class));
	}

	public List<Authors> getAllAuthors() {
		return template.query("select * from BMSAuthors", new RowMapper<Authors>() {
			public Authors mapRow(ResultSet rs, int row) throws SQLException {
				Authors a = new Authors();
				a.setAuthorID(rs.getInt(1));
				a.setAuthorName(rs.getString(2));
				return a;
			}
		});
	}

	// SEARCH QUERY
	public List<Pair> searchQuery(String name) {
		String sql = "SELECT BookName,Price,Year,AuthorName FROM BMSBooks INNER JOIN BMSAuthors "
				+ "ON BMSBooks.AuthorID = BMSAuthors.AuthorID " + "WHERE BookName LIKE '%" + name + "%' "
				+ "OR AuthorName LIKE '%" + name + "%' ; ";
		return template.query(sql, new PairMapper());
	}

	// CSV Reader
	public ArrayList<String[]> readAll(MultipartFile file) throws Exception {
		// CSVReader csvReader = new CSVReader(reader);
		// List<String[]> list = new ArrayList();
		// list = csvReader.readAll();
		// reader.close();
		// csvReader.close();
		// return list;
		BufferedReader br;
		ArrayList<String[]> result = new ArrayList<String[]>();
		try {

			String line;
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				result.add(line.split(","));
				// System.out.println(line.split(",")[1]);
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	public String addcsv(File file, int bname, int byear, int bprice, int aname)
			throws FileNotFoundException, IOException {

		try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
			String[] values = null;
			try {
				csvReader.readNext();
				int num = 0;
				while ((values = csvReader.readNext()) != null) {
					Books b = new Books();
					b.setBookName(values[bname].isEmpty() ? values[bname + 1] : values[bname].split(",")[0]);
					b.setYear(values[byear].isEmpty() ? 0 : (int) Double.parseDouble(values[byear]));
					b.setPrice(Integer.parseInt(values[bprice]));
					String Authorname = values[aname].split(",")[0];
					for (int i = 0; i < Authorname.length(); i++) {
						if (Authorname.charAt(i) == '\'') {
							Authorname = Authorname.substring(0, i) + Authorname.substring(i + 1);
							i--;
						}
					}
					Authors a = new Authors();
					try {

						a = getAuthorDetails(Authorname);
						// System.out.println(a.getAuthorID());
					} catch (Exception e) {
						System.out.println("Author not found. Adding Author " + Authorname);
						a.setAuthorName(Authorname);
						saveAuthor(a);
					}
					b.setAuthorID(getAuthorDetails(Authorname).getAuthorID());
					saveBook(b);
					num++;
					System.out.println("Book " + b.getBookName() + " Added");
				}
				System.out.println(num + " Books added");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "true";
	}

	public String readcsv(File file) throws FileNotFoundException, IOException {
		List<List<String>> records = new ArrayList<List<String>>();
		try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
			String[] values = null;
			try {
				while ((values = csvReader.readNext()) != null) {
					records.add(Arrays.asList(values));
					System.out.println(records.get(records.size() - 1).get(1).split(",")[0]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "true";
	}

	public ArrayList<String> getFeilds(File file) throws FileNotFoundException, IOException {
		List<List<String>> records = new ArrayList<List<String>>();
		ArrayList<String> result = new ArrayList<String>();
		try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
			String[] value = null;

			value = csvReader.readNext();
			result.addAll(Arrays.asList(value));
			// while ((values = csvReader.readNext()) != null) {
			// records.add(Arrays.asList(values));
			// System.out.println(records.get(records.size() -
			// 1).get(1).split(",")[0]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
