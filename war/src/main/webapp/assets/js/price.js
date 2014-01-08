createTableWithDataFromDB({
    actions: {
        get: 'price-getData'
    },
    table: {
        id: 'price-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Room type" },
                { "sTitle": "Person type" },
                { "sTitle": "Value" }
            ]
        }
    }
});

formValidate('price-add', {
    typ: {
        required: true,
        maxlength: 10,
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń-]+"    //only letters and digits
    },
    for: {
        required: true
    },
    price: {
        required: true,
        accept: "[0-9 .]+",
        number: true
    }
});