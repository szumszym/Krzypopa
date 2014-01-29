App.Models.Room = (function ($) {
    var _default = {
        events: {
            selected: 'table:rowSelected',
            unselected: 'table:rowUnselected',
            chosen: {
                selected: 'select:ItemSelected',
                unselected: 'select:ItemUnselected'
            },
            capacity: {
                plus: 'capacity:plus',
                minus: 'capacity:minus'
            }
        },
        countPersonId: '[name="person_count"]',
        capacityId: '[name="capacity"]',
        bedTypeId: '[name="bed_type"]',
        bedCountId: '[name="bed_count"]',
        dateFromId: '[name="date_from"]',
        dateToId: '[name="date_to"]',
        priceId: '[name="price"]'

    };
    // parse a date in yyyy-mm-dd format
    var _parseDate = function (input) {
        try {
            var parts = input.split('-');
            // new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
            return new Date(parts[0], parts[1] - 1, parts[2]); // Note: months are 0-based
        }
        catch (e) {
            alert('PARSE DATE: ' + input + ' \nAn error has occurred: ' + e.message);
            console.log('PARSE DATE: ' + input + ' An error has occurred: ' + e.message);
            return null;
        }
    };

    var _daysBetween = function (dateFrom, dateTo) {
        var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
        return Math.round(Math.abs((dateFrom.getTime() - dateTo.getTime()) / (oneDay)));
    };
    return {
        checkCapacity: function (tableId, formId) {
            var capacitySum = 0;
            var $form = $('#' + formId);
            $('body').on(_default.events.capacity.plus + ' ' + _default.events.capacity.minus, function (e, table_id, index, capacity) {
                if (index) {
                    if (tableId == table_id) {
                        if (e.type == _default.events.capacity.plus) {
                            capacitySum += parseInt(capacity[0]);
                        } else if (e.type == _default.events.capacity.minus) {
                            capacitySum -= parseInt(capacity[0]);
                        }
                        $form.find(_default.countPersonId).data('capacity', capacitySum);
                    }
                }
            });
        },
        countCapacity: function (formId) {
            var $form = $('#' + formId);
            $form.on('keyup change', _default.bedCountId + ',' + _default.bedTypeId, function () {
                var bedCount = $form.find(_default.bedCountId).val();
                var bedType = $form.find(_default.bedTypeId).val();
                if (bedCount == "") bedCount = 0;
                if (bedType == "") bedType = 0;
                var capacity = bedCount * bedType;
                $form.find(_default.capacityId).val(capacity);
            });
        },
        countPrice: function (tableId, formId) {

            var priceSum = 0.0;
            var $form = $('#' + formId);
            $('body').on(_default.events.selected + ' ' + _default.events.unselected + ' ' + _default.events.chosen.selected + ' ' + _default.events.chosen.unselected, function (e, table_id, index, rowValues) {
                if (index) {
                    if (tableId == table_id) {
                        if (e.type == _default.events.selected || e.type == _default.events.chosen.selected) {
                            priceSum += parseFloat(rowValues[1]);
                        } else if (e.type == _default.events.unselected || e.type == _default.events.chosen.unselected) {
                            priceSum -= parseFloat(rowValues[1]);
                        }
                        console.log(priceSum);
                        $('body').trigger('price-rooms:changed', [priceSum]);
                    }
                }
            });

            var price = 0.0;
            var days = 0;


            $form.on('keyup change', _default.dateFromId + ',' + _default.dateToId, function () {

                //TODO: trigger na zmiane ceny pokoi
                var dateFromVal = $form.find(_default.dateFromId).val();
                var dateToVal = $form.find(_default.dateToId).val();
                var dateFrom = _parseDate(dateFromVal);
                var dateTo = _parseDate(dateToVal);

                var days = _daysBetween(dateFrom, dateTo) || 0;

                var priceSum = days;
                $('body').trigger('price-days:changed', [priceSum]);
            });
            $('body').on('price-rooms:changed price-days:changed', function (e, priceSum) {

                if (e.type == 'price-rooms:changed') {
                    price = parseFloat(priceSum);
                } else if (e.type == 'price-days:changed') {
                    days = parseFloat(priceSum);
                }
                var totalPrice = price * days;
                $form.find(_default.priceId).val(totalPrice)
            });

            $(document).ready(function () {
                var dateFromVal = $form.find(_default.dateFromId).val();
                var dateToVal = $form.find(_default.dateToId).val();
                var dateFrom = _parseDate(dateFromVal);
                var dateTo = _parseDate(dateToVal);

                var days = _daysBetween(dateFrom, dateTo) || 0;

                var priceSum = days;
                $('body').trigger('price-days:changed', [priceSum]);
            });

        }

    }

})(jQuery);