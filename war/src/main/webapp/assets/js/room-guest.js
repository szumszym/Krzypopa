createTableWithDataFromDB({
    actions: {
        get: 'room-getAvadaible'
    },
    table: {
        id: 'room-table-guest',
        params: {
            aoColumns: [
                { "sTitle": "Hotel" },
                { "sTitle": "Room No" },
                { "sTitle": "Name" },
                { "sTitle": "Bed" },
                { "sTitle": "Capacity"},
                { "sTitle": "Additions" },
                { "sTitle": "Desc" },
                { "sTitle": "Price" }
            ],
            infoColumn: 9
        }
    }
});

createSelectListWithDataFromDB('room-getAvadaible', 'reservation-room-select', {
    label: 3,
    value: 8,
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
    }
});