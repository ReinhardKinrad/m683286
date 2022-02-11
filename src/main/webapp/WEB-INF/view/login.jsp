<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Spring Security Example </title>
</head>
<body>
<c:form action="/login" method="post">
    <div><label>Username: <input type="text" name="username"/></label></div>
    <div><label>Password: <input type="password" name="password"/></label></div>
    <div><input type="submit" value="Submit"/></div>
</c:form>
</body>
</html>