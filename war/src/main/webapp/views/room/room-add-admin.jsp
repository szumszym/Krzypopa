<div class="row">
    <div class="col-lg-12">
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
    <div class="col-lg-12">
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
                        <label class="control-label col-lg-4">Name</label>

                        <div class="col-lg-8">
                            <input type="text" name="room_name" placeholder="Room"
                                   class="form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Room No.</label>

                        <div class="col-lg-8">
                            <input type="text" name="roomno"
                                   class="form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Description</label>

                        <div class="col-lg-8">
                            <textarea name="description" placeholder="Description"
                                      class="form-control"></textarea>
                        </div>
                    </div>
                    <!-- /.row -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Count of beds</label>

                        <div class="col-lg-4">
                            <input type="text" name="bed_count" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">Bed Type</label>

                        <div class="col-lg-4">
                            <input type="text" name="bed_type" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-4">Capacity</label>

                        <div class="col-lg-4">
                            <input disabled type="text" name="capacity" id="roa-capacity" class="form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Price</label>

                        <div class="col-lg-8">
                            <input name="price" type="text" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">Select Additions</label>

                        <div class="col-lg-8">
                            <select id="room-addition-select" name="addition" class="form-control">
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-lg-4"></label>

                        <div class="col-lg-8">
                            <div class="checkbox">
                                <label>
                                    <input name="published" class="uniform" type="checkbox" checked>Published
                                </label>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-lg-4">Add Room</label>

                        <div class="col-lg-8">
                            <div class="input-group">
                                <input type="submit" name="submit" class="btn btn-primary" data-original-title=""
                                       title="" value="Add"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!--row End -->
</div>

<script src="./assets/js/app/views/room/add.js"></script>
<script>
    $(".chzn-select").chosen();
</script>



