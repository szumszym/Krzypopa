App.Components.Binder = (function ($) {
    var _default = {
        delay: 1000,
        events: {
            selected: 'table:rowSelected',
            unselected: 'table:rowUnselected',
            deleted: 'table:rowDeleted',
            chosen: {
                selected: 'select:ItemSelected',
                unselected: 'select:ItemUnselected'
            },
            capacity: {
                plus: 'capacity:plus',
                minus: 'capacity:minus'
            }
        }
    };

    return {
        bindSelectTable: function (tableId, selectId, multiselectOpt, labelColNumbersArray) {
            setTimeout(function () { //because of getting content of table by ajax
                var multiselectable = multiselectOpt || false;
                var labelColNumbers = labelColNumbersArray || ["2"];
                var $tableElem = $('#' + tableId);

                var $selectElem = $("#" + selectId);
                var isSelect = selectId != undefined && $selectElem.length > 0;

                //from select to table
                if (isSelect) {
                    $selectElem.chosen().change(function () {
                        var selectIndexes = $(this).val();
                        var $tableRows = $tableElem.find('tbody tr');

                        $tableRows.each(function () {
                            var $this = $(this);
                            var index = $this.find('td:first').text();
                            var labelsArray = [];
                            for (var i = 0; i < labelColNumbers.length; i++) {
                                labelsArray.push($this.find('td:eq(' + (labelColNumbers[i] - 1) + ')').text());
                            }
                            var $body = $('body');
                            if (jQuery.inArray('' + index, selectIndexes) > -1) {
                                if (!$this.hasClass('row-selected')) {
                                    $this.addClass('row-selected');
                                    $body.trigger(_default.events.capacity.plus, [tableId, index, labelsArray]);
                                    $body.trigger(_default.events.chosen.selected, [tableId, index, labelsArray]);
                                }
                            } else {
                                if ($this.hasClass('row-selected')) {
                                    $this.removeClass('row-selected');
                                    $body.trigger(_default.events.capacity.minus, [tableId, index, labelsArray]);
                                    $body.trigger(_default.events.chosen.unselected, [tableId, index, labelsArray]);
                                }

                            }
                        })
                    });
                }

                //from table to select
                $tableElem.on('click ' + _default.events.deleted, 'tbody tr', function (e) {
                    var $this = $(this);
                    var $tableRows = $tableElem.find('tbody tr');

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
                        var labelsArray = [];
                        for (var i = 0; i < labelColNumbers.length; i++) {
                            labelsArray.push($this.find('td:eq(' + (labelColNumbers[i] - 1) + ')').text());
                        }
                        var $body = $('body');
                        if ($this.hasClass('row-selected')) {

                            $body.trigger(_default.events.capacity.plus, [tableId, index, labelsArray]);
                            $body.trigger(_default.events.selected, [tableId, index, labelsArray]);
                        } else {
                            $body.trigger(_default.events.capacity.minus, [tableId, index, labelsArray]);
                            $body.trigger(_default.events.unselected, [tableId, index, labelsArray]);
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


            }, _default.delay);
        }

    }

})(jQuery);