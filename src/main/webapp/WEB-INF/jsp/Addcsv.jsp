<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select Fields</title>
</head>
<body>

<form>
Book Name : <select name="BookName" ><option value="-" label = "--Select BookName--"></option>
<c:forEach items="${l}" var="fields">
            <option value="${l.indexOf(fields)}" label ="${fields}"></option>
        </c:forEach>
        </select><br /><br />
Book Year : <select name="Year" ><option value="0" label = "--Select Year Field--"></option>
<c:forEach items="${l}" var="fields">
            <option value="${l.indexOf(fields)}" label ="${fields}"></option>
        </c:forEach>
        </select><br /><br />
Book Cost : <select name="BookCost" ><option value="0" label = "--Select Price Field--"></option>
<c:forEach items="${l}" var="fields">
            <option value="${l.indexOf(fields)}" label ="${fields}"></option>
        </c:forEach>
        </select><br /><br />

Author Name : <select name="Aname" ><option value="-" label = "--Select Author Field--"></option>
		<c:forEach items="${l}" var="fields">
            <option value="${l.indexOf(fields)}" label ="${fields}"></option>
        </c:forEach>
        </select><br />
        
        <input type = "submit" value ="Add CSV" formaction = "addCSV">
</form>
</body>
</html>