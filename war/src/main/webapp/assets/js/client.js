createTableWithDataFromDB('client-getData', 'client-table', {
    columns: [
        { "sTitle": "Id" },
        { "sTitle": "Name" },
        { "sTitle": "Email" },
        { "sTitle": "Phone" },
        { "sTitle": "City" },
        { "sTitle": "Street" },
        { "sTitle": "Country" }
    ]
});

createTableWithDataFromDB('client-getData-small', 'client-table-small', {
    columns: [
        { "sTitle": "Id" },
        { "sTitle": "Name" },
        { "sTitle": "Email" },
        { "sTitle": "Phone" }

    ]
});
