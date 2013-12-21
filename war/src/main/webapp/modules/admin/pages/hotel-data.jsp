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
                    <th>Adress
                        <i class="icon-sort"></i>
                        <i class="icon-sort-down"></i>
                        <i class="icon-sort-up"></i>
                    </th>
                    <th>Email
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
                    <td>CZArna Górka</td>
                    <td>Warszawa, ul.Wiejska 1</td>
                    <td>Rosomak@wpier.pl</td>
                    <td>966 966 098</td>
                    <td class="smalltable"><i class="fa fa-info"></i></td>
                    <td class="smalltable"><i class="fa fa-bitbucket"></i></td>
                    <td class="smalltable"><i class="fa fa-edit"></i></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Wybidołek</td>
                    <td>Kraków, ul.Kwiecista 12</td>
                    <td>Roman@pije.pl</td>
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

            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-lg-4">Name</label>

                    <div class="col-lg-8">
                        <input type="text" id="name" placeholder="Name"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Email</label>

                    <div class="col-lg-8">
                        <input type="text" id="email" placeholder="sample@sample.com"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Phone</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input id="phone_number" class="form-control" type="text"
                                   data-mask="+48 999 999 999">
                            <span class="input-group-addon">+48 999 999 999</span>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-4">City</label>

                    <div class="col-lg-8">
                        <input type="text" id="city" placeholder="eg: Kraków"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Street</label>

                    <div class="col-lg-8">
                        <input type="text" id="steet" placeholder="eg: Zakopianska"
                               class="validate[required] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Building No.</label>

                    <div class="col-lg-8">
                        <input type="text" id="building_no" placeholder="eg: 10"
                               class="validate[required], custom[number] form-control">
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Post code</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input type="text" id="postcode" class="validate[required] form-control"
                                   data-mask="99-999">
                            <span class="input-group-addon">32-600</span>
                        </div>
                    </div>
                </div>
                <!-- /.form-group -->

                <div class="form-group">
                    <label for="description" class="control-label col-lg-4">Description</label>

                    <div class="col-lg-8">
                        <textarea id="description" placeholder="Description"
                                  class="validate[required] form-control"></textarea>
                    </div>
                </div>
                <!-- /.row -->

                <div class="form-group">
                    <label class="control-label col-lg-4">Add Hotel</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <a href="#" class="btn btn-primary" data-original-title=""
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