createTableWithDataFromDB({
    actions: {
        get: 'room-getData'
    },
    table: {
        id: 'room-table',
        params: {
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
        }
    }
});