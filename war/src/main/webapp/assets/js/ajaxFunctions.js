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


function createTableWithDataFromDB(ajaxAction, tableContainerId, tableParams) {
    var dataTableParams = [];
    $.ajax({
        type: 'POST',
        url: ajaxAction,
        success: function (data) {
            var aaData = eval(data).data;
            if (aaData != undefined && aaData[0] != undefined && aaData[0][0] != "ERROR!!!") {
                for (var param in tableParams) {
                    dataTableParams[param] = tableParams[param];
                }

                dataTableParams.aaData = eval(data).data;
                dataTableParams.bJQueryUI = true;
                dataTableParams.bDestroy = true;
                dataTableParams.bDeferRender = true;
                dataTableParams.aoColumnDefs = [];

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

                if (dataTableParams.editColumn != undefined) {
                    var columnNo = dataTableParams.editColumn;

                    dataTableParams.aoColumnDefs.push(
                        {
                            aTargets: [columnNo - 1],
                            mData: null,
                            mRender: function (data, type, full) {
                                return '<a href="#" onclick="alert(\'' + full[0] + ' ' + full[1] + '\');"><i class="fa fa-edit"></i></a>';
                            }
                        }
                    );
                    dataTableParams.editColumn = null;
                }

                if (dataTableParams.deleteColumn != undefined) {
                    var columnNo = dataTableParams.deleteColumn;

                    dataTableParams.aoColumnDefs.push(
                        {
                            aTargets: [columnNo - 1],
                            mData: null,
                            mRender: function (data, type, full) {
                                return '<a href="#" onclick="alert(\'' + full[0] + ' ' + full[1] + '\');"><i class="fa fa-trash-o"></i></a>';
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
            } else {
                $selectElem.html("Server ERROR!");
                console.log("Error: ", data);
            }
        },
        error: function (data) {
            console.log("Error: ", data);
        }});


}


function ajaxSubmit(formId, resultContainerId) {
    var $form = jQuery('#' + formId);
    var send = $form.formToJSON();
    var formAction = $form.attr('action');
    var dataFromForm = {dataFrom: send};
    var $resultContainer = jQuery('#' + resultContainerId);
    jQuery.ajax({
        url: formAction,
        type: 'POST',
        data: dataFromForm,
        dataType: "json",
        error: function () {
            $resultContainer.html("<div class='alert alert-danger fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Error!</strong> Wystąpił bład podczas zapisu do bazy danych!</div>").delay(1000).hide();
            setTimeout(function () {
                $resultContainer.hide()
            }, 1000);
        },
        success: function () {
            $resultContainer.html("<div class='alert alert-success fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Info!</strong> Operacja przbiegla pomyślnie</div>").delay(1000).hide();
            setTimeout(function () {
                $resultContainer.hide()
            }, 1000);
        }
    });
    return false;
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
        if ($(this).attr('name') != 'submit') {
            //split the dot notated names into arrays and pass along with the value
            that.add(objectGraph, $(this).attr('name').split('.'), $(this).val());
        }
    });
    return JSON.stringify(objectGraph);

};

