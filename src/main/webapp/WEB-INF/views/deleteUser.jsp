<%-- 
    Document   : deleteUser
    Created on : Sep 22, 2015, 4:09:23 PM
    Author     : Chris Puzzuoli <cpuzzuol@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <c:choose>
      <c:when test="${not empty user}">
          <h1>Delete ${user.userName}</h1>
          <br />
      </c:when>    
      <c:otherwise>
          <h1>No user with that ID.</h1> 
          <br />
      </c:otherwise>
    </c:choose>
  </body>
</html>
