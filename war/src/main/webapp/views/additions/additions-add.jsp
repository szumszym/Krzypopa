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
                        <input type="text" id="aa-name" name="name" placeholder="Name"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-4">Description</label>

                    <div class="col-lg-8">
                        <textarea name="description" placeholder="Description"
                                  class="form-control"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-4">Price</label>

                    <div class="col-lg-8">
                        <input name="price" type="text" class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-4">Add Addition</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input type="submit" name="submit" class="btn btn-primary" data-original-title="" title=""
                                   value="Add"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="./assets/js/app/views/addition/add.js"></script>
