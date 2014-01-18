createTableWithDataFromDB({
    actions: {
        get: 'hotel-getData',
        edit: 'hotel-edit',
        delete: 'hotel-delete'
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
                { "sTitle": "Email" }
                /*{ "sTitle": "Owner" }*/
            ],
            infoColumn: 7, //TODO: np. wyswietlenie mapki
            editColumn: 8,
            deleteColumn: 9

        }
    }
});