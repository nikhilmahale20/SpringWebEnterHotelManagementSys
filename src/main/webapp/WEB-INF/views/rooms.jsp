<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Available Rooms</title>

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family: Arial, sans-serif;
        }

        body{
            background: linear-gradient(135deg, #667eea, #764ba2);
            min-height:100vh;
            padding:40px;
            display:flex;
            justify-content:center;
            align-items:center;
        }

        .container{
            width:95%;
            max-width:1100px;
            background:white;
            border-radius:20px;
            padding:35px;
            box-shadow:0 10px 30px rgba(0,0,0,0.2);
        }

        .header{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:30px;
            flex-wrap:wrap;
        }

        h2{
            color:#333;
            font-size:32px;
        }

        .room-count{
            background:#667eea;
            color:white;
            padding:12px 20px;
            border-radius:12px;
            font-size:18px;
            font-weight:bold;
        }

        table{
            width:100%;
            border-collapse:collapse;
            overflow:hidden;
            border-radius:15px;
        }

        th{
            background:#667eea;
            color:white;
            padding:18px;
            font-size:18px;
            text-transform:uppercase;
        }

        td{
            padding:18px;
            text-align:center;
            border-bottom:1px solid #eee;
        }

        tr:hover{
            background:#f8f9ff;
            transition:0.3s;
        }

        .status{
            padding:8px 15px;
            border-radius:20px;
            font-weight:bold;
            display:inline-block;
        }

        .available{
            background:#d4edda;
            color:#155724;
        }

        .booked{
            background:#f8d7da;
            color:#721c24;
        }

        .book-btn{
            text-decoration:none;
            background:#28a745;
            color:white;
            padding:10px 18px;
            border-radius:10px;
            font-weight:bold;
            transition:0.3s;
            display:inline-block;
        }

        .book-btn:hover{
            background:#218838;
            transform:translateY(-2px);
        }

        .empty-row{
            text-align:center;
            padding:25px;
            color:gray;
            font-size:18px;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="header">

        <h2>🏨 Available Rooms</h2>

        <div class="room-count">
            Total Rooms: ${rooms.size()}
        </div>

    </div>

    <table>

        <tr>
            <th>Room No</th>
            <th>Room Type</th>
            <th>Price/Night</th>
            <th>Status</th>
            <th>Edit</th>
<%--            <th>Action</th>--%>
        </tr>

        <c:choose>

            <c:when test="${not empty rooms}">

                <c:forEach items="${rooms}" var="room">

                    <tr>

                        <td>${room.roomNumber}</td>

                        <td>${room.roomType}</td>

                        <td>₹ ${room.pricePerNight}</td>

                        <td>

                            <span class="status
                                ${room.status == 'AVAILABLE' ? 'available' : 'booked'}">

                                    ${room.status}

                            </span>

                        </td>
                        <td>

                            <a href="edit-room?id=${room.roomId}"
                               class="btn edit-btn">

                                Edit

                            </a>

                        </td>

<%--                        <td>--%>

<%--                            <a href="${pageContext.request.contextPath}/bookings/book/${room.roomId}"--%>
<%--                               class="book-btn">--%>

<%--                                🛏 Book--%>

<%--                            </a>--%>

<%--                        </td>--%>

                    </tr>

                </c:forEach>

            </c:when>

            <c:otherwise>

                <tr>
                    <td colspan="5" class="empty-row">
                        No Rooms Available
                    </td>
                </tr>

            </c:otherwise>

        </c:choose>

    </table>

</div>

</body>
</html>