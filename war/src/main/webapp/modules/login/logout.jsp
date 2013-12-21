<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <meta name="msapplication-TileColor" content="#5bc0de"/>
    <meta name="msapplication-TileImage" content="./assets/img/metis-tile.png"/>
    <link rel="stylesheet" href="./assets/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="./assets/css/main.css">
    <link rel="stylesheet" href="./assets/lib/magic/magic.css">
    <link rel="stylesheet" href="./assets/lib/Font-Awesome/css/font-awesome.css"/>
</head>
<body class="error">
<div class="container">
    <div class="col-lg-8 col-lg-offset-2 text-center">
        <div class="logo">
            <h1>Info</h1>
        </div>
        <p class="lead text-muted">You have just logged out.</p>

        <div class="clearfix"></div>
        <br>

        <div class="col-lg-6  col-lg-offset-3">
            <div class="btn-group btn-group-justified">
                <s:a action="login" cssClass="btn btn-info">
                    Return to Login
                </s:a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
