App.Views.Client.Edit = (function (Validator) {

    Validator.validate('client-edit', {
        c_first_name: {
            required: true,
            rangelength: [2, 25],
            letter: true
        },
        c_last_name: {
            required: true,
            rangelength: [2, 50],
            letter: true
        },
        c_email: {
            required: true,
            email: true
        },
        c_emailNull: {
            required: true,
            equalTo: "#ca-email"
        },
        c_password: {
            required: true,
            rangelength: [6, 50]

        },
        c_passwordNull: {
            required: true,
            rangelength: [6, 50],
            equalTo: "#ca-pass"
        },

        c_pesel: {
            required: true,
            rangelength: [6, 11],
            digits: true
        },

        c_nip: {
            rangelength: [6, 10],
            digits: true
        },

        c_phone_number: {
            rangelength: [9, 15],
            phone: true
        },

        c_city: {
            required: true,
            letter: true

        },

        c_street: {
            required: true,
            letter: true

        },

        c_building_no: {
            required: true,
            digits: true
        },

        c_apartment_no: {
            digits: true
        },

        c_postcode: {
            required: true,
            rangelength: [2, 16],
            accept: "[0-9 -]+"

        },

        c_country: {
            required: true
        }
    });

})(App.Components.Form.Validator);