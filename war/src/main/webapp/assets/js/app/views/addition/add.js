App.Views.Addition.Add = (function (Validator, Submitter, Utils) {
    Validator.validate('additions-add', {
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

    Submitter.submit('additions-add', 'server-messages');

    Utils.activeCheckbox('[name="published"]');

})(App.Components.Form.Validator, App.Components.Form.Submitter, App.Utils);
