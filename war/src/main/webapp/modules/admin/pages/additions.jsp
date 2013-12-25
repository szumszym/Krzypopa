<div id="server-messages"></div>
<div class="row">
    <div class="col-lg-6">
        <div class="box dark">
            <header>
                <div class="icons">
                    <i class="fa fa-table"></i>
                </div>
                <h5>Addition List</h5>

                <div class="toolbar">
                    <ul class="nav">
                        <li>
                            <a class="minimize-box" data-toggle="collapse" href="#div-1a">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- /.toolbar -->
            </header>

            <div id="div-1a" class="accordion-body collapse in body">

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
                        <th>Description
                            <i class="icon-sort"></i>
                            <i class="icon-sort-down"></i>
                            <i class="icon-sort-up"></i>
                        </th>

                        <th>Published
                            <i class="icon-sort"></i>
                            <i class="icon-sort-down"></i>
                            <i class="icon-sort-up"></i>
                        </th>
                        <th class="smalltable"></th>
                        <th class="smalltable"></th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr class="smalltable">
                        <td>1</td>
                        <td>Apartament dla dwojga</td>
                        <td>2 Person</td>
                        <td>YES</td>
                        <td class="smalltable"><i class="fa fa-bitbucket"></i></td>
                        <td class="smalltable"><i class="fa fa-edit"></i></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Room for entire family</td>
                        <td>4 Person</td>
                        <td>NO</td>
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
                <h5>Add New Addition</h5>

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

                <div id="div-1" class="accordion-body collapse in body">
                    <form class="form-horizontal">

                        <div class="form-group">
                            <label class="control-label col-lg-4">Name</label>

                            <div class="col-lg-8">
                                <input type="text" id="first_name" placeholder="First Name"
                                       class="validate[required] form-control">
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
                            <label class="control-label col-lg-4"></label>

                            <div class="col-lg-8">
                                <div class="checkbox">
                                    <label>
                                        <input class="uniform" type="checkbox" value="option1" checked>Published
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-4">Add Addition</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <a href="#" class="btn btn-primary" data-original-title="" title="">Submit</a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>


</div>
<!--row End -->
