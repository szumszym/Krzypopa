App.Views.Addition.Browse = (function (Table) {
    Table.create({
        actions: {
            get: 'client-getData',
            edit: 'client-edit',
            update: 'client-update',
            delete: 'client-delete'
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
                ],
                editColumn: 8,
                deleteColumn: 9
            }
        },
        edit: {
            title: "Client edit",
            url: './views/client/edit.jsp'
        }
    });
    Table.create({
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
})(App.Components.Table);
