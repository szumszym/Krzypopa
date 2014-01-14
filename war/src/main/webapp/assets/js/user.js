createTableWithDataFromDB({
    actions: {
        get: 'user-getData',
        edit: 'user-edit',
        delete: 'user-delete'
    },
    table: {
        id: 'user-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Email" },
                { "sTitle": "Phone" },
                { "sTitle": "Type" }
            ],
            infoColumn: 6,
            editColumn: 7,
            deleteColumn: 8
        }
    }
});


formValidate('user-add', {
    first_name: {
        required: true,
        rangelength: [2, 25],
        letter: true
    },
    last_name: {
        required: true,
        rangelength: [2,50],
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
        rangelength: [6,50]

    },
    passwordNull: {
        required: true,
        rangelength: [6,50],
        equalTo: "#au-pass-1"
    },

    pesel: {
        required: true,
        rangelength: [6,11],
        digits: true
    },

    nip: {
        rangelength: [6,10],
        digits: true
    },

    phone_number: {
        rangelength: [9,15],
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

    postcode:{
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