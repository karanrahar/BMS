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
<h1>Book List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>BookID</th><th>Book Name</th><th>Year</th><th>Price</th><th>AuthorID</th></tr>  
   <c:forEach var="book" items="${l}">   
   <tr>  
   <td>${book.getBookID()}</td>
   <td>${book.getBookName()}</td>
   <td>${book.getYear()}</td>
   <td>${book.getPrice()}</td>
   <td>${book.getAuthorID()}</td>
   <td> <a href = "deleteBook/${book.getBookID() }" onclick = "func()">Delete</a> </td>
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
</body>
</html>