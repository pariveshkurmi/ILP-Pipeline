<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
form {
    border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button {
    background-color:blue ;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
}
</style>
</head>
<body>
	<h1>Integrated Learning Project</h1>
	<form action="login" method="post">
	  <div class="container" >
	    <label><b>Email</b></label>
	    <input type="text" placeholder="Enter Email" name="email" required>
	
	    <label><b>Password</b></label>
	    <input type="password" placeholder="Enter Password" name="password" required>
	        
	    <button type="submit">Login</button>
	  </div>
	
	  <div class="container" style="background-color:#f1f1f1">
	    <span class="psw"><a href="forgotpassword.jsp">Forgot password?</a>&nbsp &nbsp <a href="register.jsp" id="register">New User ?</a> </span>
	  </div>
</form>
</body>
</html>