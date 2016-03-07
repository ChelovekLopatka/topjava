<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Meals</h1>

<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#fcf">
    <tr>
        <th>Date Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="4"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${param.list}" var="meal">
        <tr>
            <c:choose>
                <c:when test="${meal.exceed}">
                    <td bgcolor="#b22222"><c:out value="${meal.dateTime}" /></td>
                    <td bgcolor="#b22222"><c:out value="${meal.description}" /></td>
                    <td bgcolor="#b22222"><c:out value="${meal.calories}" /></td>
                </c:when>
                <c:otherwise>
                    <td><c:out value="${meal.dateTime}" /></td>
                    <td><c:out value="${meal.description}" /></td>
                    <td><c:out value="${meal.calories}" /></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
