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
			<div class="inf-head"><s:text name="login.title"></s:text></div>
			<div class="login-box">
				<s:form action="login">
					
					<s:textfield key="login.username" name="username"></s:textfield>
					<s:password  key="login.password" name="password"></s:password>
					<s:submit key="login.sub"></s:submit> 
					<s:fielderror fieldName="usererror" ></s:fielderror>   	
					
				</s:form>
				  <s:a href="register.jsp"><s:text name="login.tores"></s:text></s:a> | 	
				<div class="login-box-footer"></div>
			</div>
	</body>
</html>
