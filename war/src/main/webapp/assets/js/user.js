createTableWithDataFromDB({
    actions: {
        get: 'user-getData'
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
            ]
        }
    }
});


formValidate('user-add', {
    first_name: {
        required: true,
        rangelength: [2, 25],
        accept: "[a-zA-Z ążźćśóęłĘÓĄŚŁŻŹŃń]+"    //only letters and digits
    },
    last_name: {
        required: true,
        rangelength: [2,50],
        accept: "[a-zA-Z ążźćśóęłĘÓĄŚŁŻŹŃń-]+"    //only letters and digits
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
        accept: "[0-9 -+()]+"
    },

    city: {
        required: true,
        accept: "[a-zA-z -]+"

    },

    street: {
        required: true,
        accept: "[a-zA-z -]+"

    },

    building_no: {
        required: true,
        digits: true
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