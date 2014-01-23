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
<script src="./assets/js/app/views/user/browse.js"></script>
<script>
    $(".chzn-select").chosen();
</script>