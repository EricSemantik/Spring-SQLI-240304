<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ma page d'accueil</title>
</head>
<body>
	<c:if test="${username != null && username != ''}">
		<h1>Bonjour ${username}</h1>
	</c:if>
	<c:if test="${username == null}">
		<h1>Bonjour le monde</h1>
	</c:if>
</body>
</html>