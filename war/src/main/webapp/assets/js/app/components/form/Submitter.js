App.Components.Form.Submitter = (function (Alert, Modal, FormUtils) {
    var _default = {
        alert: {
            messages: {
                error: {
                    save: 'Wystąpił bład podczas zapisu do bazy danych!',
                    js: 'JavaScript ERROR: ',
                    add: 'Error occured during add action!',
                    invalidForm: 'Błędnie wypełniony formularz!'
                },
                warning: {

                },
                success: 'Operacja przbiegla pomyślnie'
            }
        },
        modal: {
            firstHotel: {
                id: 'modal-first-hotel',
                title: 'Welcome!',
                body: ' Congratulations!' +
                    '<br>' +
                    'You\'ve just successfully added your first hotel! ' +
                    '<br>' +
                    'Click [OK] to log in again.'

            }
        }
    };

    return {
        submit: function (formId, resultContainerId) {
            var $form = jQuery('#' + formId);
            var $submitBtn = $form.find('[name="submit"]');
            $submitBtn.on('click', function () {
                var $resultContainer = jQuery('#' + resultContainerId);
                if ($form.valid()) {
                    var send = FormUtils.formToJSON(formId);
                    var formAction = $form.attr('action');
                    var dataFromForm = {dataFrom: send};
                    jQuery.ajax({
                        url: formAction,
                        type: 'POST',
                        data: dataFromForm,
                        dataType: 'json',
                        error: function () {
                            Alert.showError($resultContainer, _default.alert.messages.error.save);
                        },
                        success: function (msg) {
                            try {
                                if (msg.data[0][0] == 'success') {
                                    Alert.showSuccess($resultContainer, _default.alert.messages.success);
                                } else if (msg.data[0][0] == 'overlapped') {
                                    Alert.showWarning($resultContainer, msg.data[0][1], 8000);
                                } else {
                                    Alert.showError($resultContainer, _default.alert.messages.error.add);
                                }
                            } catch (error) {
                                Alert.showError($resultContainer, _default.alert.messages.error.js + error);
                            }
                        }
                    });
                } else {
                    Alert.showError($resultContainer, _default.alert.messages.error.invalidForm)
                }

                return false;
            });

        },
        submitFirstHotel: function (formId, resultContainerId) {
            var $form = jQuery('#' + formId);
            var $submitBtn = $form.find('[name="submit"]');
            $submitBtn.on('click', function () {
                var $resultContainer = jQuery('#' + resultContainerId);
                if ($form.valid()) {
                    var send = FormUtils.formToJSON(formId);
                    var formAction = $form.attr('action');
                    var dataFromForm = {dataFrom: send};
                    jQuery.ajax({
                        url: formAction,
                        type: 'POST',
                        data: dataFromForm,
                        dataType: 'json',
                        error: function () {
                            Alert.showError($resultContainer, _default.alert.messages.error.save);
                        },
                        success: function (msg) {
                            try {
                                if (msg.data[0][0] == 'success') {
                                    Modal.generate(_default.modal.firstHotel.id, _default.modal.firstHotel.title,
                                        _default.modal.firstHotel.body
                                        , '/bookingsystem/logout', true);
                                } else {
                                    Alert.showError($resultContainer, _default.alert.messages.error.add);
                                }
                            } catch (error) {
                                Alert.showError($resultContainer, _default.alert.messages.error.js + error);
                            }
                        }
                    });
                } else {
                    Alert.showError($resultContainer, _default.alert.messages.error.invalidForm)
                }

                return false;
            });
        }
    }

})(App.Components.Generator.Alert, App.Components.Generator.Modal, App.Components.Form.Utils);