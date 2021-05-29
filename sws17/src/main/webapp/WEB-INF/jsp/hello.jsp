<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<body>
    <h1>CSRF hello</h1>
    <p>${_csrf} # ${_csrf.parameterName} # ${_csrf.token}</p>
    <p>Back <a href="/">home</a></p>
</body>
</html>