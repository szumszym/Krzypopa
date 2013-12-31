createTableWithDataFromDB('client-getData-small', 'client-table-small', {
    aoColumns: [
        { "sTitle": "Id" },
        { "sTitle": "Name" },
        { "sTitle": "Email" },
        { "sTitle": "Phone" }

    ]
});

createTableWithDataFromDB('room-getData', 'room-table-small', {
    aoColumns: [
        { "sTitle": "Id" },
        { "sTitle": "Room No" },
        { "sTitle": "Name" },
        { "sTitle": "Bed" },
        { "sTitle": "Additions" },
        { "sTitle": "Desc" }
    ],
    infoColumn: 7
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

createSelectListWithDataFromDB('room-getData', 'reservation-room-select', {
    label: 2,
    value: 0,
    multiSelect: true
});
