<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>

  <title>Register</title>

  <style>

    body{
      font-family: Arial;
      background: #f4f6f9;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .card{
      width: 450px;
      background: white;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,.15);
    }

    h2{
      text-align: center;
    }

    input{
      width: 100%;
      padding: 12px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    button{
      width: 100%;
      padding: 12px;
      background: green;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    button:hover{
      background: darkgreen;
    }

    .error{
      color: red;
      text-align: center;
    }

  </style>

</head>

<body>

<div class="card">

  <h2>Customer Registration</h2>

  <form action="${pageContext.request.contextPath}/register"
        method="post"
        onsubmit="return validateForm()">

    <input type="text"
           name="name"
           placeholder="Full Name"
           required>

    <!-- Email Validation -->
    <input type="email"
           name="email"
           id="email"
           placeholder="Email"
           required>

    <!-- Phone Validation -->
    <input type="text"
           name="phone"
           id="phone"
           placeholder="Phone Number"
           pattern="[0-9]{10}"
           maxlength="10"
           required
           title="Phone number must contain exactly 10 digits">

    <input type="password"
           name="password"
           placeholder="Password"
           required>

    <select name="role">

      <option value="CUSTOMER">
        Customer
      </option>

      <option value="ADMIN">
        Admin
      </option>

      <option value="RECEPTIONIST">
        Receptionist
      </option>

    </select>
    
    <button type="submit">
      Register
    </button>

  </form>

  <div class="error">
    ${error}
  </div>

</div>

<script>

  function validateForm() {

    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;

    // Email Regex Validation
    let emailPattern =
            /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    // Phone Validation
    let phonePattern =
            /^[0-9]{10}$/;

    if (!emailPattern.test(email)) {
      alert("Please enter a valid email address");
      return false;
    }

    if (!phonePattern.test(phone)) {
      alert("Phone number must contain exactly 10 digits");
      return false;
    }

    return true;
  }

</script>

</body>
</html>