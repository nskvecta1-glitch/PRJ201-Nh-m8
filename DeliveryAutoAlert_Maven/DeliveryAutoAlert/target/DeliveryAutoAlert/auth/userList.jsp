<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Users</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Users</h2>
<a href="${pageContext.request.contextPath}/UserServlet?action=new" class="btn btn-success">+ New User</a></div>
<table><tr><th>#</th><th>Username</th><th>Full Name</th><th>Email</th><th>Role</th><th>Active</th><th>Actions</th></tr>
<% List<UserDTO> users=(List<UserDTO>)request.getAttribute("users");
   if(users!=null) for(UserDTO u:users){ %>
<tr><td><%=u.getUserID()%></td><td><%=u.getUserName()%></td><td><%=u.getFullname()!=null?u.getFullname():""%></td>
<td><%=u.getEmail()!=null?u.getEmail():""%></td><td><%=u.getRoleID()%></td>
<td><%=u.isIsActive()?"Yes":"No"%></td>
<td><a href="${pageContext.request.contextPath}/UserServlet?action=edit&id=<%=u.getUserID()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/UserServlet?action=delete&id=<%=u.getUserID()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Delete</a></td></tr>
<% } %></table></div></body></html>
