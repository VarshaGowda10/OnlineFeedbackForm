<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to Home Page</h1>
<form action="openfeedback" method=post>
</form>
<c:if test="${userlist!=null}">
<table>
<thead>
<tr>
<th>id</th>
<th>Name</th>
<th>Email</th>
<th>Comments</th>
</tr>
</thead>
<tbody>
<c:forEach items = "${userlist}" var = "u">

<tr>
<td>${u.getId()}</td>
<td>${u.getUname()}</td>
<td>${u.getEmail()}</td>
<td>${u.getComments()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
</body>
</html>