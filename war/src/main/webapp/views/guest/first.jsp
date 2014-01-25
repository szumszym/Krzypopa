<div id="step-first" class="container col-lg-8" style=" margin: 0 auto; float: none;">
    <div class="panel panel-default">
        <div class="panel-body">
            <strong>Input Requested data </strong>
        </div>
        <div class="panel-footer">
            <form id="step-first-form" class="form-horizontal" action="firstStep">
                <div class="form-group">
                    <label class="control-label col-lg-4">City</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input name="city" type="text" class=" form-control" placeholder="city">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-4">Date from</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input id="dateFrom" type="date" name="date_from" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-4">Date to</label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input name="date_to" type="date" class="form-control">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-4"></label>

                    <div class="col-lg-8">
                        <div class="input-group">
                            <input type="submit" name="submit" class="btn btn-lg btn-success" data-original-title=""
                                   title="" value="Continue"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="./assets/js/app/views/guest/step-first.js"></script>
<script>
    $(".chzn-select").chosen();
</script>