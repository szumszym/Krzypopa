App.Views.Hotel.Add = (function (Validator, Submitter, Select) {
    Validator.validate('hotel-add', {

        name: {
            required: true,
            rangelength: [2, 255],
            letter: true
        },
        email: {
            required: true,
            email: true
        },
        phone_number: {
            rangelength: [9, 15],
            phone: true
        },
        city: {
            required: true,
            letter: true

        },
        description: {
            required: true,
            letter_and_digit: true
        },
        street: {
            required: true,
            letter: true

        },
        building_no: {
            required: true,
            digits: true
        },
        postcode: {
            required: true,
            rangelength: [2, 16],
            accept: "[0-9 -]+"
        },
        country: {
            required: true
        }
    });

    Select.create('owner-getData', 'hotel-owner-select', {
        label: 1,
        value: 0
    });

    Submitter.submit('hotel-add', 'server-messages');

})(App.Components.Form.Validator, App.Components.Form.Submitter, App.Components.Select);
