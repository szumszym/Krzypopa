App.Views.User.Edit = (function (Validator) {

    Validator.validate('user-edit', {
        u_first_name: {
            required: true,
            rangelength: [2, 25],
            letter: true
        },
        u_last_name: {
            required: true,
            rangelength: [2, 50],
            letter: true
        },
        u_email: {
            required: true,
            email: true
        },
        u_emailNull: {
            required: true,
            equalTo: "#ue-email"
        },
        u_password: {
            required: true,
            rangelength: [6, 50]

        },
        u_passwordNull: {
            required: true,
            rangelength: [6, 50],
            equalTo: "#ue-pass"
        },
        u_pesel: {
            required: true,
            rangelength: [6, 11],
            digits: true
        },
        u_nip: {
            rangelength: [6, 10],
            digits: true
        },
        u_phone_number: {
            rangelength: [9, 15],
            phone: true
        },
        u_city: {
            required: true,
            letter: true

        },
        u_street: {
            required: true,
            letter: true

        },
        u_building_no: {
            required: true,
            letter_and_digit: true
        },
        u_apartment_no: {
            digits: true
        },
        u_postcode: {
            required: true,
            rangelength: [2, 16],
            accept: "[0-9 -]+"

        },
        u_country: {
            required: true
        }
    });

})(App.Components.Form.Validator);