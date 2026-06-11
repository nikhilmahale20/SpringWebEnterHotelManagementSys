
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <title>My Bookings</title>

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:'Poppins',sans-serif;
        }

        body{
            background:#f1f5f9;
            min-height:100vh;
            padding:25px;
        }

        /* HEADER */

        .header{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:25px;
            gap:15px;
            flex-wrap:wrap;
        }

        .header h1{
            font-size:32px;
            color:#0f172a;
        }

        .dashboard-btn{
            text-decoration:none;
            background:#2563eb;
            color:white;
            padding:12px 20px;
            border-radius:10px;
            transition:0.3s;
            font-weight:500;
        }

        .dashboard-btn:hover{
            background:#1d4ed8;
        }

        /* TABLE */

        .table-wrapper{
            overflow-x:auto;
            border-radius:20px;
            box-shadow:0 5px 20px rgba(0,0,0,0.08);
            background:white;
        }

        table{
            width:100%;
            border-collapse:collapse;
            min-width:850px;
        }

        thead{
            background:#1e293b;
            color:white;
        }

        th{
            padding:18px;
            text-align:left;
            font-weight:500;
            white-space:nowrap;
        }

        td{
            padding:18px;
            border-bottom:1px solid #e2e8f0;
            color:#334155;
        }

        tr:hover{
            background:#f8fafc;
        }

        /* STATUS */

        .status{
            padding:8px 15px;
            border-radius:30px;
            font-size:13px;
            font-weight:600;
            display:inline-block;
            white-space:nowrap;
        }

        .confirmed{
            background:#dcfce7;
            color:#15803d;
        }

        .cancelled{
            background:#fee2e2;
            color:#dc2626;
        }

        .completed{
            background:#dbeafe;
            color:#2563eb;
        }

        /* BUTTONS */

        .action-buttons{
            display:flex;
            gap:10px;
            flex-wrap:wrap;
        }

        .btn{
            text-decoration:none;
            padding:10px 15px;
            border-radius:10px;
            font-size:14px;
            font-weight:500;
            transition:0.3s ease;
            display:inline-block;
            text-align:center;
            white-space:nowrap;
        }

        .cancel-btn{
            background:#ef4444;
            color:white;
        }

        .cancel-btn:hover{
            background:#dc2626;
        }

        .pay-btn{
            background:#16a34a;
            color:white;
        }

        .pay-btn:hover{
            background:#15803d;
        }

        /* EMPTY STATE */

        .empty{
            background:white;
            padding:50px 20px;
            border-radius:20px;
            text-align:center;
            box-shadow:0 5px 20px rgba(0,0,0,0.08);
        }

        .empty h2{
            margin-bottom:10px;
            color:#0f172a;
        }

        .empty p{
            color:#64748b;
        }

        /* MOBILE VIEW */

        @media screen and (max-width:768px){

            body{
                padding:15px;
            }

            .header{
                flex-direction:column;
                align-items:flex-start;
            }

            .header h1{
                font-size:26px;
            }

            .dashboard-btn{
                width:100%;
                text-align:center;
            }

            table{
                min-width:750px;
            }

            th,td{
                padding:14px;
                font-size:14px;
            }
        }

        @media screen and (max-width:480px){

            .header h1{
                font-size:22px;
            }

            body{
                padding:10px;
            }

            .action-buttons{
                flex-direction:column;
                width:100%;
            }

            .btn{
                width:100%;
                font-size:13px;
                padding:12px;
            }

            th,td{
                font-size:13px;
                padding:12px;
            }

            .status{
                font-size:12px;
                padding:7px 12px;
            }
        }

    </style>

</head>

<body>

<div class="header">

    <h1>📅 My Bookings</h1>

    <a href="customer-dashboard"
       class="dashboard-btn">

        ← Back Dashboard

    </a>

</div>

<c:if test="${empty bookings}">

    <div class="empty">

        <h2>No Bookings Found</h2>

        <p>You haven't booked any rooms yet.</p>

    </div>

</c:if>

<c:if test="${not empty bookings}">

    <div class="table-wrapper">

        <table>

            <thead>

            <tr>
                <th>Booking ID</th>
                <th>Room Number</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Status</th>
                <th>Actions</th>
                <th>Payment Status</th>
            </tr>

            </thead>

            <tbody>

            <c:forEach var="booking"
                       items="${bookings}">

                <tr>

                    <td>#${booking.bookingId}</td>

                    <td>
                        Room ${booking.room.roomNumber}
                    </td>

                    <td>${booking.checkInDate}</td>

                    <td>${booking.checkOutDate}</td>

                    <td>

        <span class="status
            ${booking.bookingStatus == 'CONFIRMED'
            ? 'confirmed'
            : booking.bookingStatus == 'CANCELLED'
            ? 'cancelled'
            : 'completed'}">

                ${booking.bookingStatus}

        </span>

                    </td>

                    <td>

                        <div class="action-buttons">

                            <c:if test="${booking.bookingStatus == 'CONFIRMED'}">

                                <a href="${pageContext.request.contextPath}/bookings/cancel/${booking.bookingId}"
                                   class="btn cancel-btn"
                                   onclick="return confirm('Cancel this booking?')">

                                    Cancel

                                </a>

                                <a href="${pageContext.request.contextPath}/payments/pay?id=${booking.bookingId}"
                                   class="btn pay-btn">

                                    Pay Now

                                </a>

                            </c:if>

                        </div>

                    </td>

                    <!-- PAYMENT STATUS COLUMN -->

                    <td>

                        <c:choose>

                            <c:when test="${not empty booking.payment}">

                                ${booking.payment.paymentStatus}

                            </c:when>

                            <c:otherwise>

                                PENDING

                            </c:otherwise>

                        </c:choose>

                    </td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>

</c:if>

</body>

</html>
