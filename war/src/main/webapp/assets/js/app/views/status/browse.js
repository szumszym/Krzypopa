App.Views.Status.Browse = (function (Table) {
    App.Utils.refresh(3000,
        [
            {
                fn: Table.create,
                arg: [
                    {
                        actions: {
                            get: 'status-getData',
                            edit: 'status-edit',
                            update: 'status-update',
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
                        },
                        edit: {
                            title: "Status edit",
                            url: './views/status/edit.jsp'
                        }
                    }
                ]
            }
        ]
    );
})(App.Components.Table);
