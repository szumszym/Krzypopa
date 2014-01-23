App.Views.User.Add = (function (Validator, Select, Submitter, FormUtils) {
    Select.create('hotel-getList-small', 'employee-hotel-select', {
        label: 1,
        value: 0
    }, true);

    FormUtils.showFieldIfSelectedValEquals('EMPLOYEE', 'user-add', 'user-add-select-type', 'employee-hotel-select');

    Validator.validate('user-add', {
        first_name: {
            required: true,
            rangelength: [2, 25],
            letter: true
        },
        last_name: {
            required: true,
            rangelength: [2, 50],
            letter: true
        },
        email: {
            required: true,
            email: true
        },
        emailNull: {
            required: true,
            equalTo: "#au-email-1"
        },
        password: {
            required: true,
            rangelength: [6, 50]

        },
        passwordNull: {
            required: true,
            rangelength: [6, 50],
            equalTo: "#au-pass-1"
        },

        pesel: {
            required: true,
            rangelength: [6, 11],
            digits: true
        },

        nip: {
            rangelength: [6, 10],
            digits: true
        },

        phone_number: {
            rangelength: [9, 15],
            phone: true
        },

        city: {
            required: true,
            letter: true

        },

        street: {
            required: true,
            letter: true

        },

        building_no: {
            required: true,
            letter_and_digit: true
        },

        apartment_no: {
            digits: true
        },

        postcode: {
            required: true,
            rangelength: [2, 16],
            accept: "[0-9 -]+"

        },

        country: {
            required: true
        },
        type: {
            required: true
        }
    });

    Submitter.submit('user-add', 'server-messages');

})(App.Components.Form.Validator, App.Components.Select, App.Components.Form.Submitter, App.Components.Form.Utils);
