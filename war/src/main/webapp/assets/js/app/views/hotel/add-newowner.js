App.Views.Hotel.AddNewOwner = (function (Validator, Submitter) {
    Validator.validate('hotel-add-newowner', {

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

    Submitter.submitFirstHotel('hotel-add-newowner', 'server-messages');

})(App.Components.Form.Validator, App.Components.Form.Submitter);
