package BMSController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Beans.Users;    
import Beans.Authors;
import Beans.Books;
import DAO.Dao;

import com.opencsv.CSVReader;


import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  


@Controller
//@RequestMapping("/Admin")
public class AdminController {
	@Autowired    
    Dao dao;//will inject dao from XML file    
        
    /*It displays a form to input data, here "command" is a reserved request attribute  
     *which is used to display object data into form  
     */  
	@RequestMapping("/default")
	public String deafult(Model m, @ModelAttribute("user") String user) {
		m.addAttribute("user", user);
		return "AdminPage";
	}
	@RequestMapping("/search")
	public String userSearch()
	{
		return "SearchPage";
	}
	
	@RequestMapping("/vau")
	public String vau(Model m)
	{
		List<Users> l=dao.getAllUsers();    
        m.addAttribute("l",l);  
        return "viewallusers"; 
	}
	
	@RequestMapping("/vab")
	public String vab(Model m)
	{
		List<Books> l=dao.getAllBooks();    
        m.addAttribute("l",l);  
        return "viewallbooks"; 
	}
	
	@RequestMapping("/vaa")
	public String vaa(Model m)
	{
		List<Authors> l=dao.getAllAuthors();    
        m.addAttribute("l",l);  
        return "viewallauthors"; 
	}
	
	@RequestMapping("/redirectToAddAuthor")
	public String redirectAddAuthor()
	{
		return "AddAuthor";
	}
	
	@RequestMapping("/addAuthor")
	public String addAuthor(@RequestParam("authorname")String name,Model m)
	{
		Authors a=new Authors();
		a.setAuthorName(name);
		try{
			dao.saveAuthor(a);
		} catch(Exception e){
			String exp=e.getClass().getName()+": "+ e.getMessage();
        	String msg="Author Already Exists";
        	m.addAttribute("message", msg);
        	m.addAttribute("page", "/redirectToAddAuthor");
        	return "errorpage";
		}
        return "SucessAlert"; 
	}
	
	@RequestMapping("/redirectToAddBook")
	public String redirectAddBooks(Model m)
	{
		List<Authors> l=dao.getAllAuthors();
		m.addAttribute("l", l);
		return "AddBook";
	}
	
	@RequestMapping("addBook")
	public String addBook(@RequestParam("BookName") String name, @RequestParam("BookPrice") int price, @RequestParam("BookYear") int year, @RequestParam("Aname") int authorid, Model m)
	{
		Books b = new Books();
		b.setBookName(name);
		b.setPrice(price);
		b.setYear(year);
		b.setAuthorID(authorid);
		try{
			dao.saveBook(b);
			return "SucessAlert";
		} catch(Exception e){
			String exp=e.getClass().getName()+": "+ e.getMessage();
        	String msg="Book Already Exists";
        	m.addAttribute("message", msg);
        	m.addAttribute("page", "/redirectToAddBook");
        	return "errorpage";
		}
	}
	
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id, Model m)
	{
		dao.deleteBook(id);
		
		m.addAttribute("message", "");
		m.addAttribute("page", "/vab");
		return "errorpage";
	}
	
	
	@RequestMapping("/deleteAuthor/{id}")
	public String deleteAuthor(@PathVariable int id, Model m)
	{
		dao.deleteAuthor(id);
		
//		m.addAttribute("message", "");
		m.addAttribute("page", "/vaa");
		return "errorpage";
	}
	
	@RequestMapping("/deleteUser/{email}")
	public String deleteUser(@PathVariable String email, Model m)
	{
		dao.deleteUser(email);
		
//		m.addAttribute("message", "");
		m.addAttribute("page", "/vau");
		return "errorpage";
	}
	
	@RequestMapping("/redirectToAddcsv")
	public String redirectToAddcsv(Model m)
	{
		return "Addcsv";
	}
//	public String oneByOneExample() throws Exception {
//	    Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("csv/twoColumn.csv").toURI()));
//	    return dao.oneByOne(reader).toString();
//	}
	
	@RequestMapping(value = "/Addcsv", method = RequestMethod.POST)
	public String readAllExample(@RequestParam("file") MultipartFile file, Model m,HttpSession session) throws Exception {
//		String path=session.getServletContext().getRealPath("/"); 
//		System.out.println(path + file.getOriginalFilename());
//	    Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("books.csv").toURI()));
//	    System.out.println(dao.readAll(reader).toString());
		ArrayList<String[]> res = dao.readAll(file);
		for(int i=0; i<res.size(); i++){
			String [] str=  res.get(i);
//			for(String s: str){
//				System.out.print(s+" ");
//			}
			
			System.out.println(str[1]+" "+ str[7]+" "+ str[9]);
		}
	    return "SucessAlert";
	}
}
