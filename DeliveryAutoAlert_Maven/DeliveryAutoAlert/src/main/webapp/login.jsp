<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - DeliveryAutoAlert</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .login-wrap { max-width: 380px; margin: 100px auto; }
        .login-title { text-align:center; margin-bottom: 24px; font-size:22px; color:#2c3e50; }
    </style>
</head>
<body>
<div class="login-wrap">
    <div class="login-title">DeliveryAutoAlert</div>
    <div class="card">
        <h3>Sign In</h3>
        <% if(request.getAttribute("error") != null) { %>
        <div class="alert-error">${error}</div>
        <% } %>
        <form method="post" action="${pageContext.request.contextPath}/LoginServlet">
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" required autofocus />
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required />
            </div>
            <button type="submit" class="btn btn-primary" style="width:100%">Login</button>
        </form>
    </div>
</div>
</body>
</html>
