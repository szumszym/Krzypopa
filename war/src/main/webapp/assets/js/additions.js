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
                // { "sTitle": "Price" },
                { "sTitle": "Description" },
                { "sTitle": "Published" }
            ]
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
    }
});