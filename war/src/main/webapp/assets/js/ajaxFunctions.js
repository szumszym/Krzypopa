jQuery(function () {
    ajaxInclude();
    ajaxDefaultIncudeOnStart();
    //ajaxSubmit();
});

function ajaxSubmit(formId, outputId) {
    var $form = $(formId);

    $.ajax({
        type: 'POST',
        url: $form.attr('action'),
        data: {data: $form.serialize()},
        dataType: 'json',
        success: function (data) {
            // if(data.result == "ok")
            console.log("Success: ", JSON.stringify(data));
//            $(outputId).text(data);
        },
        error: function (data) {
            console.log("Error: ", JSON.stringify(data));
        }});
}

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
function ajaxGetDataFromDB(action) {

    $.ajax({
        type: 'POST',
        url: action,
        success: function (data) {
            console.log("Success: ", JSON.stringify(data));
            return eval(data).data

//            $(outputId).text(data);
        },
        error: function (data) {
            console.log("Error: ", JSON.stringify(data));
        }});


}


function createTableWithDataFromDB(ajaxAction, tableContainerId, tableParams) {
    var dataTableParams = [];
    $.ajax({
        type: 'POST',
        url: ajaxAction,
        success: function (data) {
            var aaData = eval(data).data;
            if (aaData != undefined && aaData[0][0] != "ERROR!!!") {
                for (var param in tableParams) {
                    dataTableParams[param] = tableParams[param];
                }

                dataTableParams.aaData = eval(data).data;

                // dataTableParams.aaSorting = [[0, "asc"], [1, "desc"], [2, "desc"]];
                dataTableParams.bJQueryUI = true;

                dataTableParams.bDestroy = true;
                dataTableParams.bDeferRender = true;

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


function ajaxSubmit(formId, resultContainerId) {
    var $form = jQuery('#' + formId);
    var send = $form.formToJSON();
    var formAction = $form.attr('action');
    var dataFromForm = {dataFrom: send};
    jQuery.ajax({
        url: formAction,
        type: 'POST',
        data: dataFromForm,
        dataType: "json",
        error: function () {
            jQuery('#' + resultContainerId).html("<div class='alert alert-danger fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Error!</strong> Wystąpił bład podczas zapisu do bazy danych!</div>");
        },
        success: function () {

            jQuery('#' + resultContainerId).html("<div class='alert alert-success fade in'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>×</button><strong>Info!</strong> Operacja przbiegla pomyślnie</div>");
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

