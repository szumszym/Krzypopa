App.Views.Guest.Third = (function (Validator, Submitter) {

    Validator.validate('step-third-form', {
        city: {
            required: true,
            maxlength: 50,
            letter: true
        },

        dateFrom: {
            required: true,
            date: true,
            later_than_today: true
        },
        dateTo: {
            required: true,
            date: true,
            later_than: ['#dateFrom']
        }
    });

    Submitter.submit('step-third-form', 'server-messages');

})(App.Components.Form.Validator, App.Components.Form.Submitter);
