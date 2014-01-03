createTableWithDataFromDB('client-getData', 'client-table', {
    aoColumns: [
        { "sTitle": "Id" },
        { "sTitle": "Name" },
        { "sTitle": "Email" },
        { "sTitle": "Phone" },
        { "sTitle": "City" },
        { "sTitle": "Street" },
        { "sTitle": "Country" }
    ]
});

createTableWithDataFromDB({get: 'client-getData-small'}, 'client-table-small', {
    aoColumns: [
        { "sTitle": "Id" },
        { "sTitle": "Name" },
        { "sTitle": "Email" },
        { "sTitle": "Phone" }

    ]
});
