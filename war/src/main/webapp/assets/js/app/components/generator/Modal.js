App.Components.Generator.Modal = (function ($) {
    var _default = {

    };

    return {
        generate: function (modalId, titleHTML, messageHTML, btnLabel, action, isShown, withCancelBtn) {
            var cancelBtn = withCancelBtn || false;
            var $previusModal = $('#' + modalId);
            if ($previusModal.length > 0) {
                $previusModal.remove();
            }
            var cancelBtnHTML = '';
            var cancelXHTML = '';
            if (cancelBtn) {
                cancelXHTML = '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>';
                cancelBtnHTML = '<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>';
            }


            $('body').append('<div id="' + modalId + '" class="modal fade" style="z-index: 9999;">' +
                '<div class="modal-dialog">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                cancelXHTML +
                '<h4 class="modal-title">' +
                titleHTML +
                '</h4>' +
                '</div>' +
                '<div id="' + modalId + '-body" class="modal-body">' +
                '<p>' +
                messageHTML +
                '</p>' +
                '</div>' +
                '<div class="modal-footer">' +
                cancelBtnHTML +
                '<a id="' + modalId + '-btn" href="' + action + '" class="btn btn-primary">' + btnLabel + '</a>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>');

            if (isShown) {
                $('#' + modalId).modal('show');
            }
        },
        show: function (modalId) {
            $('#' + modalId).modal('show');
        },
        hide: function (modalId) {
            var $modal = $('#' + modalId);
            $modal.modal('hide');
            setTimeout(function () {
                $modal.remove();
            }, 500);
        },
        getBody: function (modalId) {
            return $('#' + modalId + '-body');
        },
        getBtn: function (modalId) {
            return $('#' + modalId + '-btn');
        }
    }

})(jQuery);
