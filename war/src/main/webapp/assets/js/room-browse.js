createTableWithDataFromDB({
    actions: {
        get: 'room-getData',
        edit: 'room-edit',
        delete: 'room-delete'
    },
    table: {
        id: 'room-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Room No" },
                { "sTitle": "Name" },
                { "sTitle": "Bed" },
                { "sTitle": "Capacity"},
                { "sTitle": "Additions" },
                { "sTitle": "Desc" },
                { "sTitle": "Price" },
                null

            ],
            aoColumnDefs: [
                {   sTitle: "Published",
                    aTargets: [8],
                    mData: null,
                    "sWidth": "120px",
                    "sClass": "text-center",
                    mRender: function (data, type, full) {

                        if(full[8]=="true"){
                            return '<i class="fa fa-check fa-2"></i>';
                        } else {
                            return '<i class="fa fa-times fa-2"></i>';
                        }
                    }
                }
            ],
            infoColumn: 10,
            editColumn: 11,
            deleteColumn: 12
        }
    }
});