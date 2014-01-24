App.Views.Room.Edit = (function (Validator, Select, Room, Utils) {

    Select.create('additions-getData', 'room-addition-select', {
        label: 1,
        value: 0,
        multiSelect: true
    });

    Validator.validate('room-edit', {
        room_name: {
            required: true,
            letter_and_digit: true
        },
        description: {
            required: true
        },
        roomno: {
            required: true,
            digits: true
        },
        bed_count: {
            required: true,
            digits: true,
            range: [0, 9]
        },
        bed_type: {
            required: true,
            digits: true,
            range: [1, 3]
        },
        capacity: {
            required: true,
            digits: true,
            range: [1, 99]
        },
        addition: {

        },
        price: {
            required: true,
            number: true
        },

        hotel: {
            required: true
        }
    });

    Room.countCapacity('room-edit');

    Utils.activeCheckbox('[name="published"]');

})(App.Components.Form.Validator, App.Components.Select, App.Models.Room, App.Utils);