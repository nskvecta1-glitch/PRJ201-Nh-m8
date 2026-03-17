<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login — DeliveryAutoAlert</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="login-page">
  <div class="login-wrap">
    <div class="login-logo">
      <div class="login-logo-mark"></div>
      <h1>DeliveryAutoAlert</h1>
      <p>Auto Alert &amp; Reconciliation System</p>
    </div>
    <div class="login-box">
      <h3>Sign in to your account</h3>
      <% if(request.getAttribute("error") != null) { %>
      <div class="alert-error">${error}</div>
      <% } %>
      <form method="post" action="${pageContext.request.contextPath}/LoginServlet">
        <div class="form-group">
          <label>Username</label>
          <input type="text" name="username" placeholder="Enter username" required autofocus />
        </div>
        <div class="form-group">
          <label>Password</label>
          <input type="password" name="password" placeholder="Enter password" required />
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
      </form>
    </div>
  </div>
</div>
</body>
</html>
