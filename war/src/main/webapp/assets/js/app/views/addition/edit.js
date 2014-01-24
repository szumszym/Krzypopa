App.Views.Addition.Edit = (function (Validator, Utils) {
    Validator.validate('additions-edit', {
        name: {
            required: true,
            maxlength: 50,
            letter_and_digit: true
        },

        description: {
            required: true,
            letter_and_digit: true
        },

        price: {
            required: true,
            number: true
        }
    });
    Utils.activeCheckbox('[name="published"]');

})(App.Components.Form.Validator, App.Utils);