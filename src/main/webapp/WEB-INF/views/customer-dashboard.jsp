<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>

    <title>Customer Dashboard</title>

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
        }

        body{
            font-family:Arial,sans-serif;
            background:#f4f6f9;
        }

        .header{
            background:#007bff;
            color:white;
            padding:20px;
        }

        .header h1{
            margin-bottom:5px;
        }

        .container{
            width:90%;
            margin:auto;
            margin-top:30px;
        }

        .cards{
            display:grid;
            grid-template-columns:
                    repeat(auto-fit,minmax(250px,1fr));
            gap:20px;
        }

        .card{
            background:white;
            padding:25px;
            border-radius:10px;
            box-shadow:
                    0 2px 10px rgba(0,0,0,.1);
            text-align:center;
        }

        .card h3{
            margin-bottom:15px;
        }

        .btn{
            display:inline-block;
            text-decoration:none;
            padding:10px 20px;
            background:#007bff;
            color:white;
            border-radius:5px;
        }

        .btn:hover{
            background:#0056b3;
        }

        .logout{
            background:#dc3545;
        }

        .logout:hover{
            background:#b02a37;
        }

    </style>

</head>

<body>

<div class="header">

    <h1>Customer Dashboard</h1>

    <p>
        Welcome,
        ${customer.name}
    </p>

</div>

<div class="container">

    <div class="cards">

        <div class="card">

            <h3>Book Room</h3>

            <p>
                Search and book available rooms.
            </p>

            <br>

            <a class="btn"
               href="${pageContext.request.contextPath}/rooms">

                Book Now

            </a>

        </div>

        <div class="card">

            <h3>My Bookings</h3>

            <p>
                View all your bookings.
            </p>

            <br>

            <a class="btn"
               href="${pageContext.request.contextPath}/bookings/customer-bookings">

                View Bookings

            </a>

        </div>

        <div class="card">

            <h3>My Payments</h3>

            <p>
                View payment history.
            </p>

            <br>

            <a class="btn"
               href="${pageContext.request.contextPath}/payments/my-payments">

                View Payments

            </a>

        </div>

        <div class="card">

            <h3>Logout</h3>

            <p>
                End current session.
            </p>

            <br>

            <a class="btn logout"
               href="${pageContext.request.contextPath}/logout">

                Logout

            </a>

        </div>

    </div>

</div>

</body>
</html>