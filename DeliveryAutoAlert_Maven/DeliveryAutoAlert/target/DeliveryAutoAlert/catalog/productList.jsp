<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,DTO.master.*" %>
<!DOCTYPE html><html><head><title>Products</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container">
<div class="toolbar"><h2>Products</h2>
<a href="${pageContext.request.contextPath}/ProductServlet?action=new" class="btn btn-success">+ New Product</a></div>
<table><tr><th>#</th><th>SKU</th><th>Name</th><th>Price</th><th>Actions</th></tr>
<% List<ProductDTO> products=(List<ProductDTO>)request.getAttribute("products");
   if(products!=null) for(ProductDTO p:products){ %>
<tr><td><%=p.getProductId()%></td><td><%=p.getSku()%></td><td><%=p.getProductName()%></td>
<td><%=p.getPrice()!=null?p.getPrice():""%></td>
<td><a href="${pageContext.request.contextPath}/ProductServlet?action=edit&id=<%=p.getProductId()%>" class="btn btn-warning btn-sm">Edit</a>
<a href="${pageContext.request.contextPath}/ProductServlet?action=delete&id=<%=p.getProductId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Delete?')">Delete</a></td></tr>
<% } %></table></div></body></html>
