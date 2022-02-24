<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
	<title>Parking Lot</title>
	<style type="text/css">
		body
		{
			background-color: beige;
		}
		#navbar
		{
			display: grid;
  			align-items: center;
  			justify-content: center;
  			grid-template-columns: 20% 80%;
  		}
  		#nav2
  		{
  			display: flex;
  			margin: 0px 30%;
  		}
		.navContent
		{
			padding:5px;
		}
		#findMyVehicleContents
		{
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: flex-start;
		}

		.buttonstyle
		{
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
		.buttonstyle1
		{
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

		.findMyVehicleTable table,tr,td
		{
			padding: 10px;
		}
		.findMyVehicleTable td
		{
			width: 100%;
		}
		.checkboximg
		{
			width: 40px;
			height: 40px;
			padding-right: 30px;
		}
		#submitbutton
		{
			margin: 20px 40%;
		}

		#parkinghistory table,tr,td,th
		{
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
	<div id="findMyVehicleContents">
		<div id="findMyVehicleTitle">
			<h1>FIND MY VEHICLE</h1>
		</div>
		<div id="findMyVehicleForm">
			<form method="POST" action="findMyVehicleMethod">
				<table class="findMyVehicleTable">
					<tr>
						<td><label for="vnumber">Vehicle Number</label></td>
						<td><input type="text" id="vnumber" name="vnumber" required=""></td>
					</tr>
				</table>
				<input type="submit" id="submitbutton" class="buttonstyle" value="Find">	
			</form>		
		</div>
	</div>
	<c:if test = "${param.message.length() > 0}">
         <p id="message">Message: ${param.message}</p>
      </c:if>	
	<div id="parkinghistory">
		<table class="checkOutTable">
					<tr>
						<th>FLOOR</th>
						<th>DATE</th>
						<th>CHECK IN TIME</th>
						<th>CHECK OUT TIME</th>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
	</div>
	<div id="vehicleavailability">
		<p></p>		
	</div>
</body>
</html>