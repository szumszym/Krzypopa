App.Components.Select = (function ($) {
    var _default = {

    };
    return {
        create: function (ajaxAction, selectContainerId, selectParams, isHidden) {
            var params = selectParams;
            $.ajax({
                type: 'POST',
                url: ajaxAction,
                success: function (data) {
                    var aaData;
                    try {
                        aaData = eval(data).data;
                    } catch (Error) {
                        $('#' + tableContainerId).html("No data!");
                        return;
                    }

                    var $selectElem = $('#' + selectContainerId);

                    if (aaData != undefined && aaData[0][0] != "ERROR!!!") {
                        var HTMLtemplate = "";

                        for (var i = 0; i < aaData.length; i++) {
                            var elemData = aaData[i];
                            if (params.defaultSelected != undefined && i == params.defaultSelected) {
                                HTMLtemplate += "<option selected='selected' value='" + elemData[params.value] + "'>" + elemData[params.label] + "</option>";
                            } else {
                                HTMLtemplate += "<option value='" + elemData[params.value] + "'>" + elemData[params.label] + "</option>";
                            }

                        }
                        if ($selectElem.children().length > 0) {
                            $selectElem.children().remove();
                        }
                        $selectElem.append(HTMLtemplate);
                        if (selectParams.multiSelect) {
                            $selectElem.attr('multiple', '')
                        }
                        $selectElem.chosen();

                        if (isHidden) {
                            $selectElem.closest('.form-group').hide();
                        }

                    } else {
                        $selectElem.html("Server ERROR!");
                        console.log("Error: ", data);
                    }
                },
                error: function (data) {
                    console.log("Error: ", data);
                }});
        }
    }

})(jQuery);