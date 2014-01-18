<div class="box dark">
    <header>
        <div class="icons">
            <i class="fa fa-table"></i>
        </div>
        <h5>Select Hotel</h5>

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

        <table id="hotel-select-table"
               class="table table-bordered table-condensed sortableTable responsive-table table-striped">
        </table>
    </div>
</div>
<script src="./assets/js/selectHotel.js"></script>
<script>
    ajaxSelectFromTable('hotel-select-table', 'owner/select-hotel', 'server-messages', 'selected-hotel-name');
    bindSelectTable('hotel-select-table');
</script>
