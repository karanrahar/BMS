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
<h1>Author List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>AuthorID</th><th>Author Name</th></tr>  
   <c:forEach var="author" items="${l}">   
   <tr>  
   <td>${author.getAuthorID()}</td>
   <td>${author.getAuthorName()}</td>
   <td> <a href = "deleteAuthor/${author.getAuthorID() }" onclick = "func()">Delete</a> </td>
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
</body>
</html>