App.Views.User.Browse = (function (Table) {
    Table.create({
        actions: {
            get: 'user-getData',
            edit: 'user-edit',
            delete: 'user-delete'
        },
        table: {
            id: 'user-table',
            params: {
                aoColumns: [
                    { "sTitle": "Id" },
                    { "sTitle": "Name" },
                    { "sTitle": "Email" },
                    { "sTitle": "Phone" },
                    { "sTitle": "Type" },
                    { "sTitle": "Hotel" }
                ],
                editColumn: 7,
                deleteColumn: 8
            }
        }
    });
})(App.Components.Table);
