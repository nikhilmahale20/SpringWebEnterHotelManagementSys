<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Book Room</title>
</head>
<body>

<h2>Book Room</h2>

<form action="${pageContext.request.contextPath}/bookings/save"
      method="post">

    <input type="hidden"
           name="roomId"
           value="${roomId}" />

    Check In:

    <input type="date"
           name="checkInDate"
           required>

    <br><br>

    Check Out:

    <input type="date"
           name="checkOutDate"
           required>

    <br><br>

    <button type="submit">
        Confirm Booking
    </button>

</form>

</body>
</html>