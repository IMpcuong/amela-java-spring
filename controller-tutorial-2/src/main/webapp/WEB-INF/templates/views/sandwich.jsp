<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/26/2021
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sandwich condiments</title>
</head>
<body>
    <h2>Condiments List</h2>
    <form method="post">
        <input type="checkbox" id="vehicle1" name="condiment" value="lettuce">
        <label for="vehicle1"> lettuce </label><br>
        <input type="checkbox" id="vehicle2" name="condiment" value="tomato">
        <label for="vehicle2"> tomato </label><br>
        <input type="checkbox" id="vehicle3" name="condiment" value="mustard">
        <label for="vehicle3"> mustard </label><br>
        <input type="checkbox" id="vehicle4" name="condiment" value="sprouts">
        <label for="vehicle3"> sprouts </label><br><br>

        <input type="submit" value="Submit">
    </form>

    <h4 style="collapse: red">${requestScope["condiment-option"]}</h4>
</body>
</html>
