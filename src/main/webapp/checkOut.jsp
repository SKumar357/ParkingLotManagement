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
			background-color: #F6F2F2;
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
		#checkOutContents
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

		.checkOutTable table,tr,td
		{
			padding: 10px;
		}
		.checkOutTable td
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
	<div id="checkOutContents">
		<div id="checkOutTitle">
			<h1>CHECK OUT</h1>
		</div>
		<div id="checkOutForm">
			<form method="POST" action="checkOutSubmit">
				<table class="checkOutTable">
					<tr>
						<td><label for="vnumber">Vehicle Number</label></td>
						<td><input type="text" id="vnumber" name="vnumber" required=""></td>
					</tr>
					<tr>
						<td><label for="vnumber">Check out Time</label></td>
						<td><input type="datetime-local" id="vnumber" name="exittime" required=""></td>
					</tr>
				</table>
				<input type="submit" id="submitbutton" class="buttonstyle" value="CHECK OUT">	
			</form>
		</div>
	  <c:if test = "${message.length() > 0 and message1.length()==3}">
	  <p id="message" style="color:green">${status}</p>
         <p id="message">${message}</p>
      </c:if>
      <c:if test = "${message.length() > 0 and message1.length()==2}">
         <p id="message" style="color:red">${message}</p>
      </c:if>
	</div>
</body>
</html>