<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="data:;base64,=">
<link rel="stylesheet" type="text/css" href="/css/simple.css">
<title>Hello</title>
</head>
<body>
    <h1>CSRF hello</h1>

    <h2>How do you feel today?</h2>
    <form method="post" action="/welcome">
        <p>
            <label>I'm ... <input name="feeling" required autofocus></label>
        </p>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
        <button>OK</button>
    </form>
    <p>
        Back <a href="/">home</a>
    </p>
</body>
</html>