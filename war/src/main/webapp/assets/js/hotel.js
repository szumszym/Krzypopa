createTableWithDataFromDB({
    actions: {
        get: 'hotel-getData'
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
                { "sTitle": "Email" },
                { "sTitle": "Owner" }
            ]
        }
    }
});

formValidate('hotel-add', {

    name: {
        required: true,
        rangelength: [2,255],
        accept: "[a-zA-Z ążźćśóęłĘÓĄŚŁŻŹŃń-]+"    //only letters and digits
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
        accept: "[0-9 -+()]+"
    },

    city: {
        required: true,
        accept: "[a-zA-z -]+"

    },
    description: {
        required: true,
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń,.-]+"
    },

    street: {
        required: true,
        accept: "[a-zA-z -]+"

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