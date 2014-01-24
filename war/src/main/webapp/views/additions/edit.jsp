<form class="form-horizontal" id="additions-edit">
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
        <label class="control-label col-lg-4"></label>

        <div class="col-lg-8">
            <div class="checkbox">
                <label>
                    <input name="published" class="uniform" type="checkbox" checked>Published
                </label>
            </div>
        </div>
    </div>
</form>
<script src="./assets/js/app/views/addition/edit.js"></script>