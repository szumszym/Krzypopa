App.Views.Hotel.Select = (function (Table, Binder, Hotel) {
    Table.create({
        actions: {
            get: 'hotel-getData',
            edit: 'hotel-edit',
            delete: 'hotel-delete'
        },
        table: {
            id: 'hotel-select-table',
            params: {
                aoColumns: [
                    { "sTitle": "Id" },
                    { "sTitle": "Name" },
                    { "sTitle": "City" },
                    { "sTitle": "Street" },
                    { "sTitle": "Phone" },
                    { "sTitle": "Email" }
                ]
            }
        }
    });
    Table.onRowSelected('hotel-select-table', Hotel.load, {
        action: 'owner/select-hotel',
        resultContainerId: 'server-messages',
        hotelNameContainerId: 'selected-hotel-name'
    });
    Binder.bindSelectTable('hotel-select-table');
})(App.Components.Table, App.Components.Binder, App.Models.Hotel);


