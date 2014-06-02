<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Hello World!</h2>
	<form action="<%=request.getContextPath()%>/signup" method="post">
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName" /></td>
			</tr>
			<tr>
				<td>EmailId</td>
				<td><input type="text" name="emailId" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit" /></td>
				<td></td>
			</tr>
		</table>

	</form>
	<h1>Login</h1>
	

<c:if test="${not empty param.error}">
    <div style="color: red;">
        Your login attempt was not successful, try again.<br />
        Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
</c:if>

	
	<form name='f'
		action="<%=request.getContextPath()%>/j_spring_security_check"
		method="post" id="loginform">
		<!-- Login -->
		<table class="loginformtable">
			<tr>
				<td class="td1"><input type='text' name='j_username'
					placeholder="Email Id" id="userid" /></td>
				<td class="td2"><input type='password' name='j_password'
					placeHolder="Password" /></td>
				<td class="td3"><input name="submitbtn" type="submit"
					value="Login" class="navButton Bnormal graybut" /></td>
			</tr>
			<tr>
				<td>Remember Me:</td>
				<td><input type="checkbox" name="_spring_security_remember_me" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
