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

    var dataTableParams = [];
    $.ajax({
        type: 'POST',
        url: ajaxActions.get,
        success: function (data) {
            var aaData = eval(data).data;
            if (aaData == undefined) {
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
    $resultContainer.html("<div class='alert alert-success fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Info!</strong>" + message + "</div>").show();
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

function createSelectListWithDataFromDB(ajaxAction, selectContainerId, selectParams) {
    var params = selectParams;
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
            } else {
                $selectElem.html("Server ERROR!");
                console.log("Error: ", data);
            }
        },
        error: function (data) {
            console.log("Error: ", data);
        }});


}

function bindSelectTable(selectId, tableId, multiselectOpt) {
    setTimeout(function () { //because of getting content of table by ajax
        var multisectable = multiselectOpt || false;

        var $tableElem = $('#' + tableId);
        var $selectElem = $("#" + selectId);
        var $tableRows = $tableElem.find('tbody tr');

        //from select to table
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

        //from table to select
        $tableRows.on('click rowclick', function (e) {
            var $this = $(this);
            if (multisectable) {  //multi select
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
            },
            success: function (msg) {
                try{
                    if (msg.data[0][0] == "success") {
                        generateAlertSuccess($resultContainer, "Operacja przbiegla pomyślnie");
                    } else {
                        generateAlertError($resultContainer, "Error occured during add action!");
                    }
                } catch(error){
                    generateAlertError($resultContainer, "JavaScript ERROR: "+error);
                }
            }
        });
    } else {
        generateAlertError($resultContainer, "Błędnie wypełniony formularz!")
    }

    return false;
}

function formValidate(formId, rules) {


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



