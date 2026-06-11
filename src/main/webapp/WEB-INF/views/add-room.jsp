<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Room</title>

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family: Arial, sans-serif;
        }

        body{
            min-height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
            background: linear-gradient(135deg, #667eea, #764ba2);
            padding:30px;
        }

        .container{
            width:100%;
            max-width:500px;
            background:white;
            border-radius:25px;
            padding:40px;
            box-shadow:0 15px 35px rgba(0,0,0,0.2);
        }

        h2{
            text-align:center;
            color:#333;
            margin-bottom:30px;
            font-size:32px;
        }

        .form-group{
            margin-bottom:22px;
        }

        label{
            display:block;
            margin-bottom:8px;
            font-size:16px;
            font-weight:bold;
            color:#444;
        }

        input,
        select{
            width:100%;
            padding:14px;
            border:2px solid #ddd;
            border-radius:12px;
            font-size:16px;
            transition:0.3s;
        }

        input:focus,
        select:focus{
            border-color:#667eea;
            outline:none;
            box-shadow:0 0 10px rgba(102,126,234,0.3);
        }

        .btn{
            width:100%;
            padding:15px;
            background:#28a745;
            color:white;
            border:none;
            border-radius:12px;
            font-size:18px;
            font-weight:bold;
            cursor:pointer;
            transition:0.3s;
        }

        .btn:hover{
            background:#218838;
            transform:translateY(-2px);
        }

        .view-link{
            display:block;
            text-align:center;
            margin-top:20px;
            text-decoration:none;
            color:#667eea;
            font-weight:bold;
            font-size:16px;
        }

        .view-link:hover{
            text-decoration:underline;
        }

    </style>
</head>

<body>

<div class="container">

    <h2>🏨 Add Room</h2>

    <form action="${pageContext.request.contextPath}/rooms/add"
          method="post">

        <div class="form-group">

            <label>Room Number</label>

            <input type="text"
                   name="roomNumber"
                   placeholder="Enter room number"
                   required>

        </div>

        <div class="form-group">

            <label>Room Type</label>

            <select name="roomType">

                <option value="STANDARD">
                    STANDARD
                </option>

                <option value="DELUXE">
                    DELUXE
                </option>

                <option value="SUITE">
                    SUITE
                </option>

            </select>

        </div>

        <div class="form-group">

            <label>Price Per Night (₹)</label>

            <input type="number"
                   name="price"
                   placeholder="Enter room price"
                   required>

        </div>

        <button type="submit" class="btn">

            Save Room

        </button>

    </form>

    <a href="${pageContext.request.contextPath}/rooms"
       class="view-link">

        ⬅ View Rooms

    </a>

</div>

</body>
</html>