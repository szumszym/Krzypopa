App.Models.Room = (function ($) {
    var _default = {
        events: {
            selected: 'table:rowSelected',
            unselected: 'table:rowUnselected'
        },
        countPersonId: '[name="person_count"]',
        capacityId: '[name="capacity"]',
        bedTypeId: '[name="bed_type"]',
        bedCountId: '[name="bed_count"]'

    };

    return {
        checkCapacity: function (tableId, formId) {
            var capacitySum = 0;
            var $form = $('#' + formId);
            $('body').on(_default.events.selected + ' ' + _default.events.unselected, function (e, table_id, index, capacity) {
                if (index) {
                    if (tableId == table_id) {
                        if (e.type == _default.events.selected) {
                            capacitySum += parseInt(capacity);
                        } else if (e.type == _default.events.unselected) {
                            capacitySum -= parseInt(capacity);
                        }
                        $form.find(_default.countPersonId).data('capacity', capacitySum);
                    }
                }
            });
        },
        countCapacity: function (formId) {
            var $form = $('#' + formId);
            $form.on('keyup', _default.bedCountId + ',' + _default.bedTypeId, function () {
                var bedCount = $form.find(_default.bedCountId).val();
                var bedType = $form.find(_default.bedTypeId).val();
                if (bedCount == "") bedCount = 0;
                if (bedType == "") bedType = 0;
                var capacity = bedCount * bedType;
                $form.find(_default.capacityId).val(capacity);
            });
        }

    }

})(jQuery);