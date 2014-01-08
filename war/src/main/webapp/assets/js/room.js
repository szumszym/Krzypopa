createTableWithDataFromDB({
    actions: {
        get: 'room-getData'
    },
    table: {
        id: 'room-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Room No" },
                { "sTitle": "Name" },
                { "sTitle": "Price" },
                { "sTitle": "Additions" },
                { "sTitle": "Desc" }
            ],
            infoColumn: 7,
            deleteColumn: 8
        }
    }
});

createTableWithDataFromDB({
    actions: {
        get: 'hotel-getData'
    },
    table: {
        id: 'hotel-table-small',
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

createTableWithDataFromDB({
    actions: {
        get: 'additions-getData'
//        edit: 'room-edit',
//        delete: 'room-delete'
    },
    table: {
        id: 'addition-table-small',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Name" },
                { "sTitle": "Description" },
                { "sTitle": "Published" }
            ]
        }
    }
});


createSelectListWithDataFromDB('additions-getData', 'room-addition-select', {
    label: 1,
    value: 0,
    multiSelect: true
});

createSelectListWithDataFromDB('hotel-getData', 'room-hotel-select', {
    label: 1,
    value: 0,
    defaultSelected: 0
});

formValidate('room-add', {
    room_name: {
        required: true,
        accept: "[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń]+"    //only letters and digits
    },
    description: {
        required: true
    },
    roomno: {
        required: true,
        digits: true
    },
    capacity: {
        required: true,
        digits: true,
        range: [1, 99]
    },
    addition: {

    },
    price: {
        required: true,
        accept: "[0-9 .]+",
        number: true
    },

    hotel: {
        required: true
    }
});