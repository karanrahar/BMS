<html>  
<head>

      <title>Sample Code</title>
      <script type="text/javascript">
      function OpenNewWindow(MyPath)
      {
			window.open(MyPath,"","toolbar=no,status=no,menubar=no,location=center,scrollbars=no,resizable=no,height=5,width=5");
      }
      </script>
</head>
<body>  

<% session.setAttribute("isAdmin", false);%>
<form >
  
Email : <input type="text" name="email"/> <br><br>  
Password : <input type="password" name="pass"/> <br><br>   
<input type="submit"  formaction = "login" value="Login">  
<input type="button"  value="Register" onClick = "OpenNewWindow('redirectToRegister')"/>
</form>  
</body>  
</html>  