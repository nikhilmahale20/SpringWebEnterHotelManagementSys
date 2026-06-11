<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entity.Payment" %>
<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>
<html>

<head>

    <title>My Payments</title>

    <style>

        table {

            width: 100%;
            border-collapse: collapse;
        }

        th, td {

            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }

        th {

            background-color: #f2f2f2;
        }

    </style>

</head>

<body>

<h2>My Payment History</h2>

<%
    List<Payment> payments =
            (List<Payment>)
                    request.getAttribute(
                            "payments"
                    );
%>

<table>

    <tr>

        <th>Payment ID</th>

        <th>Booking ID</th>

        <th>Amount</th>

        <th>Payment Date</th>

        <th>Status</th>

    </tr>

    <%

        if(payments != null){

            for(Payment payment : payments){

    %>

    <tr>

        <td>
            <%= payment.getPaymentId() %>
        </td>

        <td>
            <%= payment.getBooking().getBookingId() %>
        </td>

        <td>
            ₹ <%= payment.getAmount() %>
        </td>

        <td>
            <%= payment.getPaymentDate() %>
        </td>

        <td>
            <%= payment.getPaymentStatus() %>
        </td>

    </tr>

    <%
            }
        }
    %>

</table>

<br>

<a href="customer-dashboard">

    Back To Dashboard

</a>

</body>

</html>