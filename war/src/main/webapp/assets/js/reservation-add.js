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
        get: 'room-getData'
    },
    table: {
        id: 'room-table-small',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Room No" },
                { "sTitle": "Name" },
                { "sTitle": "Bed" },
                { "sTitle": "Capacity"},
                { "sTitle": "Additions" },
                { "sTitle": "Desc" },
                { "sTitle": "Price" }
            ],
            infoColumn: 8
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
        letter_and_digit: true
    },
    date_from: {
        required: true,
        date: true,
        later_than_today: true
    },
    date_to: {
        required: true,
        date: true,
        later_than: ['#date_from']
    },
    person_count: {
        required: true,
        digits: true,
        range: [1, 99],
        not_more_than: ['capacity']
    },
    client_id: {
        required: true
    },
    status_id: {
        required: true
    }
});
