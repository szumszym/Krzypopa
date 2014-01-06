createTableWithDataFromDB({
    actions: {
        get: 'additions-getData'
    },
    table: {
        id: 'additions-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                // { "sTitle": "Price" },
                { "sTitle": "Description" },
                { "sTitle": "Published" }
            ]
        }
    }
});