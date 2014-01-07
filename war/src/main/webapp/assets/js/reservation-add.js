createTableWithDataFromDB({
    actions: {
        get: 'client-getData-small'
    },
    table: {
        id: 'client-table-small',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Email" },
                { "sTitle": "Phone" }
            ]
        }
    }
});

createTableWithDataFromDB({
    actions: {
        get: 'room-getData',
        edit: 'room-edit',
        delete: 'room-delete'
    },
    table: {
        id: 'room-table-small',
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

createSelectListWithDataFromDB('client-getData', 'reservation-client-select', {
    label: 1,
    value: 0
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

formValidate('reservation-add', {
    name: {
        required: true,
        //rangelength: [2, 25],
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń]+"    //only letters and digits
    },
    date_from: {
        required: true,
        date: true
    },
    date_to: {
        required: true
    },
    person_count: {
        required: true,
        digits: true,
        range: [1, 99]
    },
    client_id: {
        required: true
    },
    status_id: {
        required: true
    }
});
