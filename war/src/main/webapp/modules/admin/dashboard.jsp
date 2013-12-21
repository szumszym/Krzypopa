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

    <!-- Metis core stylesheet -->
    <link rel="stylesheet" href="./assets/css/main.css">
    <link rel="stylesheet" href="./assets/css/theme.css">
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
                    <div class="btn" style="color: white">Signed in as: <b><s:property value="username"/></b></div>
                </div>
                <div class="btn-group">
                    <a data-placement="bottom" data-original-title="Show / Hide Sidebar" data-toggle="tooltip"
                       class="btn btn-success btn-sm" id="changeSidebarPos">
                        <i class="fa fa-expand"></i>
                    </a>
                </div>
                <div class="btn-group">
                    <a data-url="./modules/admin/pages/settings.jsp" data-placement="#context"
                       data-original-title="Settings" class="btn btn-warning btn-sm" id="settings">
                        <i class="fa fa-cog"></i>
                    </a>
                </div>
                <div class="btn-group">
                    <a data-toggle="modal" data-original-title="Help" data-placement="bottom"
                       class="btn btn-default btn-sm" href="#helpModal">
                        <i class="fa fa-question"></i>
                    </a>
                </div>
                <div class="btn-group">
                    <s:a action="logout" cssClass="btn btn-metis-1 btn-sm">
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
                    <a href="#" data-url="./modules/admin/pages/reservation-browse.jsp" data-placement="#context">
                        <i class="fa fa-tasks"></i>&nbsp;Reservations
                    </a>
                </li>
                <li>
                    <a href="#" data-url="./modules/admin/pages/client-browse.jsp" data-placement="#context">
                        <i class="fa fa-users"></i>&nbsp;Clients
                    </a>
                </li>
            </ul>
            <!-- /.nav -->
        </div>
    </nav>
    <!-- /.navbar -->

    <!-- header.head -->
    <header class="head">
        <div class="search-bar">
            <a data-original-title="Show/Hide Menu" data-placement="bottom" data-tooltip="tooltip"
               class="accordion-toggle btn btn-primary btn-sm visible-xs" data-toggle="collapse" href="#menu"
               id="menu-toggle">
                <i class="fa fa-expand"></i>
            </a>

            <form class="main-search">
                <div class="input-group">
                    <input type="text" class="input-small form-control" placeholder="Live Search ...">
                <span class="input-group-btn">
                                    <button class="btn btn-primary btn-sm text-muted" type="button"><i
                                            class="fa fa-search"></i></button>
                                </span>
                </div>
            </form>
        </div>

        <!-- ."main-bar -->
        <div class="main-bar">
            <h3>
                <i class="fa fa-home"></i>&nbsp;DashBoard</h3>
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
        <li class="">
            <a href="#">
                <i class="fa fa-dashboard"></i>
                <span class="link-title">Dashboard</span>
            </a>
        </li>
        <li class="">
            <a href="#">
                <i class="fa fa-tasks"></i>&nbsp;Reservations
                <span class="fa arrow"></span>
            </a>
            <ul>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/reservation-add.jsp" data-placement="#context">
                        <i class="fa fa fa-plus"></i>&nbsp;Add new </a>
                </li>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/reservation-browse.jsp" data-placement="#context">
                        <i class="fa fa-th-list"></i>&nbsp;Browse Existing</a>
                </li>
            </ul>
        </li>
        <li class="">
            <a href="javascript:;">
                <i class="fa fa-users"></i>&nbsp;Clients
                <span class="fa arrow"></span>
            </a>
            <ul>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/client-add.jsp" data-placement="#context">
                        <i class="fa fa fa-plus"></i>&nbsp;Add new</a>
                </li>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/client-browse.jsp" data-placement="#context">
                        <i class="fa fa-th-list"></i>&nbsp;Browse Existing</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-suitcase"></i>&nbsp; Rooms
                <span class="fa arrow"></span></a>
            <ul>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/room-add.jsp" data-placement="#context">
                        <i class="fa fa fa-plus"></i>&nbsp;Add new </a>
                </li>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/room-browse.jsp" data-placement="#context">
                        <i class="fa fa-th-list"></i>&nbsp;Browse Existing</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-thumbs-up"></i>&nbsp;Status
                <span class="fa arrow"></span></a>
            <ul>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/status-add.jsp" data-placement="#context">
                        <i class="fa fa fa-plus"></i>&nbsp;Add new </a>
                </li>
                <li class="">
                    <a href="#" data-url="./modules/admin/pages/status-browse.jsp" data-placement="#context">
                        <i class="fa fa-th-list"></i>&nbsp;Browse Existing</a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#" data-url="./modules/admin/pages/prices.jsp" data-placement="#context">
                <i class="fa fa-btc"></i>&nbsp;Prices</a>
        </li>
        <li>
            <a href="#" data-url="./modules/admin/pages/additions.jsp" data-placement="#context">
                <i class="fa fa-foursquare"></i>&nbsp;Additions</a>
        </li>
        <li>
            <a href="#" data-url="./modules/admin/pages/hotel-data.jsp" data-placement="#context">
                <i class="fa  fa-home"></i>&nbsp;Hotel Data</a>
        </li>
        <li>
            <a href="#" data-url="./modules/admin/pages/user-data.jsp" data-placement="#context">
                <i class="fa fa-credit-card"></i>&nbsp;User Data</a>
        </li>
        <%--
                <li class="nav-divider"></li>
                <li>
                    <a href="#">
                        <i class="fa fa-sign-in"></i>&nbsp;Login Page</a>
                </li>--%>
    </ul>
    <!-- /#menu -->
</div>
<!-- /#left -->
<div id="content">
    <div class="outer">
        <div class="inner">
            <div id=context data-default="./modules/admin/pages/reservation-browse.jsp"></div>
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
<%--<script type="text/javascript" src="./assets/js/style-switcher.js"></script>--%>
<%--<script src="./assets/lib/fullcalendar-1.6.2/fullcalendar/fullcalendar.min.js"></script>--%>
<%--<script src="./assets/lib/tablesorter/js/jquery.tablesorter.min.js"></script>--%>
<%--<script src="./assets/lib/sparkline/jquery.sparkline.min.js"></script>--%>
<%--<script src="./assets/lib/flot/jquery.flot.js"></script>--%>
<%--<script src="./assets/lib/flot/jquery.flot.selection.js"></script>--%>
<%--<script src="./assets/lib/flot/jquery.flot.resize.js"></script>--%>
<script src="./assets/js/main.js"></script>
<script src="./assets/js/include.js"></script>
<script>
    $(function () {
        dashboard();
    });
</script>
</body>
</html>