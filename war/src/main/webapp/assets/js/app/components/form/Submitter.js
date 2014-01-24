App.Components.Form.Submitter = (function (Alert, Modal, FormUtils, Includer) {
    var _default = {
        alert: {
            messages: {
                error: {
                    save: 'Wystąpił bład podczas zapisu do bazy danych!',
                    add: 'Wystąpił bład podczas dodawania do bazy danych!',
                    update: 'Wystąpił bład podczas aktualizacji bazy danych!',
                    invalidForm: 'Błędnie wypełniony formularz!',
                    js: 'JavaScript ERROR: '
                },
                warning: {

                },
                success: 'Operacja przbiegła pomyślnie'
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
                    'Click [OK] to log in again.',
                btn: 'OK'

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
        update: function (formId, action, index, resultContainerId, modalId) {
            var $form = jQuery('#' + formId);
            var $resultContainer = jQuery('#' + resultContainerId);
            if ($form.valid()) {
                var send = FormUtils.formToJSON(formId, false);
                if (index != undefined) {
                    send['index'] = index;
                }
                send = JSON.stringify(send);

                var dataFromForm = {dataFrom: send};
                jQuery.ajax({
                    url: action,
                    type: 'POST',
                    data: dataFromForm,
                    dataType: 'json',
                    error: function () {
                        Alert.showError($resultContainer, _default.alert.messages.error.update);
                    },
                    success: function (msg) {
                        try {
                            if (msg.data[0][0] == 'success') {
                                Alert.showSuccess($resultContainer, _default.alert.messages.success);
                                App.Components.Includer.refresh();
                                Modal.hide(modalId);
                            } else if (msg.data[0][0] == 'overlapped') {
                                Alert.showWarning($resultContainer, msg.data[0][1], 8000);
                            } else {
                                Alert.showError($resultContainer, _default.alert.messages.error.update);
                            }
                        } catch (error) {
                            Alert.showError($resultContainer, _default.alert.messages.error.js + error);
                        }
                    }
                });
            } else {
                Alert.showError($resultContainer, _default.alert.messages.error.invalidForm)
            }


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
                                        _default.modal.firstHotel.body, _default.modal.firstHotel.btn
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

})(App.Components.Generator.Alert, App.Components.Generator.Modal, App.Components.Form.Utils, App.Components.Includer);