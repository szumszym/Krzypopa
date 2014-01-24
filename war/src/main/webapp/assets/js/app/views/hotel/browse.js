App.Views.Hotel.Browse = (function (Table) {
    Table.create({
        actions: {
            get: 'hotel-getData',
            edit: 'hotel-edit',
            update: 'hotel-update',
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
        },
        edit: {
            title: "Hotel edit",
            url: './views/hotel/edit.jsp'
        }
    });
})(App.Components.Table);
