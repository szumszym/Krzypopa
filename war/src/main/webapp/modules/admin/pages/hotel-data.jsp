<div id="server-messages"></div>


<div class="row">
<div class="col-lg-6">
    <div class="box dark">
        <header>
            <div class="icons">
                <i class="fa fa-table"></i>
            </div>
            <h5>Hotel Data</h5>

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

            <table id="hotel-table"
                   class="table table-bordered table-condensed sortableTable responsive-table table-striped">


            </table>
        </div>
    </div>
</div>

<div class="col-lg-6">
    <div class="box dark">
        <header>
            <div class="icons">
                <i class="fa fa-edit"></i>
            </div>
            <h5>Add New Hotel</h5>

            <div class="toolbar">
                <ul class="nav">
                    <li>
                        <a class="minimize-box" data-toggle="collapse" href="#div-2">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- /.toolbar -->
        </header>

        <div id="div-2" class="accordion-body collapse in body">

            <form id="hotel-add" action="hotel-add" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-lg-4">Name</label>

                    <div class="col-lg-8">
                        <input type="text" name="name" id="ha-name" placeholder="Name"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Email</label>

                    <div class="col-lg-8">
                        <input type="text" name="email" id="ha-email" placeholder="sample@sample.com"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Phone</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input name="phone_number" id="ha-phone_number" class="form-control" type="text"
                                   data-mask="+48 999 999 999">
                            <span class="input-group-addon">+48 999 999 999</span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-4">City</label>

                    <div class="col-lg-8">
                        <input type="text" name="city" id="ha-city" placeholder="eg: Kraków"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Street</label>

                    <div class="col-lg-8">
                        <input type="text" name="street" id="ha-street" placeholder="eg: Zakopianska"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Building No.</label>

                    <div class="col-lg-8">
                        <input type="text" name="building_no" id="ha-building_no" placeholder="eg: 10"
                               class="validate[required], custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Post code</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input type="text" name="postcode" id="ha-postcode" class="validate[required] form-control"
                                   data-mask="99-999">
                            <span class="input-group-addon">32-600</span>
                        </div>
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="ha-description" class="control-label col-lg-4">Description</label>

                    <div class="col-lg-8">
                        <textarea name="description" id="ha-description" placeholder="Description"
                                  class="validate[required] form-control"></textarea>
                    </div>
                </div>
                <!-- /.row -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Add Hotel</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input type="submit" name="submit"
                                   onclick="ajaxSubmit('hotel-add', 'server-messages'); return false;"
                                   class="btn btn-primary" data-original-title="" title="" value="Add"/>
                        </div>
                    </div>
                </div>
            </form>


        </div>
    </div>
</div>


</div>
<!--row End -->
<script src="./assets/js/hotel.js"></script>