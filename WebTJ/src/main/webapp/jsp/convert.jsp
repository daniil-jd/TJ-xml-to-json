<%--
  Created by IntelliJ IDEA.
  User: Daniil
  Date: 13.04.2018
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/styles.css" rel="stylesheet" type="text/css">
<html>
<head>
    <title>XML-to-JSON</title>
</head>
<body>

<div class="form-style-9">
    Load xml:
    <form action="" method="post" enctype="multipart/form-data">
        <input type="file" name="file" id ="file"/> <br>
        <input type="submit" name="submit"/>
    </form>
</div>
<div class="form-style-9">
    <pre>
        <%= request.getAttribute("json").toString() %>
    </pre>
</div>
</body>
</html>
