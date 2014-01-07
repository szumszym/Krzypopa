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

                <table id="additions-table"
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
                    <form class="form-horizontal" action="additions-add" id="additions-add">

                        <div class="form-group">
                            <label class="control-label col-lg-4">Name</label>

                            <div class="col-lg-8">
                                <input type="text" name="name" placeholder="Name"
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
                            <label class="control-label col-lg-4"></label>

                            <div class="col-lg-8">
                                <div class="checkbox">
                                    <label>
                                        <input name="publish" class="uniform" type="checkbox" value="YES" checked>Published
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-4">Add Addition</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input type="submit" name="submit"
                                           onclick="ajaxSubmit('additions-add', 'server-messages'); return false;"
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
<!--row End -->
<script src="./assets/js/additions.js"></script>
<script>
    $(".chzn-select").chosen();
</script>