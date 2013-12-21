jQuery(function () {
    ajaxInclude();
    ajaxDefaultIncudeOnStart();
    //ajaxSubmit();
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

function ajaxSubmit(formId, resultContainerId) {
    jQuery('#input').click(function () {
        var $form = jQuery(formId);
        var send = $form.formToJSON();
        var formAction = $form.attr('action');

        jQuery.ajax({
            url: formAction,
            type: 'POST',
            data: send,
            contentType: "application/json; charset=utf-8", //TODO: check if it working with this
            dataType: "json",
            error: function (xhr, error) {
                window.alert('Error!  Status = ' + xhr.status + ' Message = ' + error);
            },
            success: function (data) {
                jQuery(resultContainerId).text(data);
            }
        });
        return false;
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
    var objectGraph =

        function add(objectGraph, name, value) {
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
    jQuery(this).children('input, textarea, select').each(function () {
        //ignore the submit button
        if (jQuery(this).attr('name') != 'submit') {
            //split the dot notated names into arrays and pass along with the value
            add(objectGraph, jQuery(this).attr('name').split('.'), jQuery(this).val());
        }
    });
    return JSON.stringify(objectGraph);
};

