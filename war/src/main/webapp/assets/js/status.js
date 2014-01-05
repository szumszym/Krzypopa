createTableWithDataFromDB({
    actions: {
        get: 'status-getData'
    },
    table: {
        id: 'status-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Description" },
                { "sTitle": "Color" },
                { "sTitle": "Publish" }
            ]
        }
    }
});