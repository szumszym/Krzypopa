function ajaxSelect(selectId, resultContainerId) {
    var $select = jQuery('#' + selectId);
    var $resultContainer = jQuery('#' + resultContainerId);
    var action = $select.data('action');
    var selectedItemIndex = "1";//default: select first one
    $select.on('change', function () {
        var selectedIndex = $(this).val();
        if (selectedIndex) selectedItemIndex = selectedIndex;

        jQuery.ajax({
            url: action,
            type: 'POST',
            data: {dataFrom: "{'index':'" + selectedItemIndex + "'}"},
            dataType: "json",
            error: function (msg) {
                console.log(msg);
                generateAlertError($resultContainer, "Wystąpił błąd servera!");
            },
            success: function (msg) {
                console.log(msg);
                try {
                    if (msg.data[0][0] == "success") {
                        ajaxDefaultIncudeOnStart();
                        generateAlertSuccess($resultContainer, "Hotel wczytany.");
                    } else {
                        generateAlertError($resultContainer, "Wystąpił błąd podczas wczytywania hotelu!");
                    }
                } catch (error) {
                    generateAlertError($resultContainer, "JavaScript ERROR: " + error);
                }
            }
        });

    });
    return selectedItemIndex;
}

$(function () {


    var currentHotelIndex = ajaxSelect('owner-select-hotel', 'server-messages');
    createSelectListWithDataFromDB('hotel-getOwnerList', 'owner-select-hotel', {
        label: 1,
        value: 0
    });
});
