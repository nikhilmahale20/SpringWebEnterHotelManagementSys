
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <title>Customer Login</title>

    <!-- Mobile Responsive -->
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:'Poppins', sans-serif;
        }

        body{
            min-height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
            background:linear-gradient(135deg, #4f46e5, #7c3aed);
            padding:20px;
            overflow-x:hidden;
        }

        .container{
            width:100%;
            max-width:420px;
            background:rgba(255,255,255,0.12);
            backdrop-filter:blur(20px);
            -webkit-backdrop-filter:blur(20px);
            border:1px solid rgba(255,255,255,0.2);
            border-radius:25px;
            padding:40px 35px;
            box-shadow:0 8px 32px rgba(0,0,0,0.2);
            color:white;
        }

        .login-header{
            text-align:center;
            margin-bottom:30px;
        }

        .login-header h1{
            font-size:32px;
            font-weight:600;
            margin-bottom:8px;
        }

        .login-header p{
            font-size:14px;
            color:#d1d5db;
        }

        .error-message{
            background:rgba(255,0,0,0.15);
            border:1px solid rgba(255,0,0,0.2);
            color:#ffb4b4;
            padding:12px;
            border-radius:12px;
            margin-bottom:20px;
            text-align:center;
            font-size:14px;
        }

        .form-group{
            margin-bottom:20px;
        }

        .form-group label{
            display:block;
            margin-bottom:8px;
            font-size:14px;
            font-weight:500;
        }

        .form-group input{
            width:100%;
            padding:15px;
            border:none;
            outline:none;
            border-radius:12px;
            background:rgba(255,255,255,0.15);
            color:white;
            font-size:15px;
            transition:0.3s;
        }

        .form-group input::placeholder{
            color:#d1d5db;
        }

        .form-group input:focus{
            background:rgba(255,255,255,0.22);
            border:1px solid rgba(255,255,255,0.7);
        }

        .login-btn{
            width:100%;
            border:none;
            outline:none;
            padding:15px;
            border-radius:12px;
            background:white;
            color:#4f46e5;
            font-size:16px;
            font-weight:600;
            cursor:pointer;
            transition:0.3s ease;
        }

        .login-btn:hover{
            transform:translateY(-2px);
            box-shadow:0 8px 20px rgba(255,255,255,0.25);
        }

        .footer-text{
            text-align:center;
            margin-top:22px;
            font-size:14px;
            color:#e5e7eb;
        }

        /* MOBILE VIEW */

        @media screen and (max-width: 480px){

            body{
                padding:15px;
                align-items:center;
            }

            .container{
                padding:30px 22px;
                border-radius:20px;
            }

            .login-header h1{
                font-size:26px;
            }

            .login-header p{
                font-size:13px;
            }

            .form-group input{
                padding:14px;
                font-size:14px;
            }

            .login-btn{
                padding:14px;
                font-size:15px;
            }

            .footer-text{
                font-size:13px;
            }
        }

    </style>
</head>

<body>

<div class="container">

    <div class="login-header">
        <h1>Welcome Back 👋</h1>
        <p>Please login to continue</p>
    </div>

    <!-- Error Message -->

    <%
        String error = (String) request.getAttribute("error");
        if(error != null){
    %>

    <div class="error-message">
        <%= error %>
    </div>

    <% } %>

    <!-- Login Form -->

    <form action="login" method="post">

        <div class="form-group">
            <label>Email Address</label>

            <input type="email"
                   name="email"
                   placeholder="Enter your email"
                   required>
        </div>

        <div class="form-group">
            <label>Password</label>

            <input type="password"
                   name="password"
                   placeholder="Enter your password"
                   required>
        </div>

        <button type="submit" class="login-btn">
            Login
        </button>

    </form>

    <div class="footer-text">
        Secure Customer Portal 🔒
    </div>
    <p>
        New User?

        <a href="${pageContext.request.contextPath}/register">
            Register Here
        </a>
    </p>
</div>

</body>
</html>

