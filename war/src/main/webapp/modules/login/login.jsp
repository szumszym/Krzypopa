<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <meta name="msapplication-TileColor" content="#5bc0de"/>
    <meta name="msapplication-TileImage" content="assets/img/metis-tile.png"/>
    <link rel="stylesheet" href="./assets/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="./assets/css/main.css">
    <link rel="stylesheet" href="./assets/lib/magic/magic.css">
</head>
<body class="login">
<div class="container">
    <div class="text-center">
        <s:a action="" namespace="">
            <img src="./assets/img/logo.png" alt="Metis Logo">
        </s:a>
    </div>
    <div class="tab-content">
        <div id="login" class="tab-pane active">
            <s:form action="dashboard" cssClass="form-signin">
                <p class="text-muted text-center">
                    Enter your login and password
                </p>
                <s:textfield name="login" placeholder="login" cssClass="form-control"/>
                <s:password name="password" placeholder="password" cssClass="form-control"/>
                <s:submit cssClass="btn btn-lg btn-primary btn-block" value="Sign in"/>
            </s:form>
        </div>
        <div id="forgot" class="tab-pane">
            <s:form action="forgot" cssClass="form-signin">
                <p class="text-muted text-center">Enter your valid e-mail</p>
                <s:textfield name="email" placeholder="mail@domain.com" required="true" cssClass="form-control"/>
                <br>
                <s:submit cssClass="btn btn-lg btn-danger btn-block" value="Recover Password"/>
            </s:form>
        </div>
        <div id="signup" class="tab-pane">
            <s:form action="register" cssClass="form-signin">
                <s:textfield name="login" placeholder="login" cssClass="form-control"/>
                <s:textfield name="email" placeholder="mail@domain.com" cssClass="form-control"/>
                <s:password name="password" placeholder="password" cssClass="form-control"/>
                <s:submit cssClass="btn btn-lg btn-success btn-block" value="Register"/>
            </s:form>
        </div>
    </div>
    <div class="text-center">
        <ul class="list-inline">
            <li><a class="text-muted" href="#login" data-toggle="tab">Login</a></li>
            <li><a class="text-muted" href="#forgot" data-toggle="tab">Forgot Password</a></li>
            <li><a class="text-muted" href="#signup" data-toggle="tab">Signup</a></li>
        </ul>
        <s:a action="" namespace="" cssClass="btn btn-warning">
            Back
        </s:a>
    </div>
</div>
<!-- /container -->
<script src="./assets/lib/jquery.min.js"></script>
<script src="./assets/lib/bootstrap/js/bootstrap.js"></script>

<script src="./assets/js/app/namespace.js"></script>
<script src="./assets/js/app/utils/Utils.js"></script>
<script> App.Utils.initLoginForm() </script>
</body>
</html>
