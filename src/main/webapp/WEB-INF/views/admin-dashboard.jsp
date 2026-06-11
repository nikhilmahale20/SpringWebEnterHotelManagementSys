<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family: Arial, sans-serif;
        }

        body{
            min-height:100vh;
            background: linear-gradient(135deg, #667eea, #764ba2);
            display:flex;
            justify-content:center;
            align-items:center;
            padding:30px;
        }

        .dashboard-container{
            width:100%;
            max-width:950px;
            background:white;
            border-radius:25px;
            padding:40px;
            box-shadow:0 15px 35px rgba(0,0,0,0.2);
        }

        .header{
            text-align:center;
            margin-bottom:35px;
        }

        .header h2{
            font-size:36px;
            color:#333;
            margin-bottom:10px;
        }

        .welcome-card{
            background:#f4f6ff;
            border-left:6px solid #667eea;
            padding:25px;
            border-radius:15px;
            margin-bottom:35px;
        }

        .welcome-card p{
            font-size:18px;
            color:#444;
            margin:10px 0;
        }

        .welcome-card b{
            color:#667eea;
        }

        .menu-grid{
            display:grid;
            grid-template-columns:repeat(auto-fit, minmax(220px, 1fr));
            gap:25px;
        }

        .card{
            text-decoration:none;
            background:white;
            border-radius:20px;
            padding:30px;
            text-align:center;
            box-shadow:0 8px 20px rgba(0,0,0,0.1);
            transition:0.3s;
            border:2px solid #eee;
        }

        .card:hover{
            transform:translateY(-8px);
            box-shadow:0 12px 25px rgba(0,0,0,0.15);
        }

        .card-icon{
            font-size:50px;
            margin-bottom:15px;
        }

        .card-title{
            font-size:22px;
            font-weight:bold;
            color:#333;
            margin-bottom:10px;
        }

        .card-desc{
            color:#777;
            font-size:14px;
        }

        .add-room{
            border-top:5px solid #28a745;
        }

        .view-room{
            border-top:5px solid #007bff;
        }

        .reports{
            border-top:5px solid #ff9800;
        }

        .logout{
            border-top:5px solid #dc3545;
        }

    </style>
</head>

<body>

<div class="dashboard-container">

    <div class="header">
        <h2>🏨 Admin Dashboard</h2>
    </div>

    <div class="welcome-card">

        <p>
            Welcome:
            <b>${admin.name}</b>
        </p>

        <p>
            Role:
            <b>${admin.role}</b>
        </p>

    </div>

    <div class="menu-grid">

        <a href="${pageContext.request.contextPath}/rooms/add"
           class="card add-room">

            <div class="card-icon">➕</div>

            <div class="card-title">
                Add Room
            </div>

            <div class="card-desc">
                Add new rooms to hotel inventory
            </div>

        </a>

        <a href="${pageContext.request.contextPath}/rooms"
           class="card view-room">

            <div class="card-icon">🛏</div>

            <div class="card-title">
                View Rooms
            </div>

            <div class="card-desc">
                Manage available hotel rooms
            </div>

        </a>

        <a href="${pageContext.request.contextPath}/reports"
           class="card reports">

            <div class="card-icon">📊</div>

            <div class="card-title">
                View Reports
            </div>

            <div class="card-desc">
                Check hotel booking reports
            </div>

        </a>

        <a href="${pageContext.request.contextPath}/logout"
           class="card logout">

            <div class="card-icon">🚪</div>

            <div class="card-title">
                Logout
            </div>

            <div class="card-desc">
                Securely logout from system
            </div>

        </a>

    </div>

</div>

</body>
</html>