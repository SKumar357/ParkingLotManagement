<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Parking Lot</title>
	<style type="text/css">
		body
		{
			background-color: #F6F2F2;
		}
		#navbar
		{
			display: flex;
  			align-items: center;
  			justify-content: center;
		}
		.navContent
		{
			padding:5px;
		}
		#Option
		{
			display: grid;
			justify-content: center;
			align-items: center;
		}
		.optionbuttons
		{
			padding:20px 0px;
			margin: auto;
			width: 80%;
		}
		#Option
		{
			margin: 100px 10px;
		}
		.buttonstyle1
		{
			background-color: black;
			border: none;
			border-radius: 7px;
			color: white;
			padding: 10px 10px;
			text-decoration: none;
		}
		.buttonstyle1:hover {
			cursor: pointer;
		}

		#showslots
		{
			display: flex;
			justify-content: flex-end;
		}
	</style>
</head>
<body>
	<nav id="navbar">
		<img class="navContent" src="parkinglot.png">
		<h1 class="navContent">Parking Lot</h1>
	</nav>
	<div id="Option">
		<div class="optionbuttons" id="checkIn">
			<a href="checkIn"><button class="buttonstyle1" type="button">CHECK IN</button></a>
		</div>
		<div class="optionbuttons" id="checkOut">
			<a href="checkOut"><button class="buttonstyle1" type="button">CHECK OUT</button></a>
		</div>
		<div class="optionbuttons" id="findVehicle">
			<a href="findMyVehicle"><button class="buttonstyle1" type="button">FIND VEHICLE</button></a>
		</div>
	</div>

	<div class="optionbuttons" id="showslots">
			<a href="showSlots"><button class="buttonstyle1" type="button">SHOW SLOTS</button></a>
	</div>
</body>
</html>