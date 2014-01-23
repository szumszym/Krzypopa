App.Views.Status.Browse = (function (Table) {
    Table.create({
        actions: {
            get: 'status-getData',
            edit: 'status-edit',
            delete: 'status-delete'
        },
        table: {
            id: 'status-table',
            params: {
                aoColumns: [
                    { "sTitle": "Id" },
                    { "sTitle": "Name" },
                    { "sTitle": "Description" },
                    null
                ],
                aoColumnDefs: [
                    {   sTitle: "Color",
                        aTargets: [3],
                        mData: null,
                        mRender: function (data, type, full) {
                            return '<div class="status-color" style="background: ' + full[3] + '"></div>';
                        }
                    }
                ],
                editColumn: 5,
                deleteColumn: 6
            }
        }
    });
})(App.Components.Table);
