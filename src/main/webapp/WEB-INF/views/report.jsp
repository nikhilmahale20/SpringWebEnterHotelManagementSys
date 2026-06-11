<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <title>Reports</title>
</head>

<body>

<h2>Hotel Reports</h2>

<hr>

<h3>
    Total Revenue :
    ₹ ${revenue}
</h3>

<h3>
    Occupancy Rate :
    ${occupancy} %
</h3>

<br>

<a href="${pageContext.request.contextPath}/admin-dashboard">
    Back To Dashboard
</a>

</body>

</html>