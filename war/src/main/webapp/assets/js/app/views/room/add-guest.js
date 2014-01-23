App.Views.Room.AddGuest = (function (Table, Validator, Submitter, Select, Binder, Room) {

    Table.create({
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
                ]
            }
        }
    });

    Select.create('room-getAvadaible', 'reservation-room-select', {
        label: 3,
        value: 8,
        multiSelect: true
    });

    Validator.validate('reservation-add', {
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

    Submitter.submit('room-add', 'server-messages');

    Binder.bindSelectTable('room-table-guest', 'reservation-room-select-guest', true, "3");

    Room.checkCapacity('room-table-quest', 'reservation-add-guest');

})(App.Components.Table, App.Components.Form.Validator, App.Components.Form.Submitter, App.Components.Select, App.Components.Binder, App.Models.Room);
