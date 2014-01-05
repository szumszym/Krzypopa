createTableWithDataFromDB({
    actions: {
        get: 'price-getData'
    },
    table: {
        id: 'price-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Room type" },
                { "sTitle": "Person type" },
                { "sTitle": "Value" }
            ]
        }
    }
});