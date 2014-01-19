createTableWithDataFromDB({
    actions: {
        get: 'hotel-getData-client'
    },
    table: {
        id: 'hotel-table-small',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "City" },
                { "sTitle": "Street" },
                { "sTitle": "Phone" }
            ],
            infoColumn: 6 //TODO: np. wyswietlenie mapki
        }
    }
});

formValidate('reservation-add', {
    name: {
        required: true,
        letter_and_digit: true
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

bindSelectTable('hotel-table-small');
callFnOnSelectRow('hotel-table-small', createTableWithDataFromDB, [{
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
}], bindSelectTable, ['room-table-small', 'reservation-room-select', true, "5"]);
