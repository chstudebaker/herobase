<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Blog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/heroBase.css">
</head>
<body>
<c:import url="${pageContext.request.contextPath}/header.jsp" />
<c:import url="${pageContext.request.contextPath}/nav.jsp" />

<h2>Edit Blog</h2>

<form action="${pageContext.request.contextPath}/EditBlog?userId=${param.userId}" method="post">
    <!-- Hidden input field for Blog ID -->
    <input type="hidden" id="blogId" name="blogId" value="${blog.blogId}">

    <label for="heroID">Hero ID:</label>
    <input type="text" id="heroID" name="heroID" value="${param.heroId}" required>

    <!-- Blog Title -->
    <label for="blogTitle">Blog Title:</label>
    <input type="text" id="blogTitle" name="blogTitle" value="${blog.blogTitle}" required><br>

    <!-- Blog Content -->
    <label for="blogContent">Blog Content:</label>
    <textarea id="blogContent" name="blogContent" rows="8" cols="50" required>${blog.blogContent}</textarea><br>

    <!-- Submit Button -->
    <button type="submit">Save Changes</button>
</form>

</body>
</html>
