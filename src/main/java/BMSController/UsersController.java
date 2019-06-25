package BMSController;

import javax.servlet.http.HttpServletRequest;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Beans.Users;    
import Beans.Authors;
import Beans.Books;
import Beans.Pair;
import DAO.Dao;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  


@Controller
//@RequestMapping("/Users")
public class UsersController {
	
	@Autowired    
    Dao dao;//will inject dao from XML file    
        
    /*It displays a form to input data, here "command" is a reserved request attribute  
     *which is used to display object data into form  
     */  
	
	
	@RequestMapping("/login")  
    public String login(@RequestParam("email") String email,@RequestParam("pass") String pass,Model m, RedirectAttributes r, HttpServletRequest req)  
    {  
        
        Users u;
        try{
        	u=dao.getUsersDetails(email);
        } catch(Exception e ){
        	String exp=e.getClass().getName()+": "+ e.getMessage();
        	String msg="User not found";
        	m.addAttribute("message", exp);
        	m.addAttribute("page", "/index.jsp");
        	return "errorpage";
        }
        
        if(pass.equals(u.getPassword()))  
        {  
            String msg="Hello "+ u.getName();  
            //add a message to the model 
            if (u.getIsAdmin()) {
				msg+=". You are an ADMIN.";
				m.addAttribute("user", u.getName());
				m.addAttribute("message", msg); 
				r.addFlashAttribute("user", u.getName());
				req.getSession().setAttribute("isAdmin", (boolean)true);
				return "redirect:/default";
			}
            else
            {
            	req.getSession().setAttribute("isAdmin", (boolean)false);
            	m.addAttribute("message", msg);  
                return "SearchPage";
            }
              
        }  
        else  
        {  
            String msg="Sorry "+ u.getName()+". You entered an incorrect password";  
            m.addAttribute("message", msg); 
            m.addAttribute("page", "index");
            return "errorpage";  
        }     
    }
	
	@RequestMapping("/redirectToRegister")
	public String redirectToRegister(Model m, HttpServletRequest req)
	{
		System.out.println(req.getSession().getAttribute("isAdmin"));
		m.addAttribute("msg", "hi this is a message");
		return "Register";
	}
	
	@RequestMapping("/registerUser")
	public String registerUser(@RequestParam("email") String email,@RequestParam("name") String name,@RequestParam("pass") String pass,Model m, HttpServletRequest req)
	{
		try{
        	Users u = new Users();
        	u.setEmail(email);
        	u.setName(name);
        	u.setPassword(pass);
        	
        	u.setIsAdmin(req.getSession().getAttribute("isAdmin").toString().equals("true"));
        	dao.saveUser(u);
        	
        	String msg="User Registered";
        	m.addAttribute("message", msg);
        	return "SucessAlert";
        } catch(Exception e ){
        	String exp=e.getClass().getName()+": "+ e.getMessage();
        	String msg="User already exist";
        	m.addAttribute("message", msg);
        	return "FailedAlert";
        }
	}
	
	@RequestMapping("results")
	public String getResults(@RequestParam("searchQuery") String query,Model m)
	{
		List<Pair> l = dao.searchQuery(query);
		m.addAttribute("list", l);
		m.addAttribute("page", "/search");
		return "ResultPage";
	}
	
	
	
}
