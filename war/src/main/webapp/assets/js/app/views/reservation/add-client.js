App.Views.Reservation.AddClient = (function (Table, Validator, Submitter, Select, Binder, Room) {

    Table.create({
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
                    { "sTitle": "Price: Room + Additions" },
                    { "sTitle": "Price Total" }
                ]
            }
        }
    });

    Select.create('room-getData', 'reservation-room-select', {
        label: 2,
        value: 0,
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

    Submitter.submit('reservation-add', 'server-messages');

    Binder.bindSelectTable('room-table-small', 'reservation-room-select', true, ["5"]);

    Room.checkCapacity('room-table-small', 'reservation-add');

})(App.Components.Table, App.Components.Form.Validator, App.Components.Form.Submitter, App.Components.Select, App.Components.Binder, App.Models.Room);


