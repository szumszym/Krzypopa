App.Views.Status.Add = (function (Validator, Submitter, Utils) {
    Validator.validate('status-add', {
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

    Submitter.submit('status-add', 'server-messages');

    Utils.activeCheckbox('[name="published"]');

})(App.Components.Form.Validator, App.Components.Form.Submitter, App.Utils);

