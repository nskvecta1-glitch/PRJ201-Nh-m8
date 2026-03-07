<%@ page contentType="text/html;charset=UTF-8" import="DTO.master.*" %>
<!DOCTYPE html><html><head><title>Customer Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% CustomerDTO c=(CustomerDTO)request.getAttribute("customer"); boolean isEdit=c!=null; %>
<h2><%=isEdit?"Edit":"New"%> Customer</h2>
<form method="post" action="${pageContext.request.contextPath}/CustomerServlet">
<% if(isEdit){ %><input type="hidden" name="customerId" value="<%=c.getCustomerID()%>"><% } %>
<div class="form-group"><label>Name</label><input type="text" name="customerName" value="<%=isEdit?c.getCustomerName():""%>" required/></div>
<div class="form-group"><label>Phone</label><input type="text" name="phone" value="<%=isEdit&&c.getPhone()!=null?c.getPhone():""%>"/></div>
<div class="form-group"><label>Email</label><input type="email" name="email" value="<%=isEdit&&c.getEmail()!=null?c.getEmail():""%>"/></div>
<div class="form-group"><label>Address</label><textarea name="address"><%=isEdit&&c.getAddress()!=null?c.getAddress():""%></textarea></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/CustomerServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
