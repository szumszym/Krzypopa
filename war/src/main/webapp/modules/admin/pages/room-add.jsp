
<div class="row">
    <div class="col-lg-6">
        <div class="box">
            <header>
                <div class="icons">
                    <i class="fa fa-table"></i>
                </div>
                <h5>Select Hotel</h5>

                <div class="toolbar">
                    <div class="btn-group">
                        <a href="#sortableTable" data-toggle="collapse" class="minimize-box">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
            </header>

            <div id="sortableTable" class="body collapse in">
                <table id="hotel-table-small"
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
                <h5>Select Additions</h5>

                <div class="toolbar">
                    <div class="btn-group">
                        <a href="#sortableTable" data-toggle="collapse" class="minimize-box">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
            </header>

            <div id="sortableTable2" class="body collapse in">
                <table id="addition-table-small"
                       class="table table-bordered table-condensed sortableTable responsive-table table-striped">
                </table>
            </div>
        </div>
    </div>
</div>



<div class="row">
    <div class="col-lg-6">
        <div class="box dark">
            <header>
                <div class="icons">
                    <i class="fa fa-edit"></i>
                </div>
                <h5>Add New Room</h5>

                <div class="toolbar">
                    <ul class="nav">
                        <li>
                            <a class="minimize-box" data-toggle="collapse" href="#form-1">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- /.toolbar -->
            </header>

            <div id="form-1" class="accordion-body collapse in body">
                <form id="room-add" class="form-horizontal" action="room-add">

                    <div class="form-group">
                        <label class="control-label col-lg-4">Room Name</label>

                        <div class="col-lg-8">
                            <input type="text" name="room_name" placeholder="Room"
                                   class="form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">RoomNo.</label>

                        <div class="col-lg-8">
                            <input type="text" name="roomno"
                                   class="form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Description</label>

                        <div class="col-lg-8">
                            <textarea name="description"  placeholder="Description"
                                      class="form-control"></textarea>
                        </div>
                    </div>
                    <!-- /.row -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Capacity</label>

                        <div class="col-lg-8">
                            <input type="text" name="capacity" id="roa-capacity"
                                   class="form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Select Additions</label>
                        <div class="col-lg-8">
                            <select id="room-addition-select" name="addition"  class="form-control">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">Select Hotel</label>
                        <div class="col-lg-8">
                            <select  id="room-hotel-select" name="hotel"  class="form-control">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">Add Room</label>

                        <div class="col-lg-8">
                            <div class="input-group">
                                <input type="submit" name="submit"
                                       onclick="ajaxSubmit('room-add', 'server-messages'); return false;"
                                       class="btn btn-primary" data-original-title="" title="" value="Add"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!--row End -->
</div>

<script src="./assets/js/room.js"></script>
<script>
        $(".chzn-select").chosen();
        bindSelectTable('room-hotel-select', 'hotel-table-small', false);
        bindSelectTable('room-addition-select', 'addition-table-small', true);
</script>



