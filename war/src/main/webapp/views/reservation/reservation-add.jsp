<div class="row">
    <div class="col-lg-6">
        <div class="box">
            <header>
                <div class="icons">
                    <i class="fa fa-table"></i>
                </div>
                <h5>Select Client</h5>

                <div class="toolbar">
                    <div class="btn-group">
                        <a href="#sortableTable" data-toggle="collapse" class="minimize-box">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
            </header>

            <div id="sortableTable" class="body collapse in">
                <table id="client-table-small"
                       class="table table-bordered table-condensed sortableTable responsive-table table-striped">

                </table>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
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
                <table id="room-table-small"
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
                <form id="reservation-add" action="reservation-add" class="form-horizontal">

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
                            <input type="date" name="date_from" class="validate[required] form-control">
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
                            <select id="reservation-room-select" data-placeholder="Choose rooms..." name="room_ids"
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
                                       onclick="ajaxSubmit('reservation-add', 'server-messages'); return false;"
                                       class="btn btn-primary" data-original-title="" title="" value="Add"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="./assets/js/reservation-add.js"></script>
<script>
    $(".chzn-select").chosen();
    bindSelectTable('reservation-client-select', 'client-table-small', false);
    bindSelectTable('reservation-room-select', 'room-table-small', true);
</script>
