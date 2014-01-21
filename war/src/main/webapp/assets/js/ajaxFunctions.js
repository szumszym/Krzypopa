jQuery(function () {
    ajaxInclude();
    ajaxDefaultIncudeOnStart();
});
function ajaxInclude() {
    var menuSelectors = '#menu, #top-menu';
    jQuery(menuSelectors).on('click', 'a[data-url]', function () {
        var url = jQuery(this).data('url');
        var placement = jQuery(this).data('placement');
        jQuery(placement).load(url);
    });
}
function ajaxDefaultIncudeOnStart() {
    var $contextElem = jQuery('div[data-default]');
    if ($contextElem.length) {
        var contextId = $contextElem.attr('id');
        var defaultContext = $contextElem.data('default');
        jQuery("#" + contextId).load(defaultContext);
    }
}


function createTableWithDataFromDB(params) {
    var ajaxActions = params.actions;
    var tableContainerId = params.table.id;
    var tableParams = params.table.params;
    var paramToJAVA = params.toJAVA;
    var dataTableParams = [];
    $.ajax({
        type: 'POST',
        url: ajaxActions.get,
        data: {dataFrom: '{index:'+paramToJAVA+'}'},
        success: function (data) {
            var aaData = eval(data).data;
            if (aaData!= undefined && aaData.length==0) {
                $('#' + tableContainerId).html("No data!");
            }
            else if (aaData != undefined && aaData[0] != undefined && aaData[0][0] != "ERROR!!!") {
                for (var param in tableParams) {
                    dataTableParams[param] = tableParams[param];
                }

                dataTableParams.aaData = eval(data).data;
                dataTableParams.bJQueryUI = true;
                dataTableParams.bDestroy = true;
                dataTableParams.bDeferRender = true;
                dataTableParams.aoColumnDefs = dataTableParams.aoColumnDefs || [];

                if (dataTableParams.infoColumn != undefined) {
                    var columnNo = dataTableParams.infoColumn;

                    dataTableParams.aoColumnDefs.push(
                        {
                            aTargets: [columnNo - 1],
                            mData: null,
                            mRender: function (data, type, full) { //TODO: replace alert with other method (modal)?
                                return '<a href="#" onclick="alert(\'' + full[0] + ' ' + full[1] + '\');"><i class="fa fa-info"></i></a>';
                            }
                        }
                    );
                    dataTableParams.infoColumn = null;
                }

                if (dataTableParams.editColumn != undefined && ajaxActions.edit != undefined) {
                    var columnNo = dataTableParams.editColumn;

                    dataTableParams.aoColumnDefs.push(
                        {
                            aTargets: [columnNo - 1],
                            mData: null,
                            mRender: function (data, type, full) {
                                return '<a href="#" onclick="editRow(this, \'' + ajaxActions.edit + '\');"><i class="fa fa-edit"></i></a>';
                            }
                        }
                    );
                    dataTableParams.editColumn = null;
                }

                if (dataTableParams.deleteColumn != undefined && ajaxActions.delete != undefined) {
                    var columnNo = dataTableParams.deleteColumn;

                    dataTableParams.aoColumnDefs.push(
                        {
                            aTargets: [columnNo - 1],
                            mData: null,
                            mRender: function (data, type, full) {
                                return '<a href="#" onclick="deleteRow(this, \'' + ajaxActions.delete + '\');"><i class="fa fa-trash-o"></i></a>';
                            }
                        }
                    );
                    dataTableParams.deleteColumn = null;
                }

                //scrollable - properties:
                // dataTableParams.bScrollCollapse = true;
                // dataTableParams.bScrollInfinite = true;
                // dataTableParams.iDisplayLength = 50;
                // dataTableParams.sScrollY = '400px';

                $('#' + tableContainerId).dataTable(dataTableParams);
            } else {
                $('#' + tableContainerId).html("Server ERROR!");
                console.log("Error: ", data);
            }
        },
        error: function (data) {
            console.log("Error: ", data);
        }});


}

function generateAlertSuccess($resultContainer, message) {
    $resultContainer.html("<div class='alert alert-success fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Info!</strong> " + message + "</div>").show();
    setTimeout(function () {
        $resultContainer.hide()
    }, 2000);
}
function generateAlertError($resultContainer, message) {
    $resultContainer.html("<div class='alert alert-danger fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Error!</strong> " + message + "</div>").show();
    setTimeout(function () {
        $resultContainer.hide();
    }, 2000);
}
function generateAlertWarning($resultContainer, message, time) {
    var _time = time || 2000;
    $resultContainer.html("<div class='alert alert-warning fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Warning!</strong> " + message + "</div>").show();
    setTimeout(function () {
        $resultContainer.hide();
    }, _time);
}

