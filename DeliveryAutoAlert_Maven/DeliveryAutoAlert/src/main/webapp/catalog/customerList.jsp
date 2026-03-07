<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Customers</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Customers</h2>
<a href="${pageContext.request.contextPath}/CustomerServlet?action=new" class="btn btn-success">+ New Customer</a></div>
<table><tr><th>#</th><th>Name</th><th>Phone</th><th>Email</th><th>Address</th><th>Actions</th></tr>
<% List<CustomerDTO> customers=(List<CustomerDTO>)request.getAttribute("customers");
   if(customers!=null) for(CustomerDTO c:customers){ %>
<tr><td><%=c.getCustomerID()%></td><td><%=c.getCustomerName()%></td>
<td><%=c.getPhone()!=null?c.getPhone():""%></td><td><%=c.getEmail()!=null?c.getEmail():""%></td>
<td><%=c.getAddress()!=null?c.getAddress():""%></td>
<td><a href="${pageContext.request.contextPath}/CustomerServlet?action=edit&id=<%=c.getCustomerID()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/CustomerServlet?action=delete&id=<%=c.getCustomerID()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Delete</a></td></tr>
<% } %></table></div></body></html>
