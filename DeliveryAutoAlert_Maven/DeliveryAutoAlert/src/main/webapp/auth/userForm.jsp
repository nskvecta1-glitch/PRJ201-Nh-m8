<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>User Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% UserDTO u=(UserDTO)request.getAttribute("userEdit"); boolean isEdit=u!=null;
   List<RoleDTO> roles=(List<RoleDTO>)request.getAttribute("roles"); %>
<h2><%=isEdit?"Edit":"New"%> User</h2>
<form method="post" action="${pageContext.request.contextPath}/UserServlet">
<% if(isEdit){ %><input type="hidden" name="userId" value="<%=u.getUserID()%>"><% } %>
<div class="form-group"><label>Username</label><input type="text" name="username" value="<%=isEdit?u.getUserName():""%>" required/></div>
<div class="form-group"><label>Password<%=isEdit?" (leave blank to keep)":""%></label><input type="password" name="password" <%=isEdit?"":"required"%>/></div>
<div class="form-group"><label>Full Name</label><input type="text" name="fullname" value="<%=isEdit&&u.getFullname()!=null?u.getFullname():""%>"/></div>
<div class="form-group"><label>Email</label><input type="email" name="email" value="<%=isEdit&&u.getEmail()!=null?u.getEmail():""%>"/></div>
<div class="form-group"><label>Role</label>
<select name="roleId">
<% if(roles!=null) for(RoleDTO r:roles){ %><option value="<%=r.getRoleID()%>" <%=isEdit&&u.getRoleID()==r.getRoleID()?"selected":""%>><%=r.getRoleName()%></option><% } %>
</select></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/UserServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
