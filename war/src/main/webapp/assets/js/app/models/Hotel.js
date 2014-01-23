App.Models.Hotel = (function ($, Alert, Includer) {
    var _default = {

    };

    return {
        load: function (params) {
            var selectedItemIndex = params.index;
            var hotelName = params.label;

            var action = params.action;
            var resultContainerId = params.resultContainerId;
            var hotelNameContainerId = params.hotelNameContainerId;

            var $resultContainer = $('#' + resultContainerId);
            var $hotelNameContainer = $('#' + hotelNameContainerId);

            $.ajax({
                url: action,
                type: 'POST',
                data: {dataFrom: "{'index':'" + selectedItemIndex + "'}"},
                dataType: "json",
                error: function (msg) {
                    if (msg.data[0][0] == "error") {
                        if (msg.data[0][1] != undefined && msg.data[0][1] == "THE_SAME") {
                            Alert.showError($resultContainer, "Hotel został już wybrany!");
                        }
                    }
                    console.log(msg);
                    Alert.showError($resultContainer, "Wystąpił błąd servera!");
                },
                success: function (msg) {
                    console.log(msg);
                    try {
                        if (msg.data[0][0] == "success") {
                            //Includer.includeOnStart();
                            Alert.showSuccess($resultContainer, "Hotel wczytany.");
                            $hotelNameContainer.text(hotelName);
                        } else if (msg.data[0][0] == "error") {
                            if (msg.data[0][1] != undefined && msg.data[0][1] == "THE_SAME") {
                                Alert.showWarning($resultContainer, "Hotel został już wybrany!");
                            }
                        } else {
                            Alert.showError($resultContainer, "Wystąpił błąd podczas wczytywania hotelu!");
                        }
                    } catch (error) {
                        Alert.showError($resultContainer, "JavaScript ERROR: " + error);
                    }
                }
            });

        }

    }

})(jQuery, App.Components.Generator.Alert, App.Components.Includer);