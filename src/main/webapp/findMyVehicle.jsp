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

#findMyVehicleContents {
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

.findMyVehicleTable table, tr, td {
	padding: 10px;
}

#bordernone {
	border: none;
}

.checkboximg {
	width: 40px;
	height: 40px;
	padding-right: 30px;
}

#submitbutton {
	margin: 20px 40%;
}

#parkinghistory {
	width: 10px 20%;
}

#parkinghistory table, tr, td, th {
	border: 1px solid black;
	border-collapse: collapse;
}

#fmvDetailsWrap {
	margin: 0px 10%;
	display: grid;
	grid-template-areas: "PT IT" "PTable ITable";
	grid-template-columns: 60% 35%;
	align-items: flex-start;
	justify-content: flex-start;
}

#parkinghistorytitle {
	grid-area: PT;
}

.checkOutTable {
	grid-area: PTable;
	width: 90%;
}

#inParking {
	margin: 0px 10px;
}

#inParkingTitle {
	grid-area: IT;
}

#inParkingDetails {
	grid-area: ITable;
	background-color: white;
	padding: 10px;
	width: 100%;
	height: 200px;
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
	<div id="findMyVehicleContents">
		<div id="findMyVehicleTitle">
			<h1>FIND MY VEHICLE</h1>
		</div>
		<div id="findMyVehicleForm">
			<form method="POST" action="findMyVehicleMethod">
				<table class="findMyVehicleTable">
					<tr>
						<td id="bordernone"><label for="vnumber">Vehicle
								Number</label></td>
						<td id="bordernone"><input type="text" id="vnumber"
							name="vnumber" autocomplete="off" required placeholder="Enter Vehicle number"></td>
					</tr>
				</table>
				<input type="submit" id="submitbutton" class="buttonstyle"
					value="Find">
			</form>
		</div>
	</div>
	<c:if test="${message.length() > 0 and message1.length()==3}">
		<div id="fmvDetailsWrap">
			<div id="parkinghistory">
				<c:if test="${arrmsg > 0}">
					<h3 id="parkinghistorytitle">PARKING HISTORY</h3>
					<table class="checkOutTable">
						<tr>
							<th>FLOOR</th>
							<th>DATE</th>
							<th>CHECK IN TIME</th>
							<th>CHECK OUT TIME</th>
						</tr>
						<%
						request.getAttribute("parkingHistory");
						%>
						<c:forEach var="value" items="${parkingHistory}">
							<tr>
								<td>${value[1]}</td>
								<td>${value[2]}</td>
								<td>${value[3]}</td>
								<td>${value[4]}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${arrmsg == 0}">
					<h3 id="parkinghistorytitle">PARKING HISTORY</h3>
					<p id="parkinghistorytitle" style="color: red">*No parking
						history available</p>
				</c:if>
			</div>
			<div id="inParking">
				<h3 id="inParkingTitle">IN PARKING</h3>

				<p id="inParkingDetails" style="color: green">${message}</p>
			</div>
		</div>
	</c:if>
	<c:if test="${message.length() > 0 and message1.length()==2}">
		<div id="fmvDetailsWrap">
			<div id="parkinghistory">
				<c:if test="${arrmsg > 0}">
					<h3 id="parkinghistorytitle">PARKING HISTORY</h3>
					<table class="checkOutTable">
						<tr>
							<th>FLOOR</th>
							<th>DATE</th>
							<th>CHECK IN TIME</th>
							<th>CHECK OUT TIME</th>
						</tr>
						<%
						request.getAttribute("parkingHistory");
						%>
						<c:forEach var="value" items="${parkingHistory}">
							<tr>
								<td>${value[1]}</td>
								<td>${value[2]}</td>
								<td>${value[3]}</td>
								<td>${value[4]}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${arrmsg == 0}">
					<h3 id="parkinghistorytitle">PARKING HISTORY</h3>
					<p id="parkinghistorytitle" style="color: red">*No parking
						history available</p>
				</c:if>
			</div>
			<div id="inParking">
				<h3 id="inParkingTitle">IN PARKING</h3>
				<p id="inParkingDetails" style="color: red">${message}</p>
			</div>
		</div>
	</c:if>
</body>
</html>