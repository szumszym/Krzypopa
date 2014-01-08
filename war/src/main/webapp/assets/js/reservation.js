createTableWithDataFromDB({
    actions: {
        get: 'reservation-getData'
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
                { "sTitle": "Price" },
                { "sTitle": "Status" },
                { "sTitle": "Entry Date" },
                { "sTitle": "Update Date", sDefaultContent: "-"}
            ],
            infoColumn: 10,
            editColumn: 11,
            deleteColumn: 12
        }
    }
});