function generateModal(modalId, titleHTML, messageHTML, action, isShown) {
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
        '<a href="'+action+'" class="btn btn-primary">Ok</a>'+
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>');

    if (isShown) {
        $('#' + modalId).modal('show');
    }
}

function deleteRow(that, action) {
    if (confirm("Are you sure?")) {
        var $this = $(that);
        var $thisRow = $this.parents('tr');
        var index = $thisRow.find('td:first').text();
        var table = $this.parents('table').dataTable();
        var $resultContainer = jQuery('#server-messages');

        $.ajax({
            type: 'POST',
            url: action,
            data: {dataFrom: "{'index':'" + index + "'}"},
            success: function (msg) {
                console.log(action, msg);
                try {
                    if (msg.data[0][0] == "success") {
                        $thisRow.trigger("rowclick");
                        $thisRow.removeClass('row-selected');
                        table.fnDeleteRow($thisRow[0]);
                    } else {
                        generateAlertError($resultContainer, "Error occured during deleting action!");
                    }
                } catch (error) {
                    generateAlertError($resultContainer, "JavaScript ERROR: " + error);
                }
            },
            error: function (msg) {
                generateAlertError($resultContainer, "Wystąpił bład podczas zapisu do bazy danych!");
            }
        });
    }
}
var isFinishedSubmit = true;
function createSelectListWithDataFromDB(ajaxAction, selectContainerId, selectParams, isHidden) {
    var params = selectParams;
    if (isFinishedSubmit) {
        isFinishedSubmit = false;
        $.ajax({
            type: 'POST',
            url: ajaxAction,
            success: function (data) {
                var aaData = eval(data).data;

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
                isFinishedSubmit = true;
            },
            error: function (data) {
                console.log("Error: ", data);
                isFinishedSubmit = true;
            }});
    }

}

function bindSelectTable(tableId, selectId, multiselectOpt, labelColNumber) {
    setTimeout(function () { //because of getting content of table by ajax
        var multiselectable = multiselectOpt || false;
        var labelColNum = labelColNumber || "2";
        var $tableElem = $('#' + tableId);
        var $tableRows = $tableElem.find('tbody tr');

        var $selectElem = $("#" + selectId);
        var isSelect = selectId != undefined && $selectElem.length > 0;

        //from select to table
        if (isSelect) {
            $selectElem.chosen().change(function () {
                var selectIndexes = $(this).val();

                $tableRows.each(function () {
                    var $this = $(this);
                    var index = $this.find('td:first').text();
                    if (jQuery.inArray('' + index, selectIndexes) > -1) {
                        $this.addClass('row-selected');
                    } else {
                        $this.removeClass('row-selected');
                    }
                })
            });
        }

        //from table to select
        $tableRows.on('click rowclick', function (e) {
            var $this = $(this);
            if (multiselectable) {  //multi select
                if (!$this.hasClass('row-selected')) {
                    $this.addClass('row-selected');
                } else {
                    $this.removeClass('row-selected');
                }
            } else {   //single select
                if (!$this.hasClass('row-selected')) {
                    $tableRows.removeClass('row-selected');
                    $this.addClass('row-selected');
                }
            }

            if (e.type == "click") { //for trigger rowselected only!
                var index = $this.find('td:first').text();
                var label = $this.find('td:eq(' + (labelColNum - 1) + ')').text();
                var $body = $('body');
                if ($this.hasClass('row-selected')) {
                    $body.trigger("table:rowselected", [tableId, index, label]);
                } else {
                    $body.trigger("table:rowunselected", [tableId, index, label]);
                }
            }

            if (isSelect) {
                var indexes = [];
                $tableRows.each(function () {
                    var index = $(this).find('td:first').text();
                    if ($(this).hasClass('row-selected')) {
                        indexes.push(index);
                    } else {
                        var position = jQuery.inArray(index, indexes);
                        if (~position) indexes.splice(position, 1);
                    }

                });
                $selectElem.chosen().val(indexes);
                $selectElem.trigger("chosen:updated");
            }
        });


    }, 1000);
}


