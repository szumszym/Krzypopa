<div id="server-messages"></div>

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
                        <label for="roa-room_name" class="control-label col-lg-4">Room Name</label>

                        <div class="col-lg-8">
                            <input type="text" name="room_name" id="roa-room_name" placeholder="Room"
                                   class="validate[required] form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">RoomNo.</label>

                        <div class="col-lg-8">
                            <input type="text" name="roomno" id="roa-roomno"
                                   class="validate[required],custom[number] form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label for="roa-description" class="control-label col-lg-4">Description</label>

                        <div class="col-lg-8">
                            <textarea name="description" id="roa-description" placeholder="Description"
                                      class="validate[required] form-control"></textarea>
                        </div>
                    </div>
                    <!-- /.row -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Capacity</label>

                        <div class="col-lg-8">
                            <input type="text" name="capacity" id="roa-capacity"
                                   class="validate[required],custom[number] form-control">
                        </div>
                    </div>
                    <!-- /.form-group -->

                    <div class="form-group">
                        <label class="control-label col-lg-4">Select Additions</label>
                        <div class="col-lg-8">
                            <select name="addition" multiple class="form-control chzn-select" tabindex="8">
                                <option value=""></option>
                                <option>Sample-1</option>
                                <option>Sample-2</option>
                                <option>Sample-3</option>
                                <option>Sample-4</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-4">Select Hotel</label>
                        <div class="col-lg-8">
                            <select name="hotel" data-placeholder="Select Hotel" class="form-control chzn-select" tabindex="7">
                                <option value=""></option>
                                <option value="1">Hotel_01</option>
                                <option>Sample-1</option>
                                <option>Sample-2</option>
                                <option>Sample-3</option>
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