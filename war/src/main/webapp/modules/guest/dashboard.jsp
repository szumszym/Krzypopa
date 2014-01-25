<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>BookingSystem</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/lib/Font-Awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="./assets/lib/chosen/chosen.min.css">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="./assets/css/bootstrap-2.css">
    <link rel="stylesheet" href="./assets/css/dashboard.css">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Booking System</a>
        </div>
        <div class="navbar-collapse collapse">
            <div class="navbar-form navbar-right">
                <s:a action="login" namespace="" cssClass="btn btn-success">
                    Sign In
                </s:a>
            </div>
        </div>
        <!--/.navbar-collapse -->
    </div>
</div>
<div class="jumbotron">
    <div class="container ">
        <h1 class="text-center">Hello in reservation world!</h1>

        <p class="text-center text-muted">Make reservation in 3 easy steps!</p>

    </div>
</div>
<div id="content" style="padding-bottom: 60px;">
    <div id="context"></div>
</div>


<div class="navbar navbar-inverse navbar-fixed-bottom">
    <div class="container">
        <p class="footer-text">&#169 2013-2014 BookingSystem. All rights reserved, created by Szymon & Sebastian</p>
    </div>
</div>

<div id="server-messages"></div>

<script src="./assets/lib/jquery.min.js"></script>
<script src="./assets/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/lib/chosen/chosen.jquery.js"></script>
<script src="./assets/lib/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script src="./assets/lib/datatables/jquery.dataTables.js"></script>
<script src="./assets/lib/datatables/DT_bootstrap.js"></script>

<script src="./assets/js/app/namespace.js"></script>

<script src="./assets/js/app/utils/Utils.js"></script>

<script src="./assets/js/app/components/generator/Alert.js"></script>
<script src="./assets/js/app/components/generator/Modal.js"></script>

<script src="./assets/js/app/components/form/Utils.js"></script>
<script src="./assets/js/app/components/form/Submitter.js"></script>
<script src="./assets/js/app/components/form/Validator.js"></script>

<script src="./assets/js/app/components/Binder.js"></script>
<script src="./assets/js/app/components/Includer.js"></script>
<script src="./assets/js/app/components/Table.js"></script>
<script src="./assets/js/app/components/Select.js"></script>

<script src="./assets/js/app/models/Hotel.js"></script>
<script src="./assets/js/app/models/Room.js"></script>

<script src="./assets/js/app/main-start.js"></script>

</body>
</html>