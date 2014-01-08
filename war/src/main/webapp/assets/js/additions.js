createTableWithDataFromDB({
    actions: {
        get: 'additions-getData'
    },
    table: {
        id: 'additions-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Description" },
                { "sTitle": "Price" },
                { "sTitle": "Published" }
            ],
            infoColumn: 6,
            editColumn: 7,
            deleteColumn: 8
        }
    }
});

formValidate('additions-add', {
    name: {
        required: true,
        maxlength: 50,
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń-]+"    //only letters and digits
    },

    description: {
        required: true,
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń,.-]+"
    },

    price: {
        required: true,
        accept: "[0-9 .]+",
        number: true
    }
});