function ajaxSubmit(formId, resultContainerId) {
    var $form = jQuery('#' + formId);
    var $resultContainer = jQuery('#' + resultContainerId);
    if ($form.valid()) {
        var send = $form.formToJSON();
        var formAction = $form.attr('action');
        var dataFromForm = {dataFrom: send};
        jQuery.ajax({
            url: formAction,
            type: 'POST',
            data: dataFromForm,
            dataType: "json",
            error: function () {
                generateAlertError($resultContainer, "Wystąpił bład podczas zapisu do bazy danych!");
                isFinishedSubmit = true;
            },
            success: function (msg) {
                try {
                    if (msg.data[0][0] == "success") {
                        generateAlertSuccess($resultContainer, "Operacja przbiegla pomyślnie");
                    } else if (msg.data[0][0] == "overlapped") {
                        generateAlertWarning($resultContainer, msg.data[0][1], 8000);
                    } else {
                        generateAlertError($resultContainer, "Error occured during add action!");
                    }
                } catch (error) {
                    generateAlertError($resultContainer, "JavaScript ERROR: " + error);
                }
            }
        });
    } else {
        generateAlertError($resultContainer, "Błędnie wypełniony formularz!")
    }

    return false;
}

function ajaxSubmitFirstHotel(formId, resultContainerId) {
    var $form = jQuery('#' + formId);
    var $resultContainer = jQuery('#' + resultContainerId);
    if ($form.valid()) {
        var send = $form.formToJSON();
        var formAction = $form.attr('action');
        var dataFromForm = {dataFrom: send};
        jQuery.ajax({
            url: formAction,
            type: 'POST',
            data: dataFromForm,
            dataType: "json",
            error: function () {
                generateAlertError($resultContainer, "Wystąpił bład podczas zapisu do bazy danych!");
            },
            success: function (msg) {
                try {
                    if (msg.data[0][0] == "success") {
                        generateModal("modal-first-hotel", "Welcome!",
                            " Congratulations!" +
                            "<br>" +
                            "You've just successfully added your first hotel! " +
                            "<br>" +
                            "Click [OK] to log in again."
                            , '/bookingsystem/logout', true);
                    } else {
                        generateAlertError($resultContainer, "Error occured during add action!");
                    }
                } catch (error) {
                    generateAlertError($resultContainer, "JavaScript ERROR: " + error);
                }
            }
        });
    } else {
        generateAlertError($resultContainer, "Błędnie wypełniony formularz!")
    }

    return false;
}

function formValidate(formId, rules) {
    var capacity = 0;
    jQuery.validator.addMethod("later_than_today", function (value, element, param) {
        var fDate = new Date();
        var sDate = new Date(value);
        return fDate <= sDate;

    }, jQuery.validator.format("'Date must be equals or later than today!"));


    jQuery.validator.addMethod("later_than", function (value, element, param) {
        var firstDate = param[0];
        var fDate = new Date($(firstDate).val());
        var sDate = new Date(value);
        return fDate < sDate;

    }, jQuery.validator.format("'Date To' must be later than 'Date From'!"));

    jQuery.validator.addMethod("not_more_than", function (value, element, param) {
        var what = param[0];
        var capacity = $(element).data(what);
        return capacity >= value;

    }, jQuery.validator.format("Count of person must be <= capacity of rooms"));


    jQuery.validator.addMethod("accept", function (value, element, param) {
        return value.match(new RegExp("^" + param + "$"));
    });
    jQuery.validator.addMethod("letter_and_digit", function (value, element, param) {
        return value.match(new RegExp("^[a-zA-Z0-9 ążźćśóęłĘÓĄŚŁŻŹŃń,.-]+$"));
    });
    jQuery.validator.addMethod("letter", function (value, element, param) {
        return value.match(new RegExp("^[a-zA-Z ążźćśóęłĘÓĄŚŁŻŹŃń,.-]+$"));
    });
    jQuery.validator.addMethod("phone", function (value, element, param) {
        return value.match(new RegExp("^[0-9 -+()]+$"));
    });

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

    jQuery.extend(jQuery.validator.messages, {
        accept: "Please fix this field.",
        letter_and_digit: "Please enter only letters and digits.",
        letter: "Please enter only letters.",
        phone: "Please enter a valid phone number."
    });
}


/**
 * Generates json from html form elements - nested properties supported.
 *
 * <input type="text" name="foo.bar.bar"/>
 * <input type="text" name="foo.barfoo"/>
 * <input type="text" name="bar.bar.foo"/>
 * <input type="text" name="foo.bar.foo"/>
 *
 * returns:
 *{foo: {bar: {bar: '', foo: ''}, barfoo: '' }, bar: {bar: {foo: ''}}}
 *
 * @returns json
 */
