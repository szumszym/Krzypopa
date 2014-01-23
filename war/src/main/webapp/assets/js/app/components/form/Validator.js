App.Components.Form.Validator = (function ($) {
    var _default = {
        messages: {
            later_than_today: 'Date must be equals or later than today!',
            later_than: '"Date To" must be later than "Date From"!',
            not_more_than: 'Count of person must be <= capacity of rooms',
            accept: 'Please fix this field.',
            letter_and_digit: 'Please enter only letters and digits.',
            letter: 'Please enter only letters.',
            phone: 'Please enter a valid phone number.'
        }
    };

    var init = function () {
        jQuery.validator.addMethod("later_than_today", function (value, element, param) {
            var fDate = new Date();
            var sDate = new Date(value);
            return fDate <= sDate;

        }, jQuery.validator.format(_default.messages.later_than_today));
        jQuery.validator.addMethod("later_than", function (value, element, param) {
            var firstDate = param[0];
            var fDate = new Date($(firstDate).val());
            var sDate = new Date(value);
            return fDate < sDate;

        }, jQuery.validator.format(_default.messages.later_than));
        jQuery.validator.addMethod("not_more_than", function (value, element, param) {
            var what = param[0];
            var capacity = $(element).data(what);
            return capacity >= value;

        }, jQuery.validator.format(_default.messages.not_more_than));
        jQuery.validator.addMethod("accept", function (value, element, param) {
            return value.match(new RegExp("^" + param + "$"));
        }, jQuery.validator.format(_default.messages.accept));
        jQuery.validator.addMethod("letter_and_digit", function (value, element, param) {
            return value.match(new RegExp("^[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń,.-]+$"));
        }, jQuery.validator.format(_default.messages.letter_and_digit));
        jQuery.validator.addMethod("letter", function (value, element, param) {
            return value.match(new RegExp("^[a-zA-Z ążźćśóęłĘÓĄŚŁŻŹŃń,.-]+$"));
        }, jQuery.validator.format(_default.messages.letter));
        jQuery.validator.addMethod("phone", function (value, element, param) {
            return value.match(new RegExp("^[0-9 -+()]+$"));
        }, jQuery.validator.format(_default.messages.phone));
    };

    init();
    return {
        validate: function (formId, rules) {

            $('#' + formId).validate({
                rules: rules,
                errorClass: 'help-block col-lg-6',
                errorElement: 'span',
                onfocusout: function (element) {
                    $(element).valid();
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).parents('.form-group').removeClass('has-success').addClass('has-error');
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).parents('.form-group').removeClass('has-error').addClass('has-success');
                }
            });
        }
    }

})(jQuery);