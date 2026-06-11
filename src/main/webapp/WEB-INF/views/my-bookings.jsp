
<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

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
            padding:30px;
        }

        /* HEADER */

        .header{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:30px;
            flex-wrap:wrap;
            gap:15px;
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

        /* COUNT CARD */

        .count-card{
            background:white;
            border-radius:20px;
            padding:25px;
            box-shadow:0 4px 14px rgba(0,0,0,0.08);
            margin-bottom:25px;
        }

        .count-card h3{
            color:#64748b;
            margin-bottom:10px;
        }

        .count-card p{
            font-size:35px;
            font-weight:600;
            color:#2563eb;
        }

        /* TABLE */

        .table-container{
            background:white;
            border-radius:20px;
            overflow:hidden;
            box-shadow:0 5px 20px rgba(0,0,0,0.08);
        }

        table{
            width:100%;
            border-collapse:collapse;
        }

        thead{
            background:#1e293b;
            color:white;
        }

        th{
            padding:18px;
            text-align:left;
            font-weight:500;
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
            border-radius:20px;
            font-size:13px;
            font-weight:600;
            display:inline-block;
        }

        .confirmed{
            background:#dcfce7;
            color:#15803d;
        }

        .cancelled{
            background:#fee2e2;
            color:#dc2626;
        }

        .checkedout{
            background:#dbeafe;
            color:#2563eb;
        }

        /* BUTTONS */

        .btn{
            text-decoration:none;
            padding:10px 15px;
            border-radius:8px;
            font-size:14px;
            font-weight:500;
            transition:0.3s;
            display:inline-block;
        }

        .checkout-btn{
            background:#16a34a;
            color:white;
        }

        .checkout-btn:hover{
            background:#15803d;
        }

        .cancel-btn{
            background:#ef4444;
            color:white;
        }

        .cancel-btn:hover{
            background:#dc2626;
        }

        /* EMPTY */

        .empty-state{
            background:white;
            text-align:center;
            padding:50px;
            border-radius:20px;
            box-shadow:0 5px 20px rgba(0,0,0,0.08);
            color:#64748b;
        }

        .empty-state h2{
            margin-bottom:10px;
            color:#0f172a;
        }

        /* MOBILE */

        @media(max-width:768px){

            body{
                padding:15px;
            }

            .header h1{
                font-size:24px;
            }

            .table-container{
                overflow-x:auto;
            }

            table{
                min-width:900px;
            }

            td,th{
                padding:14px;
            }
        }

    </style>

</head>

<body>

<div class="header">

    <h1>
        📅 My Bookings
    </h1>

    <a href="customer-dashboard"
       class="dashboard-btn">

        ← Dashboard

    </a>

</div>

<!-- BOOKINGS COUNT -->

<div class="count-card">

    <h3>Total Bookings</h3>

    <p>
        ${bookings.size()}
    </p>

</div>

<!-- EMPTY STATE -->

<c:if test="${empty bookings}">

    <div class="empty-state">

        <h2>No Bookings Found</h2>

        <p>
            You haven't booked any rooms yet.
        </p>

    </div>

</c:if>

<!-- BOOKINGS TABLE -->

<c:if test="${not empty bookings}">

    <div class="table-container">

        <table>

            <thead>

            <tr>
                <th>Booking ID</th>
                <th>Room Number</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            </thead>

            <tbody>

            <c:forEach var="booking"
                       items="${bookings}">

                <tr>

                    <td>
                        #${booking.bookingId}
                    </td>

                    <td>
                        Room ${booking.room.roomNumber}
                    </td>

                    <td>
                            ${booking.checkInDate}
                    </td>

                    <td>
                            ${booking.checkOutDate}
                    </td>

                    <td>

                    <span class="status
                        ${booking.bookingStatus == 'CONFIRMED'
                        ? 'confirmed'
                        : booking.bookingStatus == 'CANCELLED'
                        ? 'cancelled'
                        : 'checkedout'}">

                            ${booking.bookingStatus}

                    </span>

                    </td>

                    <td>

                        <c:if test="${booking.bookingStatus == 'CONFIRMED'}">

                            <a href="checkout-booking?id=${booking.bookingId}"
                               class="btn checkout-btn">

                                Check Out

                            </a>

                            <a href="${pageContext.request.contextPath}/bookings/cancel/${booking.bookingId}"
                               class="btn cancel-btn"
                               onclick="return confirm('Cancel this booking?')">

                                Cancel

                            </a>

                        </c:if>

                    </td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>

</c:if>

</body>

</html>
