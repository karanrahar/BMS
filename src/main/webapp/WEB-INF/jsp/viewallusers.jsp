<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
  
  
<html>
<head>
	<title>Success Deleted</title>
      <script type="text/javascript">
      function func()
      {
			window.alert("Sucessfully Deleted");

      }
      </script>

</head>

<body>
<h1>Users List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Email</th><th>Name</th><th>IsAdmin</th><th>Password</th></tr>  
   <c:forEach var="usr" items="${l}">   
   <tr>  
   <td>${usr.getEmail()}</td>  
   <td>${usr.getName()}</td>  
   <td>${usr.getIsAdmin()}</td>
   <td>${usr.getPassword()}</td>
   <td> <a href = "deleteUser/${usr.getEmail() }" onclick = "func()">Delete</a> </td>
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
</body>
</html>