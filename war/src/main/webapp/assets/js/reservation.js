createTableWithDataFromDB({
    actions: {
        get: 'reservation-getData',
        edit: 'reservation-edit',
        delete: 'reservation-delete'
    },
    table: {
        id: 'reservation-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "From Date" },
                { "sTitle": "To Date" },
                { "sTitle": "Count of person" },
                null,
                { "sTitle": "Entry Date" },
                { "sTitle": "Update Date" },
                { "sTitle": "Price"},
                { "sTitle": "User Email" },
                { "sTitle": "Hotel" },
                { "sTitle": "Room No" }
            ],
            aoColumnDefs: [
                {   sTitle: "Status",
                    aTargets: [5],
                    mData: null,
                    mRender: function (data, type, full) {
                        var data2 = full[5];
                        var sep = data2.indexOf("&");
                        var color = data2.substring(0, sep);
                        var type2 = data2.substring(sep+1, data2.length);
                        return '<div class="reservation-status-color" style="background: ' + color + '"></div>' +
                            '<div class="reservation-status-type"  style="color: ' + color + '">'+type2+'</div>';
                    }
                }
            ],
            infoColumn: 13,
            editColumn: 14,
            deleteColumn: 15
        }
    }
});