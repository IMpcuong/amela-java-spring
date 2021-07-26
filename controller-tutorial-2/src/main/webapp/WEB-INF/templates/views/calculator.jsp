<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/26/2021
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple Calculator</title>
</head>
<body>
    <form method="post">
        <a>Input your first operand: </a><br>
        <input type="text" name="operand">
        <br>
        <a>Input your second operand: </a><br>
        <input type="text" name="operand">
        <br>
        <span> <input type="submit" name="option" value="Add"> </span>
        <span> <input type="submit" name="option" value="Sub"></span>
        <span> <input type="submit" name="option" value="Mul"></span>
        <span> <input type="submit" name="option" value="Div"></span>

    </form>
    <h4 style="color: rebeccapurple"> ${requestScope["calculator-result"]}</h4>
</body>
</html>
