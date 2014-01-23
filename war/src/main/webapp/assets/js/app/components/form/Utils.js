App.Components.Form.Utils = (function ($) {
    var _default = {
        toJSON: {
            elements: 'input, textarea, select'
        }
    };

    return {
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
        formToJSON: function (formId) {
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
                    that.add(objectGraph[name[0]], name.slice(1), value);
                }
            };

            //loop through all of the input/textarea elements of the form
            //this.find('input, textarea').each(function() {
            $('#' + formId).find(_default.toJSON.elements).each(function () {
                //ignore the submit button
                if ($(this).attr('name') != undefined && $(this).attr('name') != 'submit') {
                    //split the dot notated names into arrays and pass along with the value
                    that.add(objectGraph, $(this).attr('name').split('.'), $(this).val());
                }
            });
            return JSON.stringify(objectGraph);

        },
        showFieldIfSelectedValEquals: function (value, formId, fieldFromId, fieldToId) {
            var $form = $('#' + formId);
            var $typeField = $form.find('#' + fieldFromId);
            var $field = $form.find('#' + fieldToId);
            var $fieldGroup = $field.closest('.form-group');
            $typeField.on('change', function () {
                var $this = $(this);
                var selectedType = $this.val();
                if (selectedType == value) {
                    $fieldGroup.show();
                } else {
                    $fieldGroup.hide();
                }
            });

        }
    }

})(jQuery);