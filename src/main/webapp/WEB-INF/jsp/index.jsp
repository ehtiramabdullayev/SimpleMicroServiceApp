<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<style>

</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/content-table.css" />

<script type="text/javascript" src=""></script>
</head>
<body>

<div id="main">
<div id="header"><h2>Post your message to the world !!! </h2></div>
<div id="content-all">
    <div id ="menu">
      <a href="home">Home</a>
      <a href="post">Post</a>
      <a href="#">All posts</a>
      <a href="#">Sample3</a>
    </div>
    <div id ="content">
    	<h2>Content</h2>

    	<form method="post" class="post-message" action="/post">
    		Post Title: <br> <input type="text" name="title"><br>
    		Post Author:<br> <input type="text" name="author"> <input
    			type="submit" value="Submit">

    	</form>


    	<div class="header">Header </div>
    	<table id="content-table">
    		<thead>
    			<tr>
    				<th>Id</th>
    				<th>Title</th>
    				<th>Author</th>
    			</tr>
    		</thead>
    		<c:forEach var="post" items="${posts}">
    			<tr>
    				<td>${post.id}</td>
    				<td>${post.title}</td>
    				<td>${post.author}</td>
    			</tr>
    		</c:forEach>
    	</table>
    </div>


</div>


<div id="footer">© 2018, EPAM systems </div>

</div>



</body>
</html>