createTableWithDataFromDB({
    actions: {
        get: 'user-getData'
    },
    table: {
        id: 'user-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Email" },
                { "sTitle": "Phone" },
                { "sTitle": "Type" }
            ]
        }
    }
});