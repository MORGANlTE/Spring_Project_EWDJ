<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/resources/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css" /> 
<title>Wedstrijd tickets</title>
</head>
<body>
	<h1>FIFA World Cup Qatar 2022</h1>
	<h2>Stadion: ${stadium}</h2>
	<h3>${ticket.getWedstrijd().getLanden()[0]} vs ${ticket.getWedstrijd().getLanden()[1]} op ${ticket.getWedstrijd().getDag()}-11</h3>
	<h3>aantal tickets beschikbaar: ${ticket.getTickets()}</h3>
	<form:form method="POST" action="/fifa/${ticket.getWedstrijd().getId()}" modelAttribute="bestelling">
	<table>
		<tr>
			<td>email:</td>
			<td>
				<form:input path="email" size="20" />
			</td>
			<td>
				<form:errors path="email" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<td>aantal tickets:</td>
			<td>
				<form:input path="ticketAantal" size="20" />
			</td>
			<td>
				<form:errors path="ticketAantal" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<td>voetbalCode1:</td>
			<td>			
				<form:input path="voetbalCode1" size="20" />
			</td>
			<td>
				<form:errors path="voetbalCode1" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<td>voetbalCode2:</td>
			<td>			
				<form:input path="voetbalCode2" size="20" />

			</td>
			<td>
				<form:errors path="voetbalCode2" cssClass="error"/>
			</td>
		</tr>
	</table>
	<input type="submit" value="Koop"/>
	</form:form>
	
</body>
</html>