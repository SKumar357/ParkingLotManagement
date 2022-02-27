<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.Date"%>
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

#checkInContents {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: flex-start;
}

.buttonstyle {
	margin: 2% 47%;
	background-color: black;
	border: none;
	border-radius: 50px;
	color: white;
	padding: 11px 20px;
	text-decoration: none;
}

.buttonstyle:hover {
	cursor: pointer;
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

.checkInTable table, tr, td {
	padding: 10px;
}

.checkInTable td {
	width: 100%;
}

.checkboximg {
	width: 40px;
	height: 40px;
	padding-right: 30px;
}

#submitbutton {
	margin: 20px 40%;
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
	<div id="checkInContents">
		<div id="checkInTitle">
			<h1>CHECK IN</h1>
		</div>
		<div id="checkInForm">
			<form method="POST" action="checkInSubmit">
				<table class="checkInTable">
					<tr>
						<td><label for="vnumber">Vehicle Number</label></td>
						<td><input type="text" id="vnumber" name="vnumber"
							autocomplete="off" required placeholder="Enter Vehicle number"></td>
					</tr>
					<tr>
						<td><label for="vtype">Vehicle Type</label></td>
						<td><input type="radio" id="html" name="vtype" value="CAR"
							autocomplete="off" required> <label for="car"><img
								class="checkboximg" src="car.png"></label> <input type="radio"
							id="css" name="vtype" value="BIKE" autocomplete="off" required>
							<label for="bike"><img class="checkboximg" src="bike.png"></label>
						</td>
					</tr>
					<tr>
						<td><label for="lot">Enter lot</label></td>
						<td><input type="text" id="lot" name="lotname"
							autocomplete="off" required placeholder="Enter lot (A-E)"></td>
					</tr>
					<tr>
						<td><label for="checkIn">Check in Time</label></td>
						<td><input type="datetime-local" id="checkIn"
							name="entrytime" autocomplete="off" required></td>
					</tr>
				</table>
				<input type="submit" id="submitbutton" class="buttonstyle"
					value="CHECK IN">
			</form>
		</div>
		<c:if test="${message.length() > 0 and message1.length()==3}">
			<p id="message" style="color: green">${message}</p>
		</c:if>
		<c:if test="${message.length() > 0 and message1.length()==2}">
			<p id="message" style="color: red">${message}</p>
		</c:if>
	</div>
</body>
<script>
	var today = new Date().toISOString().slice(0, 11);
	document.getElementById("checkIn").min = today + '00:00';
</script>
</html>