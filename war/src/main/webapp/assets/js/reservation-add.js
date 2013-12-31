createTableWithDataFromDB('client-getData-small', 'client-table-small', {
    aoColumns: [
        { "sTitle": "Id" },
        { "sTitle": "Name" },
        { "sTitle": "Email" },
        { "sTitle": "Phone" }

    ]
});

createSelectListWithDataFromDB('client-getData', 'reservation-client-select', {
    label: 1,
    value: 0,
    defaultSelected: 0
});

createSelectListWithDataFromDB('status-getData', 'reservation-status-select', {
    label: 1,
    value: 0,
    defaultSelected: 0
});