jQuery.fn.formToJSON = function () {
    var objectGraph = {};
    var that = {};

    that.add = function (objectGraph, name, value) {
        if (name.length == 1) {
            //if the array is now one element long, we're done
            objectGraph[name[0]] = value;
        }
        else {
            //else we've still got more than a single element of depth
            if (objectGraph[name[0]] == null) {
                //create the node if it doesn't yet exist
                objectGraph[name[0]] = {};
            }
            //recurse, chopping off the first array element
            add(objectGraph[name[0]], name.slice(1), value);
        }
    };

    //loop through all of the input/textarea elements of the form
    //this.find('input, textarea').each(function() {
    $(this).find('input, textarea, select').each(function () {
        //ignore the submit button
        if ($(this).attr('name') != undefined && $(this).attr('name') != 'submit') {
            //split the dot notated names into arrays and pass along with the value
            that.add(objectGraph, $(this).attr('name').split('.'), $(this).val());
        }
    });
    return JSON.stringify(objectGraph);

};

var isFinishedLoad = true;
function loadHotel(action, resultContainerId, selectedItemIndex, hotelnameContainerId, hotel_name) {
    var $resultContainer = jQuery('#' + resultContainerId);
    var $hotelnameContainer = jQuery('#' + hotelnameContainerId);

    if (isFinishedLoad) {
        isFinishedLoad = false;
        jQuery.ajax({
            url: action,
            type: 'POST',
            data: {dataFrom: "{'index':'" + selectedItemIndex + "'}"},
            dataType: "json",
            error: function (msg) {
                if (msg.data[0][0] == "error") {
                    if (msg.data[0][1] != undefined && msg.data[0][1] == "THE_SAME") {
                        generateAlertError($resultContainer, "Hotel został już wybrany!");
                    }
                }
                console.log(msg);
                generateAlertError($resultContainer, "Wystąpił błąd servera!");
                isFinishedLoad = true;
            },
            success: function (msg) {
                console.log(msg);
                try {
                    if (msg.data[0][0] == "success") {
                        ajaxDefaultIncudeOnStart();
                        generateAlertSuccess($resultContainer, "Hotel wczytany.");
                        $hotelnameContainer.text(hotel_name);
                    } else if (msg.data[0][0] == "error") {
                        if (msg.data[0][1] != undefined && msg.data[0][1] == "THE_SAME") {
                            generateAlertWarning($resultContainer, "Hotel został już wybrany!");
                        }
                    } else {
                        generateAlertError($resultContainer, "Wystąpił błąd podczas wczytywania hotelu!");
                    }
                } catch (error) {
                    generateAlertError($resultContainer, "JavaScript ERROR: " + error);
                }
                isFinishedLoad = true;
            }
        });
    }

}

function ajaxSelect(selectId, action, resultContainerId) {
    var selectedItemIndex = "";
    var $select = jQuery('#' + selectId);
    $select.on('change', function () {
        var selectedIndex = $(this).val();
        if (selectedIndex) selectedItemIndex = selectedIndex;
        loadHotel(action, resultContainerId, selectedItemIndex);
    });

    return selectedItemIndex;
}

function ajaxSelectFromTable(tableId, action, resultContainerId, hotelnameContainerId) {
    $('body').on('table:rowselected', function (e, table_id, index, hotel_name) {
        if (index) {
            if (tableId == table_id) {
                loadHotel(action, resultContainerId, index, hotelnameContainerId, hotel_name);
            }
        }
    });
}

function checkRoomsCapacity(tableId, formId) {
    var capacitySum = 0;
    var $form = $('#' + formId);
    $('body').on('table:rowselected table:rowunselected', function (e, table_id, index, capacity) {
        if (index) {
            if (tableId == table_id) {
                if (e.type == 'table:rowselected') {
                    capacitySum += parseInt(capacity);
                } else if (e.type == 'table:rowunselected') {
                    capacitySum -= parseInt(capacity);
                }
                $form.find('[name="person_count"]').data('capacity', capacitySum);
            }
        }
    });
}


function countCapacity(formId) {
    var $form = $('#' + formId);
    $form.on('keyup', '[name="bed_count"], [name="bed_type"]', function () {
        var bedCount = $form.find('[name="bed_count"]').val();
        var bedType = $form.find('[name="bed_type"]').val();
        if (bedCount == "") bedCount = 0;
        if (bedType == "") bedType = 0;
        var capacity = bedCount * bedType;
        $form.find('[name="capacity"]').val(capacity);
    });
}


function showFieldIfSelectedValEquals(value, formId, fieldFromId, fieldToId) {
    var $form = $('#' + formId);
    var $typeField = $form.find('#' + fieldFromId);
    var $hotelField = $form.find('#' + fieldToId);
    var $hotelFieldGroup = $hotelField.closest('.form-group');
    $typeField.on('change', function () {
        var $this = $(this);
        var selectedType = $this.val();
        if (selectedType == value) {
            $hotelFieldGroup.show();
        } else {
            $hotelFieldGroup.hide();
        }
    });

}
