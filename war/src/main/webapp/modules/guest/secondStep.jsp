<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>BookingSystem</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="./assets/lib/bootstrap/css/bootstrap.css">
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
        <h1 class="text-center">Hello, in reservation world!</h1>

        <p class="text-center text-muted">Making reservation is pretty easy just follow 3 steps process</p>

    </div>
</div>

<div class="darken-bg">
    <div class="row">

        <div class="col-lg-12">
            <div class="box">
                <header>
                    <div class="icons">
                        <i class="fa fa-table"></i>
                    </div>
                    <h5>Select Room</h5>

                    <div class="toolbar">
                        <div class="btn-group">
                            <a href="#sortableTable" data-toggle="collapse" class="minimize-box">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                </header>

                <div id="sortableTable2" class="body collapse in">
                    <table id="room-table-guest"
                           class="table table-bordered table-condensed sortableTable responsive-table table-striped">
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="box dark">
                <header>
                    <div class="icons">
                        <i class="fa fa-edit"></i>
                    </div>
                    <h5>Reservation</h5>

                    <div class="toolbar">
                        <ul class="nav">
                            <li>
                                <a class="minimize-box" data-toggle="collapse" href="#div-1">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.toolbar -->
                </header>

                <div id="div-1" class="accordion-body collapse in body">
                    <form id="reservation-add-guest" action="reservation-add-guest" class="form-horizontal">

                        <div class="form-group">
                            <label class="control-label col-lg-4">Name</label>

                            <div class="col-lg-8">
                                <input type="text" name="name" placeholder="Reservation"
                                       class="validate[required] form-control">
                            </div>
                        </div>
                        <!-- /.form-group -->

                        <div class="form-group">
                            <label class="control-label col-lg-4">Start Date</label>

                            <div class="col-lg-8">
                                <input id="date_from" type="date" name="date_from"
                                       class="validate[required] form-control">
                            </div>
                        </div>
                        <!-- /.form-group -->

                        <div class="form-group">
                            <label class="control-label col-lg-4">End Date</label>

                            <div class="col-lg-8">
                                <input type="date" name="date_to" class="validate[required] form-control">
                            </div>
                        </div>
                        <!-- /.form-group -->

                        <div class="form-group">
                            <label class="control-label col-lg-4">Count of person</label>

                            <div class="col-lg-8">
                                <input type="text" name="person_count" class="validate[required] form-control">
                            </div>
                        </div>
                        <!-- /.form-group -->

                        <div class="form-group">
                            <label class="control-label col-lg-4">Client</label>

                            <div class="col-lg-8">
                                <select id="reservation-client-select" name="client_id" class="form-control">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-4">Rooms</label>

                            <div class="col-lg-8">
                                <select id="reservation-room-select-guest" data-placeholder="Choose rooms..."
                                        name="room_ids"
                                        class="form-control" tabindex="2">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-4">Status</label>

                            <div class="col-lg-8">
                                <select id="reservation-status-select" name="status_id" class="form-control">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-4"></label>

                            <div class="col-lg-8">
                                <div class="checkbox">
                                    <label>
                                        <input class="uniform" name="send_email" type="checkbox" value="send_email"
                                               checked>AutoSend
                                        Email to
                                        Client with confirmation
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-4">Add Reservation</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input type="submit" name="submit"
                                           onclick="SubmitterSubmit('reservation-add-guest', 'server-messages'); return false;"
                                           class="btn btn-primary" data-original-title="" title="" value="Add"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="box-si">

</div>
<div class="navbar navbar-inverse navbar-fixed-bottom">
    <div class="container">
        <p class="footer-text">&#169 1999-2014 BookingSystem. All rights reserved, created by Szymon & Sebastian</p>
    </div>
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

<script src="./assets/js/app/models/Hotel.js"></script>
<script src="./assets/js/app/models/Room.js"></script>

<script src="./assets/js/app/main.js"></script>

<script src="./assets/js/app/views/room/add-guest.js"></script>
<script>
    $(".chzn-select").chosen();
</script>
</body>
</html>