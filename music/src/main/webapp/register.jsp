<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>


	</head>
	<body> 
			<div class="inf-head"><s:text name="register.title"></s:text></div>
			<div >
				<s:form action="register" method="get">
					<s:fielderror fieldName="userexist"></s:fielderror>  
					<s:textfield  key="register.username" name="username"></s:textfield>
					<s:password   key="register.password1" name="password1"></s:password>
					<s:fielderror fieldName="upwd"></s:fielderror>  
					<s:password   key="register.password2" name="password2"></s:password>
					<s:textfield  key="register.nickname" name="nickname"></s:textfield>
					<s:submit key="register.sub"></s:submit>  		
				</s:form>
				<div class="login-box-footer"></div>
			</div>
	</body>
</html>
