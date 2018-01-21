<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add</title> 
</head>
<body>
<p>Add new establishment</p>
<form action="/establishment" method="post">
<input type="hidden" name="_method" value="PUT">
  Name:<br>
  <input type="text" name="name" value="${f.name}">
  <br>
  Address:<br>
  <input type="text" name="address" value="${f.address}">
  <br><br>
  <input type="submit" value="Edit">
</form> 
</html>