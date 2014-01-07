createTableWithDataFromDB({
    actions: {
        get: 'status-getData'
    },
    table: {
        id: 'status-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Description" },
                { "sTitle": "Color" },
                { "sTitle": "Publish" }
            ]
        }
    }
});

formValidate('status-add', {
    typ: {
        required: true,
        maxlength: 50,
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń-]+"    //only letters and digits
    },

    description: {
        required: true,
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń.,-]+"
    }
});