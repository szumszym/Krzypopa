    <div class="row">
        <div class="col-lg-6">
            <div class="box dark">
                <header>
                    <div class="icons">
                        <i class="fa fa-table"></i>
                    </div>
                    <h5>Price List</h5>

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

                    <table id="price-table"
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
                    <h5>Add New Price</h5>

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
                        <form class="form-horizontal" id="price-add" action="price-add">

                            <div class="form-group">
                                <label class="control-label col-lg-4">Room Type</label>

                                <div class="col-lg-8">
                                    <input type="text" name="type" placeholder="Type"
                                           class="form-control">
                                </div>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-label col-lg-4">Person Type</label>

                                <div class="col-lg-8">
                                    <select name="for" class="form-control chzn-select">
                                        <option>Adult</option>
                                        <option>Kid</option>
                                        <option>Pet</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-lg-4">price</label>

                                <div class="col-lg-8">
                                    <input name="price" type="text" class="form-control">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-lg-4">Add Price</label>

                                <div class="col-lg-8">
                                    <div class="input-group">
                                        <input type="submit" name="submit"
                                               onclick="ajaxSubmit('price-add', 'server-messages'); return false;"
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
<script src="./assets/js/price.js"></script>
    <script>
        $(".chzn-select").chosen();
    </script>

