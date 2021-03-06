App.Views.Addition.Browse = (function (Table) {
    App.Utils.refresh(3000,
        [
            {
                fn: Table.create,
                arg: [
                    {
                        actions: {
                            get: 'additions-getData',
                            edit: 'additions-edit',
                            update: 'additions-update',
                            delete: 'additions-delete'
                        },
                        table: {
                            id: 'additions-table',
                            params: {
                                aoColumns: [
                                    { "sTitle": "Id" },
                                    { "sTitle": "Name" },
                                    { "sTitle": "Description" },
                                    { "sTitle": "Price" },
                                    null
                                ],
                                aoColumnDefs: [
                                    {   sTitle: "Published",
                                        aTargets: [4],
                                        mData: null,
                                        "sWidth": "120px",
                                        "sClass": "text-center",
                                        mRender: function (data, type, full) {

                                            if (full[4] == "true") {
                                                return '<i class="fa fa-check fa-2"></i>';
                                            } else {
                                                return '<i class="fa fa-times fa-2"></i>';
                                            }
                                        }
                                    }
                                ],
                                editColumn: 6,
                                deleteColumn: 7
                            }
                        },
                        edit: {
                            title: 'Edit Addition',
                            url: './views/additions/edit.jsp'
                        }
                    }
                ]
            }
        ]
    );

})(App.Components.Table);

