<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Equipment Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />
<h1>Equipment Added Successfully</h1>
<p>The equipment was successfully added.</p>

<form action="${pageContext.request.contextPath}/AddEquipment" method="get">
    <input type="hidden" name="userId" value="${param.userId}">
    <button type="submit">Add Equipment</button>
</form>

<form action="${pageContext.request.contextPath}/" method="get">
    <input type="submit" value="Return Home">
</form>

<%
    // Set heroId in the session
    String heroId = request.getParameter("heroId");
    if (heroId != null && !heroId.isEmpty()) {
        session.setAttribute("heroId", heroId);
    }
%>
</body>
</html>
