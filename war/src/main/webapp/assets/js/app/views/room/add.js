App.Views.Room.Add = (function (Table, Validator, Submitter, Select, Binder, Room, Utils) {

    Table.create({
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

    Select.create('additions-getData', 'room-addition-select', {
        label: 1,
        value: 0,
        multiSelect: true
    });

    Validator.validate('room-add', {
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
        bed_count: {
            required: true,
            digits: true,
            range: [0, 9]
        },
        bed_type: {
            required: true,
            digits: true,
            range: [1, 3]
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

    Submitter.submit('room-add', 'server-messages');

    Binder.bindSelectTable('addition-table-small', 'room-addition-select', true);

    Room.countCapacity('room-add');

    Utils.activeCheckbox('[name="published"]');

})(App.Components.Table, App.Components.Form.Validator, App.Components.Form.Submitter, App.Components.Select, App.Components.Binder, App.Models.Room, App.Utils);

/*createTableWithDataFromDB({
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
 { "sTitle": "Capacity"},
 { "sTitle": "Additions" },
 { "sTitle": "Desc" },
 { "sTitle": "Price" },
 null

 ],
 aoColumnDefs: [
 {   sTitle: "Published",
 aTargets: [8],
 mData: null,
 "sWidth": "120px",
 "sClass": "text-center",
 mRender: function (data, type, full) {

 if(full[8]=="true"){
 return '<i class="fa fa-check fa-2"></i>';
 } else {
 return '<i class="fa fa-times fa-2"></i>';
 }
 }
 }
 ],
 infoColumn: 10,
 editColumn: 11,
 deleteColumn: 12
 }
 }
 });*/

/*createTableWithDataFromDB({
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
 { "sTitle": "Email" }*//*,
 { "sTitle": "Owner" }*//*
 ]
 }
 }
 });*/


/*
 createSelectListWithDataFromDB('hotel-getData', 'room-hotel-select', {
 label: 1,
 value: 0,
 defaultSelected: 0
 });
 */
