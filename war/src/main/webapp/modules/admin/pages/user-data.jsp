<div id="server-messages"></div>

<div class="row">
<div class="col-lg-6">
    <div class="box dark">
        <header>
            <div class="icons">
                <i class="fa fa-table"></i>
            </div>
            <h5>User Data</h5>

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

            <table id="user-table"
                   class="table table-bordered table-condensed sortableTable responsive-table table-striped">

            </table>
        </div>
    </div>
</div>

<%--<td class="smalltable"><i class="fa fa-info"></i></td>
<td class="smalltable"><i class="fa fa-bitbucket"></i></td>
<td class="smalltable"><i class="fa fa-edit"></i></td>--%>


<div class="col-lg-6">
    <div class="box dark">
        <header>
            <div class="icons">
                <i class="fa fa-edit"></i>
            </div>
            <h5>Add New User</h5>

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

            <form class="form-horizontal">
                <div class="form-group">
                    <label for="au-first_name" class="control-label col-lg-4">First Name</label>

                    <div class="col-lg-8">
                        <input type="text" name="first_name" id="au-first_name" placeholder="First Name"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-last_name" class="control-label col-lg-4">Last Name</label>

                    <div class="col-lg-8">
                        <input type="text" name="last_name" id="au-last_name" placeholder="Last Name"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-email" class="control-label col-lg-4">Email</label>

                    <div class="col-lg-8">
                        <input type="email" name="email" id="au-email" placeholder="sample@sample.com"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-email2" class="control-label col-lg-4">Confirm Email</label>

                    <div class="col-lg-8">
                        <input type="text" name="emailNull" id="au-email2" placeholder="sample@sample.com"
                               class="validate[required],equals[au-email]] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-pass1" class="control-label col-lg-4">Password</label>

                    <div class="col-lg-8">
                        <input class="form-control" type="password" name="password" id="au-pass1"
                               data-original-title="Please use your secure password" data-placement="top">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-pass2" class="control-label col-lg-4">Confirm Password</label>

                    <div class=" col-lg-8">
                        <input class="validate[required,equals[au-pass1]] form-control" type="password" name="passwordNull"
                               id="au-pass2">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-pesel" class="control-label col-lg-4">PESEL</label>

                    <div class="col-lg-8">
                        <input type="text" name="pesel" id="au-pesel" class="maxSizep[11] ,custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-nip" class="control-label col-lg-4">NIP</label>

                    <div class="col-lg-8">
                        <input type="text" name="nip" id="au-nip" class="maxSizep[10] ,custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-phone_number" class="control-label col-lg-4">Phone</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input name="phone_number" id="au-phone_number" class="form-control" type="text" data-mask="+48 999 999 999">
                            <span class="input-group-addon">+48 999 999 999</span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="au-city" class="control-label col-lg-4">City</label>

                    <div class="col-lg-8">
                        <input type="text" name="city" id="au-city" placeholder="eg: Kraków" class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-street" class="control-label col-lg-4">Street</label>

                    <div class="col-lg-8">
                        <input type="text" name="street" id="au-street" placeholder="eg: Zakopianska"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-building_no" class="control-label col-lg-4">Building No.</label>

                    <div class="col-lg-8">
                        <input type="text" name="building_no" id="au-building_no" placeholder="eg: 10"
                               class="validate[required], custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-apartment_no" class="control-label col-lg-4">Apartment No.</label>

                    <div class="col-lg-8">
                        <input type="text" name="apartment_no" id="au-apartment_no" placeholder="eg: 10"
                               class="validate[required], custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-postcode" class="control-label col-lg-4">Post code</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input type="text" id="au-postcode" class="validate[required] form-control" data-mask="99-999">
                            <span class="input-group-addon">32-600</span>
                        </div>
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="au-select-type" class="control-label col-lg-4">Type</label>

                    <div class="col-lg-8">
                        <select name="type" id="au-select-type" class="form-control">
                            <option>ADMIN</option>
                            <option>OWNER</option>
                            <option>EMPLOYEE</option>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-lg-4">Add User</label>

                    <div class="col-lg-8">
                        <div class="input-group">

                            <input type="submit" name="submit"
                                   onclick="ajaxSubmit('user-add', 'server-messages'); return false;"
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


<script src="./assets/js/user.js"></script>