createTableWithDataFromDB({
    actions: {
        get: 'room-getData',
        edit: 'room-edit',
        delete: 'room-delete'
    },
    table: {
        id: 'room-table',
        params: {
            aoColumns: [
                { "sTitle": "Id" },
                { "sTitle": "Room No" },
                { "sTitle": "Name" },
                { "sTitle": "Bed" },
                { "sTitle": "Additions" },
                { "sTitle": "Desc" },
                { "sTitle": "Price" },
                null

            ],
            aoColumnDefs: [
                {   sTitle: "Published",
                    aTargets: [7],
                    mData: null,
                    "sWidth": "120px",
                    "sClass": "text-center",
                    mRender: function (data, type, full) {

                        if(full[7]=="true"){
                            return '<i class="fa fa-check fa-2"></i>';
                        } else {
                            return '<i class="fa fa-times fa-2"></i>';
                        }
                    }
                }
            ],
            infoColumn: 9,
            editColumn: 10,
            deleteColumn: 11
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
                { "sTitle": "Email" }/*,
                 { "sTitle": "Owner" }*/
            ]
        }
    }
});

createTableWithDataFromDB({
    actions: {
        get: 'additions-getData'
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
        letter_and_digit: true
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
        number: true
    },

    hotel: {
        required: true
    }
});