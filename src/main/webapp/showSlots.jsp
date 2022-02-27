<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Parking Lot</title>
<style type="text/css">
body {
	background-color: #F6F2F2;
}

#navbar {
	display: grid;
	align-items: center;
	justify-content: center;
	grid-template-columns: 20% 80%;
}

#nav2 {
	display: flex;
	margin: 0px 30%;
}

.navContent {
	padding: 5px;
}

.buttonstyle1 {
	background-color: black;
	border: none;
	border-radius: 7px;
	color: white;
	padding: 11px 20px;
	text-decoration: none;
}

.buttonstyle1:hover {
	cursor: pointer;
}

#showDetailsContent {
	display: flex;
	flex-direction: column;
	align-items: center;
}

#lotdetails table, tr, td, th {
	padding: 10px;
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<nav id="navbar">
		<div id="nav1">
			<a href="WelcomePage"><button class="buttonstyle1" type="button">Home</button></a>
		</div>
		<div id="nav2">
			<img class="navContent" src="parkinglot.png">
			<h1 class="navContent">Parking Lot</h1>
		</div>
	</nav>
	<div id="showDetailsContent">
		<div id="showSLotsTitle">
			<h1>SHOW SLOTS</h1>
		</div>
		<div id="showSLotsTitle1">
			<h3>Total no of floors:4</h3>
		</div>
		<div id="lotdetails">
			<table class="lotDetailsTable">
				<tr>
					<th>FLOOR</th>
					<th>CAPACITY</th>
					<th>NO OF VEHICLES</th>
					<th>AVAILABILITY</th>
				</tr>
				<%
				request.getAttribute("arr");
				%>
				<c:forEach var="value" items="${arr}">
					<tr>
						<td>${value[0]}</td>
						<td>${value[1]}</td>
						<td>${value[2]}</td>
						<td>${value[3]}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>