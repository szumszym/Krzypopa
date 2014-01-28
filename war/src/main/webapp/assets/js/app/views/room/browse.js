App.Views.Room.Browse = (function (Table) {
    App.Utils.refresh(3000,
        [
            {
                fn: Table.create,
                arg: [
                    {
                        actions: {
                            get: 'room-getData',
                            edit: 'room-edit',
                            update: 'room-update',
                            delete: 'room-delete'
                        },
                        table: {
                            id: 'room-table',
                            params: {
                                aoColumns: [
                                    { "sTitle": "Id" },
                                    { "sTitle": "Room No" },
                                    { "sTitle": "Name" },
                                    { "sTitle": "Bed" },
                                    { "sTitle": "Capacity"},
                                    { "sTitle": "Additions" },
                                    { "sTitle": "Desc" },
                                    { "sTitle": "Price: Room + Additions" },
                                    { "sTitle": "Price Total" },
                                    null

                                ],
                                aoColumnDefs: [
                                    {   sTitle: "Published",
                                        aTargets: [9],
                                        mData: null,
                                        "sWidth": "120px",
                                        "sClass": "text-center",
                                        mRender: function (data, type, full) {

                                            if (full[9] == "true") {
                                                return '<i class="fa fa-check fa-2"></i>';
                                            } else {
                                                return '<i class="fa fa-times fa-2"></i>';
                                            }
                                        }
                                    }
                                ],
                                editColumn: 11,
                                deleteColumn: 12
                            }
                        },
                        edit: {
                            title: "Room edit",
                            url: './views/room/edit.jsp'
                        }
                    }
                ]
            }
        ]
    );
})(App.Components.Table);
