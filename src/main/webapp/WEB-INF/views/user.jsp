<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Management</title>
  </head>
  <body>
    <h1>User Data</h1>
    <form:form action="user.do" method="POST" commandName="user">
      <table>
        <tr>
          <td>User ID</td>
          <td><form:input path="userId" /></td>
        </tr>
        <tr>
          <td>User Name</td>
          <td><form:input path="userName" /></td>
        </tr>
        <tr>
          <td colspan="2">
            <input type="submit" name="action" value="Add" />
            <input type="submit" name="action" value="Edit" />
            <input type="submit" name="action" value="Delete" />
            <input type="submit" name="action" value="Search" />
          </td>
        </tr>
      </table>
    </form:form>
    <br>
    <table border="1">
      <th>ID</th>
      <th>Username</th>
      <c:forEach items="${userList}" var="user">
        <tr>
          <td>${user.userId}</td>
          <td>${user.userName}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
