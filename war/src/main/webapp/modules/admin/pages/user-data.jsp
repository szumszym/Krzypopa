<div id="content">
<div class="outer">

<div class="inner">
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

            <table id="dataTable"
                   class="table table-bordered table-condensed sortableTable responsive-table table-striped">
                <thead>
                <tr>
                    <th class="smalltable">#
                        <i class="icon-sort"></i>
                        <i class="icon-sort-down"></i>
                        <i class="icon-sort-up"></i>
                    </th>
                    <th>Name
                        <i class="icon-sort"></i>
                        <i class="icon-sort-down"></i>
                        <i class="icon-sort-up"></i>
                    </th>
                    <th>Email
                        <i class="icon-sort"></i>
                        <i class="icon-sort-down"></i>
                        <i class="icon-sort-up"></i>
                    </th>
                    <th>Type
                        <i class="icon-sort"></i>
                        <i class="icon-sort-down"></i>
                        <i class="icon-sort-up"></i>
                    </th>
                    <th>Phone Number
                        <i class="icon-sort"></i>
                        <i class="icon-sort-down"></i>
                        <i class="icon-sort-up"></i>
                    </th>
                    <th class="smalltable"></th>
                    <th class="smalltable"></th>
                    <th class="smalltable"></th>
                </tr>
                </thead>

                <tbody>
                <tr class="smalltable">
                    <td>1</td>
                    <td>Zenon Breszka</td>
                    <td>Rosomak@wpier.pl</td>
                    <td>Admin</td>
                    <td>966 966 098</td>
                    <td class="smalltable"><i class="fa fa-info"></i></td>
                    <td class="smalltable"><i class="fa fa-bitbucket"></i></td>
                    <td class="smalltable"><i class="fa fa-edit"></i></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Roman Zachlaka</td>
                    <td>Roman@pije.pl</td>
                    <td>Worker</td>
                    <td>888 999 666</td>
                    <td class="smalltable"><i class="fa fa-info"></i></td>
                    <td class="smalltable"><i class="fa fa-bitbucket"></i></td>
                    <td class="smalltable"><i class="fa fa-edit"></i></td>
                </tbody>
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
                    <label for="first_name" class="control-label col-lg-4">First Name</label>

                    <div class="col-lg-8">
                        <input type="text" id="first_name" placeholder="First Name"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="last_name" class="control-label col-lg-4">Last Name</label>

                    <div class="col-lg-8">
                        <input type="text" id="last_name" placeholder="Last Name"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="email" class="control-label col-lg-4">Email</label>

                    <div class="col-lg-8">
                        <input type="email" id="email" placeholder="sample@sample.com"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="email2" class="control-label col-lg-4">Confirm Email</label>

                    <div class="col-lg-8">
                        <input type="text" id="email2" placeholder="sample@sample.com"
                               class="validate[required],equals[email]] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="pass1" class="control-label col-lg-4">Password</label>

                    <div class="col-lg-8">
                        <input class="form-control" type="password" id="pass1"
                               data-original-title="Please use your secure password" data-placement="top">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="pass2" class="control-label col-lg-4">Confirm Password</label>

                    <div class=" col-lg-8">
                        <input class="validate[required,equals[pass1]] form-control" type="password" name="pass2"
                               id="pass2">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="pesel" class="control-label col-lg-4">PESEL</label>

                    <div class="col-lg-8">
                        <input type="text" id="pesel" class="maxSizep[11] ,custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="nip" class="control-label col-lg-4">NIP</label>

                    <div class="col-lg-8">
                        <input type="text" id="nip" class="maxSizep[10] ,custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="phone_number" class="control-label col-lg-4">Phone</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input id="phone_number" class="form-control" type="text" data-mask="+48 999 999 999">
                            <span class="input-group-addon">+48 999 999 999</span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="city" class="control-label col-lg-4">City</label>

                    <div class="col-lg-8">
                        <input type="text" id="city" placeholder="eg: Kraków" class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="street" class="control-label col-lg-4">Street</label>

                    <div class="col-lg-8">
                        <input type="text" id="street" placeholder="eg: Zakopianska"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="building_no" class="control-label col-lg-4">Building No.</label>

                    <div class="col-lg-8">
                        <input type="text" id="building_no" placeholder="eg: 10"
                               class="validate[required], custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="apartment_no" class="control-label col-lg-4">Apartment No.</label>

                    <div class="col-lg-8">
                        <input type="text" id="apartment_no" placeholder="eg: 10"
                               class="validate[required], custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="postcode" class="control-label col-lg-4">Post code</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input type="text" id="postcode" class="validate[required] form-control" data-mask="99-999">
                            <span class="input-group-addon">32-600</span>
                        </div>
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="select-type" class="control-label col-lg-4">Type</label>

                    <div class="col-lg-8">
                        <select id="select-type" class="form-control">
                            <option>Admin</option>
                            <option>Worker</option>
                            <option>pet</option>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <label for="add-user-btn" class="control-label col-lg-4">Add User</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <a href="#" id="add-user-btn" class="btn btn-primary" data-original-title=""
                               title="">Submit</a>
                        </div>
                    </div>
                </div>
            </form>


        </div>
    </div>
</div>


</div>
<!--row End -->
</div>
<!-- end .inner -->


</div>
<!-- end .outer -->


</div>