createTableWithDataFromDB({
    actions: {
        get: 'hotel-getData',
        edit: 'hotel-edit',
        delete: 'hotel-delete'
    },
    table: {
        id: 'hotel-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "City" },
                { "sTitle": "Street" },
                { "sTitle": "Phone" },
                { "sTitle": "Email" }
                /*{ "sTitle": "Owner" }*/
            ],
            infoColumn: 7, //TODO: np. wyswietlenie mapki
            editColumn: 8,
            deleteColumn: 9

        }
    }
});

formValidate('hotel-add', {

    name: {
        required: true,
        rangelength: [2,255],
        letter: true
    },
    email: {
        required: true,
        email: true
    },
    /*    emailNull: {
     required: true,
     equalTo: "#au-email-1"
     },*/


    phone_number: {
        rangelength: [9,15],
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

    postcode:{
        required: true,
        rangelength: [2, 16],
        accept: "[0-9 -]+"

    },

    country: {
        required: true
    }
});


createSelectListWithDataFromDB('owner-getData', 'hotel-owner-select', {
    label: 1,
    value: 0
});