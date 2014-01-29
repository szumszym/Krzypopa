<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <!--Mobile first-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--IE Compatibility modes-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-TileColor" content="#5bc0de">
    <meta name="msapplication-TileImage" content="./assets/img/metis-tile.png">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="./assets/lib/bootstrap/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="./assets/lib/Font-Awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="./assets/lib/chosen/chosen.min.css">

    <!-- Metis core stylesheet -->
    <link rel="stylesheet" href="./assets/css/main.css">
    <link rel="stylesheet" href="./assets/css/dashboard.css">
    <%--<link rel="stylesheet" href="./assets/lib/fullcalendar-1.6.2/fullcalendar/fullcalendar.css">--%>

    <!--Modernizr 3.0-->
    <script src="./assets/lib/modernizr-build.min.js"></script>
    <sj:head/>
</head>
<body>
<div id="wrap">
    <div id="top">

        <!-- .navbar -->
        <nav id="top-menu" class="navbar navbar-inverse navbar-static-top">

            <!-- Brand and toggle get grouped for better mobile display -->
            <header class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">
                    <img src="./assets/img/logo.png" alt="">
                </a>
            </header>
            <div class="topnav">
                <div class="btn-toolbar">
                    <div class="btn-group">
                        <div class="btn" style="color: white">Signed in as: <b><s:property value="login"/></b></div>
                    </div>
                    <div class="btn-group">
                        <a data-placement="bottom" data-original-title="Show / Hide Sidebar" data-toggle="tooltip"
                           class="btn btn-success btn-sm" id="changeSidebarPos">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                    <div class="btn-group hidden">
                        <a data-url="./views/account_settings/settings.jsp" data-placement="#context"
                           data-original-title="Settings" class="btn btn-warning btn-sm" id="settings">
                            <i class="fa fa-cog"></i>
                        </a>
                    </div>
                    <div class="btn-group hidden">
                        <a data-toggle="modal" data-original-title="Help" data-placement="bottom"
                           class="btn btn-default btn-sm" href="#helpModal">
                            <i class="fa fa-question"></i>
                        </a>
                    </div>
                    <div class="btn-group">
                        <s:a action="logout" namespace="" cssClass="btn btn-metis-1 btn-sm">
                            <i class="fa fa-power-off"></i>
                        </s:a>
                    </div>
                </div>
            </div>
            <!-- /.topnav -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">

                <!-- .nav -->
                <ul class="nav navbar-nav">
                    <li>
                        <a class="btn-select-hotel" href="#" data-url="./views/hotel/hotel-select.jsp"
                           data-placement="#context">
                            Select hotel
                        </a>
                    </li>
                    <li>
                        <a href="#" data-url="./views/reservation/reservation-browse-client.jsp"
                           data-placement="#context">
                        <i class="fa fa-tasks"></i>&nbsp;Reservations
                        </a>
                    </li>
                </ul>
                <!-- /.nav -->
            </div>
        </nav>
        <!-- /.navbar -->

        <!-- header.head -->
        <header class="head">

            <!-- ."main-bar -->
            <div class="main-bar" style="margin-left: 0">
                <h3 class="col-lg-4">
                    <i class="fa fa-home"></i>&nbsp;DashBoard</h3>

                <div class="menu-select-hotel col-lg-6" style="">
                    <div class="form-horizontal" style="">
                        <div class="form-group" style="margin:0;">
                            <label class="control-label col-lg-5" id="selected-hotel-name-label">
                                Selected Hotel: </label>

                            <div class="col-lg-7" style="padding:0">
                                <div id="selected-hotel-name"><s:property value="hotelname"/></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- /.main-bar -->
        </header>

        <!-- end header.head -->
    </div>
    <!-- /#top -->
    <div id="left">

        <!-- #menu -->
        <ul id="menu" class="collapse">
            <li class="nav-header">Menu</li>
            <%--<li class="nav-divider"></li>--%>
            <li class="hidden">
                <a href="#">
                    <i class="fa fa-dashboard"></i>
                    <span class="link-title">Dashboard</span>
                </a>
            </li>
            <li>
                <a href="#" data-url="./views/reservation/reservation-add-client.jsp" data-placement="#context">
                    <i class="fa fa fa-plus"></i>&nbsp;Add reservation </a>
            </li>

            <li>
                <a href="#" data-url="./views/reservation/reservation-browse-client.jsp" data-placement="#context">
                <i class="fa fa-th-list"></i>&nbsp;My reservations</a>
            </li>
        </ul>
        <!-- /#menu -->
    </div>
    <!-- /#left -->
    <div id="content">
        <div class="outer">
            <div class="inner">
                <div id=context data-default="./views/hotel/hotel-select.jsp"></div>
            </div>
        </div>

        <!-- end .inner -->
    </div>

    <!-- end .outer -->
</div>

<!-- end #content -->
<!-- /#wrap -->
<div id="footer">
    <p>2013 &copy; BS Admin</p>
</div>
<div id="server-messages"></div>
<!-- #helpModal -->
<div id="helpModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
                    et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                    aliquip ex ea commodo consequat. Duis aute irure dolor
                    in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
                    occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal --><!-- /#helpModal -->

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

<script src="./assets/js/app/models/Room.js"></script>
<script src="./assets/js/app/models/Hotel.js"></script>

<script src="./assets/js/app/main.js"></script>
</body>
</html>
