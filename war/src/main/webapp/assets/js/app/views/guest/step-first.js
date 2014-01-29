App.Views.Guest.First = (function (Validator, Submitter, Includer, Utils) {

    Validator.validate('step-first-form', {
        city: {
            required: true,
            maxlength: 50,
            letter: true
        },

        date_from: {
            required: true,
            date: true,
            later_than_today: true
        },
        date_to: {
            required: true,
            date: true,
            later_than: ['#dateFrom']
        }
    });

    Submitter.submit('step-first-form', 'server-messages', true, Includer.load, ['./views/guest/second.jsp', 'context']);

    Utils.setTodayTo('[name="date_from"]');
    Utils.setTodayTo('[name="date_to"]', 1);

})(App.Components.Form.Validator, App.Components.Form.Submitter, App.Components.Includer, App.Utils);
