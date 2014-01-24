App.Views.Status.Edit = (function (Validator, Utils) {
    Validator.validate('status-edit', {
        type: {
            required: true,
            maxlength: 20,
            letter: true
        },

        description: {
            letter_and_digit: true,
            required: false
        }
    });
    Utils.activeCheckbox('[name="published"]');

})(App.Components.Form.Validator, App.Utils);