createTableWithDataFromDB({
    actions: {
        get: 'hotel-getData'
    },
    table: {
        id: 'hotel-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "City" },
                { "sTitle": "Street" },
                { "sTitle": "Phone" },
                { "sTitle": "Email" },
                { "sTitle": "Owner" }
            ]
        }
    }
});