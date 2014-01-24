App.Views.Reservation.Edit = (function (Validator, Select, Room) {

    Select.create('status-getData', 'reservation-status-select', {
        label: 1,
        value: 0,
        defaultSelected: 0
    });

    Validator.validate('reservation-edit', {
        name: {
            required: true,
            letter_and_digit: true
        },
        status_id: {
            required: true
        }
    });

})(App.Components.Form.Validator, App.Components.Select, App.Models.Room);