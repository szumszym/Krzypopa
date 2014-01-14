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
                { "sTitle": "Status" },
                { "sTitle": "Entry Date" },
                { "sTitle": "Update Date" },
                { "sTitle": "Price"},
                { "sTitle": "User Email" },
                { "sTitle": "Room Ids" },
                { "sTitle": "Hotel Id" }
            ],
            infoColumn: 13,
            editColumn: 14,
            deleteColumn: 15
        }
    }
});