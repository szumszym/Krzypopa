formValidate('client-add-guest', {
    g_first_name: {
        required: true,
        rangelength: [2, 25],
        letter: true
    },
    g_last_name: {
        required: true,
        rangelength: [2,50],
        letter: true
    },
    g_email: {
        required: true,
        email: true
    },
    g_emailNull: {
        required: true,
        equalTo: "#gu-email"
    },
    g_password: {
        required: true,
        rangelength: [6,50]

    },
    g_passwordNull: {
        required: true,
        rangelength: [6,50],
        equalTo: "#gu-pass"
    },

    g_pesel: {
        required: true,
        rangelength: [6,11],
        digits: true
    },

    g_nip: {
        rangelength: [6,10],
        digits: true
    },

    g_phone_number: {
        rangelength: [9,15],
        phone: true
    },

    g_city: {
        required: true,
        letter: true

    },

    g_street: {
        required: true,
        letter: true

    },

    g_building_no: {
        required: true,
        digits: true
    },

    g_apartment_no: {
        digits: true
    },

    g_postcode:{
        required: true,
        rangelength: [2, 16],
        accept: "[0-9 -]+"

    },

    g_country: {
        required: true
    }
});