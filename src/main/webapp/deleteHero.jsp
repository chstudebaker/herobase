<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Delete Confirmation</title>
  <link rel="stylesheet" href="css/delete.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<div class="container">
  <!-- Hidden input field to store userId -->
  <input type="hidden" name="userId" value="${param.userId}">
  <h2>Are you sure you want to delete?</h2>
  <div class="button-wrapper">
    <!-- Include userId in the URL of the "Yes" link -->
    <a href="${pageContext.request.contextPath}/DeleteHero?heroId=${param.heroId}&userId=${param.userId}" class="yes">Yes</a>
    <!-- Include userId in the URL of the "No" link -->
    <a href="${pageContext.request.contextPath}/heroList?userId=${param.userId}" class="no">No</a>
  </div>
</div>
</body>
</html>
