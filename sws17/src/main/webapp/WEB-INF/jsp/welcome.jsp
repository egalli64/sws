<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/css/simple.css">
<title>Welcome ${param.feeling} ${name}</title>
</head>
<body>
    <h1>Welcome</h1>
    
    <p>Are you feeling ${param.feeling}, ${name}?</p> 
    <p>
        Back <a href="/">home</a>
    </p>
</body>
</html>