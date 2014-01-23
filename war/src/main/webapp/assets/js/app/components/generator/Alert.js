App.Components.Generator.Alert = (function ($) {
    var _default = {
        hideTime: 2000
    };

    return {
        showSuccess: function ($resultContainer, message, time) {
            var _time = time || _default.hideTime;
            $resultContainer.html("<div class='alert alert-success fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Info!</strong> " + message + "</div>").show();
            setTimeout(function () {
                $resultContainer.hide()
            }, _time);
        },
        showError: function ($resultContainer, message, time) {
            var _time = time || _default.hideTime;
            $resultContainer.html("<div class='alert alert-danger fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Error!</strong> " + message + "</div>").show();
            setTimeout(function () {
                $resultContainer.hide();
            }, _time);
        },
        showWarning: function ($resultContainer, message, time) {
            var _time = time || _default.hideTime;
            $resultContainer.html("<div class='alert alert-warning fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Warning!</strong> " + message + "</div>").show();
            setTimeout(function () {
                $resultContainer.hide();
            }, _time);
        }
    }

})(jQuery);