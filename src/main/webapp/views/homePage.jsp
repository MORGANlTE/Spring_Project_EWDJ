<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FIFA World Cup Qatar 2022</title>
</head>
<body>
	<h1>FIFA World Cup Qatar 2022</h1>
	<form:form method="POST" action="fifa" modelAttribute="stadium">
		stadiums 
		<select name="stadium" path="fifa">
		<c:forEach var="aStadium" items="${stadiums}">
			<option value="${aStadium}">${aStadium}</option>
		</c:forEach>
		</select>
		<br/>
		<br/>
		<input type="submit" value="Voer uit"/>
	</form:form>
</body>
</html>