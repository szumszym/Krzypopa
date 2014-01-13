<%--
  Created by IntelliJ IDEA.
  User: thx-
  Date: 13.01.14
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <title>BookingSystem</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="./../../assets/lib/bootstrap/css/bootstrap-2.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->



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
            <form class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
            </form>
        </div>
        <!--/.navbar-collapse -->
    </div>
</div>
<div class="jumbotron">
    <div class="container ">
        <h1 class="text-center">Hello, in reservation world!</h1>

        <p class="text-center text-muted">Making reservation is pretty easy just follow 3 steps process</p>

    </div>
</div>

<div class="darken-bg">
    <div class="container">
        <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a href="#" data-url="./../../modules/head/first.jsp" data-placement="#context" data-toggle="tab">Step One</a></li>
            <li><a href="#" data-url="./../../modules/head/secend.jsp" data-placement="#context" data-toggle="tab">Step Two</a></li>
            <li><a href="#" data-url="./../../modules/head/third.jsp" data-placement="#context" data-toggle="tab">Step Three</a></li>
        </ul>
     </div>

    <div class="container" id=context data-default="./modules/head/first.jsp"></div>



</div>
<div class="box-si">

</div>
<div class="navbar navbar-inverse navbar-fixed-bottom">
    <div class="container">
        <p class="footer-text">&#169 1999-2014 BookingSystem. All rights reserved, created by Szymon & Sebastian</p>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="./../../assets/lib/jquery.min.js"></script>

<script src="./../../assets/lib/chosen/chosen.jquery.js"></script>
<script src="./../../assets/lib/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<%--<script type="text/javascript" src="./assets/js/style-switcher.js"></script>--%>
<%--<script src="./assets/lib/fullcalendar-1.6.2/fullcalendar/fullcalendar.min.js"></script>--%>
<%--<script src="./assets/lib/tablesorter/js/jquery.tablesorter.min.js"></script>--%>
<%--<script src="./assets/lib/sparkline/jquery.sparkline.min.js"></script>--%>
<%--<script src="./assets/lib/flot/jquery.flot.js"></script>--%>
<%--<script src="./assets/lib/flot/jquery.flot.selection.js"></script>--%>
<%--<script src="./assets/lib/flot/jquery.flot.resize.js"></script>--%>
<script src="./../../assets/lib/datatables/jquery.dataTables.js"></script>
<script src="./../../assets/lib/datatables/DT_bootstrap.js"></script>

<script src="./../../assets/js/app/metisMenu.js"></script>
<script src="./../../assets/js/main.js"></script>
<script src="./../../assets/js/ajaxFunctions.js"></script>


<script src="./../../assets/lib/bootstrap/js/bootstrap.min.js"></script>
<%--<script>
    $(function () {
        $("#myTab").tabs();
    });
</script>--%>
</body>
</html>