App.Views.Guest.Second = (function (Validator, Submitter, Table, Select, Binder, Room, Includer) {

    Table.create({
        actions: {
            get: 'secondStep-get-rooms'
        },
        table: {
            id: 'step-second-room-table',
            params: {
                aoColumns: [
                    { "sTitle": "Id", "sClass": "hidden" },
                    { "sTitle": "Hotel", "sClass": "center-text" },
                    { "sTitle": "Room No", "sClass": "center-text" },
                    { "sTitle": "Name", "sClass": "center-text" },
                    { "sTitle": "Bed", "sClass": "center-text" },
                    { "sTitle": "Capacity", "sClass": "center-text" },
                    { "sTitle": "Additions", "sClass": "center-text" },
                    { "sTitle": "Description", "sClass": "center-text" },
                    { "sTitle": "Price: Room + Additions", "sClass": "center-text" },
                    { "sTitle": "Price Total", "sClass": "center-text" }
                ]
            }
        }
    });

    Select.create('secondStep-get-rooms', 'step-second-room-select', {
        label: 3,
        value: 0,
        multiSelect: true
    });

    Validator.validate('step-second-form', {
        name: {
            required: true,
            maxlength: 30,
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
        room_ids: {
            required: true
        }
    });

    Submitter.submit('step-second-form', 'server-messages', Includer.load, ['./views/guest/third.jsp', 'context']);

    Binder.bindSelectTable('step-second-room-table', 'step-second-room-select', true, ["6", "10"]);

    Room.countPrice('step-second-room-table', 'step-second-form');
    Room.checkCapacity('step-second-room-table', 'step-second-form');


})(App.Components.Form.Validator, App.Components.Form.Submitter, App.Components.Table, App.Components.Select, App.Components.Binder, App.Models.Room, App.Components.Includer);
