<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>  
<body>    
 <jsp:include page="${page}"></jsp:include>

<table border = "2" width = "70%" cellpadding = "2">
	<tr><th>Book Name</th><th>Price</th><th>Year</th><th>Author Name</th></tr>
	<c:forEach var = "Pair" items = "${list}">
	<tr>
		<td>${Pair.getB().getBookName() }</td>
		<td>${Pair.getB().getPrice() }</td>
		<td>${Pair.getB().getYear() }</td>
		<td>${Pair.getA().getAuthorName() }</td>
	</tr>
	</c:forEach>
</table>
</body>  
</html> 