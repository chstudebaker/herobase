<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Powers Result</title>
    <link rel="stylesheet" href="css/heroBase.css">
</head>
<body>
<c:import url="header.jsp" />
<c:import url="nav.jsp" />
<h1>Power Addition Failed</h1>
<p>Failed to add the power. Please try again.</p>
<form action="AddHero" method="get">
    <input type="submit" value="Add Another Hero">
</form>
<form action="AddPowers" method="get">
    <input type="submit" value="Add Another Power">
</form>
<form action="${pageContext.request.contextPath}/" method="get">
    <input type="submit" value="Return Home">
</form>
</body>
</html>
