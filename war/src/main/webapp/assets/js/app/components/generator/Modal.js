App.Components.Generator.Modal = (function ($) {
    var _default = {

    };

    return {
        generate: function (modalId, titleHTML, messageHTML, action, isShown) {
            $('body').append('<div id="' + modalId + '" class="modal fade" style="z-index: 9999;">' +
                '<div class="modal-dialog">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<h4 class="modal-title">' +
                titleHTML +
                '</h4>' +
                '</div>' +
                '<div class="modal-body">' +
                '<p>' +
                messageHTML +
                '</p>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<a href="' + action + '" class="btn btn-primary">Ok</a>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>');

            if (isShown) {
                $('#' + modalId).modal('show');
            }
        }
    }

})(jQuery);
