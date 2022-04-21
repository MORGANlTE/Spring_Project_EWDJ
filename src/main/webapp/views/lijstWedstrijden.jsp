<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss"/>
<title>Wedstrijden ${stadium}</title>
</head>
<body>
	<h1>FIFA World Cup Qatar 2022</h1>
	<h2>Stadion: ${stadium}</h2>
	<table>
		<thead>
			<tr>
				<td>Nr</td>
				<td>Voetbalmatch</td>
				<td>Datum</td>
				<td>Aftrap</td>
				<td>Tickets</td>
			</tr>
		</thead>
		<tbody>
		<% int teller = 1; %>
			<c:forEach var="ticket" items="${tickets}">
			<tr>
				<td><a href=""><%= teller%></a></td>
				<td>${ticket.getWedstrijd().getLanden()[0]} - ${ticket.getWedstrijd().getLanden()[1]}</td>
				<td>${ticket.getWedstrijd().getDag()} November</td>
				<td>${ticket.getWedstrijd().getUur()}</td>
				<td>${ticket.getTickets()}</td>
			</tr>
			<%teller = teller+1; %>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>