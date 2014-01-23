App.Views.Hotel.Browse = (function (Table) {
    Table.create({
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
                ],
                editColumn: 7,
                deleteColumn: 8

            }
        }
    });
})(App.Components.Table);
