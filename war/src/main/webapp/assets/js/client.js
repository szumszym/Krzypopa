createTableWithDataFromDB({
    actions: {
        get: 'client-getData'
    },
    table: {
        id: 'client-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Email" },
                { "sTitle": "Phone" },
                { "sTitle": "City" },
                { "sTitle": "Street" },
                { "sTitle": "Country" }
            ]
        }
    }
});

createTableWithDataFromDB({
    actions: {
        get: 'client-getData-small'
    },
    table: {
        id: 'client-table-small',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Email" },
                { "sTitle": "Phone" }
            ]
        }
    }
});