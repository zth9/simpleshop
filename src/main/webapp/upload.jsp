<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">

<head>
    <meta charset="UTF-8"/>
    <title>上传文件</title>
</head>
<body>
    <form action="/xmshop/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file"><br/>
        <input type="submit" value="上传">
    </form>
</body>
</html>