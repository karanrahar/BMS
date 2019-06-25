<html>  
<head>
<style>
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  border-radius: 8px;
  color: white;
  padding: 24px 40px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
}

.button1 {
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
  border-radius: 8px;
   padding: 16px;
  width:300px;
  text-align: center;
  font-size: 24px;
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
  cursor: pointer;
}

.button1:hover {
  background-color: #4CAF50;
  color: white;
}

h1{
	font-size:60;
}
h2{
	font-size:40;
	margin-left: 50px;
}


</style>
</head>
<body>  
<center><h1>Welcome ${user}</h1></center>
<!-- <h2>Operations:</h2> -->
	<form target = "_blank">
		<center><input type="submit" class="button1" formaction = "search" value = "Search"><br>
		<center><input type="submit" class="button1" formaction = "vau" value = "View all Users">
		<input type="submit" class="button1" formaction = "vab" value = "View all Books">
		<input type="submit" class="button1" formaction = "vaa" value = "View all Authors"><br>
		<center><input type="submit" class="button1" formaction = "redirectToAddAuthor" value = "Add Author">
		<input type="submit" class="button1" formaction = "redirectToAddBook" value = "Add Book">
		<input type="submit" class="button1" formaction = "redirectToRegister" value = "Add Admin">
	</form>
</body>  
</html> 