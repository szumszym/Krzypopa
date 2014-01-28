App.Views.Reservation.Browse = (function (Table) {
    App.Utils.refresh(3000,
        [
            {
                fn: Table.create,
                arg: [
                    {
                        actions: {
                            get: 'reservation-getData',
                            edit: 'reservation-edit',
                            update: 'reservation-update',
                            delete: 'reservation-delete'
                        },
                        table: {
                            id: 'reservation-table',
                            params: {
                                aoColumns: [
                                    { "sTitle": "Id" },
                                    { "sTitle": "Name" },
                                    { "sTitle": "From Date" },
                                    { "sTitle": "To Date" },
                                    { "sTitle": "Count of person" },
                                    null,
                                    { "sTitle": "Entry Date" },
                                    { "sTitle": "Update Date" },
                                    { "sTitle": "Price"},
                                    { "sTitle": "User Email" },
                                    { "sTitle": "Hotel" },
                                    { "sTitle": "Room No" }
                                ],
                                aoColumnDefs: [
                                    {   sTitle: "Status",
                                        aTargets: [5],
                                        mData: null,
                                        mRender: function (data, type, full) {
                                            var data2 = full[5];
                                            var sep = data2.indexOf("&");
                                            var color = data2.substring(0, sep);
                                            var type2 = data2.substring(sep + 1, data2.length);
                                            return '<div class="reservation-status-color" style="background: ' + color + '"></div>' +
                                                '<div class="reservation-status-type"  style="color: ' + color + '">' + type2 + '</div>';
                                        }
                                    }
                                ],
                                editColumn: 13,
                                deleteColumn: 14
                            }
                        },
                        edit: {
                            title: "Reservation edit",
                            url: './views/reservation/edit.jsp'
                        }
                    }
                ]
            }
        ]
    );
})(App.Components.Table);
