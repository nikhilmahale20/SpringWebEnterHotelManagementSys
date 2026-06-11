
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<!DOCTYPE html>
<html>

<head>

    <title>Rooms Management</title>

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
            color:#0f172a;
            font-size:32px;
        }

        .back-btn{
            text-decoration:none;
            background:#2563eb;
            color:white;
            padding:12px 20px;
            border-radius:10px;
            transition:0.3s;
        }

        .back-btn:hover{
            background:#1d4ed8;
        }

        /* TABLE CONTAINER */

        .table-container{
            background:white;
            border-radius:20px;
            box-shadow:0 5px 20px rgba(0,0,0,0.08);
            overflow:hidden;
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

        /* BUTTONS */

        .btn{
            text-decoration:none;
            padding:10px 18px;
            border-radius:8px;
            font-size:14px;
            font-weight:500;
            transition:0.3s;
            display:inline-block;
        }

        .edit-btn{
            background:#2563eb;
            color:white;
        }

        .edit-btn:hover{
            background:#1d4ed8;
        }

        .delete-btn{
            background:#ef4444;
            color:white;
        }

        .delete-btn:hover{
            background:#dc2626;
        }

        /* PRICE STYLE */

        .price{
            font-weight:600;
            color:#16a34a;
        }

        /* MOBILE VIEW */

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
                min-width:700px;
            }

            td,th{
                padding:14px;
            }

            .btn{
                padding:8px 14px;
                font-size:13px;
            }
        }

    </style>

</head>

<body>

<div class="header">

    <h1>🏨 Room Management</h1>

    <a href="customer-dashboard"
       class="back-btn">
        ← Back to Dashboard
    </a>

</div>

<div class="table-container">

    <table>

        <thead>

        <tr>
            <th>ID</th>
            <th>Room Number</th>
            <th>Room Type</th>
            <th>Price / Night</th>

            <th>Delete</th>
        </tr>

        </thead>

        <tbody>

        <c:forEach var="room"
                   items="${rooms}">

            <tr>

                <td>${room.roomId}</td>

                <td>
                    Room ${room.roomNumber}
                </td>

                <td>
                        ${room.roomType}
                </td>

                <td class="price">
                    ₹${room.pricePerNight}
                </td>



                <td>

                    <a href="delete-room?id=${room.roomId}"
                       class="btn delete-btn"
                       onclick="return confirm('Delete this room?')">

                        Delete

                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>

</html>
