
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<!DOCTYPE html>
<html>

<head>

    <title>Edit Room</title>

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
            min-height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
            background:#f1f5f9;
            padding:20px;
        }

        .container{
            width:100%;
            max-width:500px;
            background:white;
            padding:35px;
            border-radius:24px;
            box-shadow:0 8px 25px rgba(0,0,0,0.08);
        }

        .header{
            text-align:center;
            margin-bottom:30px;
        }

        .header h1{
            color:#0f172a;
            font-size:30px;
            margin-bottom:8px;
        }

        .header p{
            color:#64748b;
            font-size:14px;
        }

        .form-group{
            margin-bottom:22px;
        }

        label{
            display:block;
            margin-bottom:8px;
            font-size:14px;
            font-weight:500;
            color:#334155;
        }

        input,
        select{
            width:100%;
            padding:14px;
            border:1px solid #cbd5e1;
            border-radius:12px;
            outline:none;
            font-size:15px;
            transition:0.3s;
            background:#fff;
        }

        input:focus,
        select:focus{
            border-color:#2563eb;
            box-shadow:0 0 0 4px rgba(37,99,235,0.1);
        }

        .button-group{
            display:flex;
            gap:15px;
            margin-top:25px;
        }

        .btn{
            flex:1;
            text-align:center;
            text-decoration:none;
            border:none;
            padding:15px;
            border-radius:12px;
            font-size:15px;
            font-weight:600;
            cursor:pointer;
            transition:0.3s ease;
        }

        .update-btn{
            background:#2563eb;
            color:white;
        }

        .update-btn:hover{
            background:#1d4ed8;
            transform:translateY(-2px);
        }

        .back-btn{
            background:#e2e8f0;
            color:#0f172a;
        }

        .back-btn:hover{
            background:#cbd5e1;
        }

        /* MOBILE */

        @media(max-width:480px){

            .container{
                padding:25px;
                border-radius:18px;
            }

            .header h1{
                font-size:24px;
            }

            .button-group{
                flex-direction:column;
            }
        }

    </style>

</head>

<body>

<div class="container">

    <div class="header">

        <h1>
            🏨 Edit Room
        </h1>

        <p>
            Update room details easily
        </p>

    </div>

    <form action="${pageContext.request.contextPath}/rooms/edit"
          method="post">

        <input type="hidden"
               name="roomId"
               value="${room.roomId}">

        <!-- ROOM NUMBER -->

        <div class="form-group">

            <label>
                Room Number
            </label>

            <input type="text"
                   name="roomNumber"
                   value="${room.roomNumber}"
                   placeholder="Enter room number"
                   required>

        </div>

        <!-- ROOM TYPE -->

        <div class="form-group">

            <label>
                Room Type
            </label>

            <select name="roomType">

                <option value="STANDARD"
                ${room.roomType == 'STANDARD' ? 'selected' : ''}>

                    STANDARD

                </option>

                <option value="DELUXE"
                ${room.roomType == 'DELUXE' ? 'selected' : ''}>

                    DELUXE

                </option>

                <option value="SUITE"
                ${room.roomType == 'SUITE' ? 'selected' : ''}>

                    SUITE

                </option>

            </select>

        </div>

        <!-- PRICE -->

        <div class="form-group">

            <label>
                Price Per Night (₹)
            </label>

            <input type="number"
                   name="price"
                   value="${room.pricePerNight}"
                   placeholder="Enter room price"
                   required>

        </div>

        <!-- BUTTONS -->

        <div class="button-group">

            <a href="rooms"
               class="btn back-btn">

                ← Back

            </a>

            <button type="submit"
                    class="btn update-btn">

                Update Room

            </button>

        </div>

    </form>

</div>

</body>
</html>

