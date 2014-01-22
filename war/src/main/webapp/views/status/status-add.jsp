<div class="box dark">
    <header>
        <div class="icons">
            <i class="fa fa-edit"></i>
        </div>
        <h5>Add New Status</h5>

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

    <div id="div-1" class="accordion-body collapse in body">
        <form id="status-add" action="status-add" class="form-horizontal">

            <div class="form-group">
                <label class="control-label col-lg-4">Name</label>

                <div class="col-lg-8">
                    <input type="text" name="type" placeholder="Type" class="form-control">
                </div>
            </div>
            <!-- /.form-group -->

            <div class="form-group">
                <label class="control-label col-lg-4">Description</label>

                <div class="col-lg-8">
                    <textarea name="description" placeholder="Description" class="form-control"></textarea>
                </div>
            </div>
            <!-- /.row -->

            <div class="form-group">
                <label class="control-label col-lg-4">Select Color</label>

                <div class="col-lg-3">
                    <input type="color" name="color" class="form-control">
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
                <label class="control-label col-lg-4">Add Status</label>

                <div class="col-lg-8">
                    <div class="input-group">
                        <input type="submit" name="submit"
                               onclick="ajaxSubmit('status-add', 'server-messages'); return false;"
                               class="btn btn-primary" data-original-title="" title="" value="Add"/>
                    </div>
                </div>
                </div>
        </form>
    </div>
    </div>


<script src="./assets/js/status.js"></script>
<script>
    $(".chzn-select").chosen();
</script>