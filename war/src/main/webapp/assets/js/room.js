createTableWithDataFromDB('room-getData', 'room-table', {
    aoColumns: [
        { "sTitle": "Id" },
        { "sTitle": "Room No" },
        { "sTitle": "Name" },
        { "sTitle": "Bed" },
        { "sTitle": "Additions" },
        { "sTitle": "Desc" }
    ],
    infoColumn: 7,
    deleteColumn: 8
});
