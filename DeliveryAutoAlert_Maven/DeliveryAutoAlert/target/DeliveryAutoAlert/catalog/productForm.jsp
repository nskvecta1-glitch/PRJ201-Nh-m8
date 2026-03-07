<%@ page contentType="text/html;charset=UTF-8" import="DTO.master.*" %>
<!DOCTYPE html><html><head><title>Product Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body><%@ include file="/nav.jsp" %>
<div class="container"><div class="card" style="max-width:500px">
<% ProductDTO p=(ProductDTO)request.getAttribute("product"); boolean isEdit=p!=null; %>
<h2><%=isEdit?"Edit":"New"%> Product</h2>
<form method="post" action="${pageContext.request.contextPath}/ProductServlet">
<% if(isEdit){ %><input type="hidden" name="productId" value="<%=p.getProductId()%>"><% } %>
<div class="form-group"><label>SKU</label><input type="text" name="sku" value="<%=isEdit?p.getSku():""%>" required/></div>
<div class="form-group"><label>Product Name</label><input type="text" name="productName" value="<%=isEdit?p.getProductName():""%>" required/></div>
<div class="form-group"><label>Price</label><input type="number" step="0.01" name="price" value="<%=isEdit&&p.getPrice()!=null?p.getPrice():""%>"/></div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="${pageContext.request.contextPath}/ProductServlet" class="btn btn-warning">Cancel</a>
</form></div></div></body></html>
