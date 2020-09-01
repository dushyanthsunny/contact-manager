<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Manger</title>
</head>
<body>

	<h1 align="center">Contact Manger Application</h1>
	<div align="center">
		<h3>Contact List</h3>
		<h4><a href="newContact">New Contact</a></h4>
		<table border="1" cellpadding="5">
			<tr>
				<th>No</th>
				<th>Name</th>
				<th>Email</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${contactList}" var="contact" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${contact.name}</td>
					<td>${contact.email}</td>
					<td>${contact.address}</td>
					<td>${contact.phone}</td>
				<td>
				<a href="edit?id=${contact.id}">Edit</a>
				<a href="delete?id=${contact.id}">Delete</a>
				</td>
				</tr> 

			</c:forEach>
		</table>
	</div>

</body>
</html>