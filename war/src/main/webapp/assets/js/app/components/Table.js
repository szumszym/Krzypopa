App.Components.Table = (function ($, Alert, Includer, Modal, Submitter) {
    var _default = {
        alert: {
            messages: {
                error: {
                    save: 'Wystąpił bład podczas zapisu do bazy danych!',
                    delete: 'Wystąpił bład podczas usuwania w bazie danych!',
                    edit: 'Wystąpił bład podczas edycji w bazie danych!',
                    js: 'JavaScript ERROR: ',
                    invalidForm: 'Błędnie wypełniony formularz!'
                },
                warning: {
                    hasReservations: 'Nie można usunąć pokoju, istnieją przypisane do niego rezerwacje!',
                    hasRooms: 'Nie można usunąć dodatku - istnieją przypisane do niego pokoje!'
                },
                success: {
                    save: 'Operacja zapisu do bazy danych przbiegla pomyślnie',
                    edit: 'Operacja edycji przbiegla pomyślnie',
                    delete: 'Operacja usunięcia przbiegla pomyślnie'
                }
            }
        },
        events: {
            selected: 'table:rowSelected',
            unselected: 'table:rowUnselected',
            deleted: 'table:rowDeleted',
            edited: 'table:rowEdited'
        }
    };

    var _updateCells = function ($cells, data) {
        var i = 1;//exclude first cell with id
        $cells.each(function () {
            var $this = $(this);
            $this.text(data[i]);
            i++;
        });
    };

    var _updateChosenSelect = function (formId) {
        //  setTimeout(function(){
        var $selectElements = $('#' + formId).find('select');
        $selectElements.each(function () {
            var $this = $(this);
            var array = [];
            var indexes = "" + $this.data('indexes');

            $this.chosen();

            while (indexes.length > 0) {
                var sep = indexes.indexOf(',');
                if (sep == -1) {
                    array.push(parseInt(indexes.substring(0, indexes.length)));
                    break;
                }
                array.push(parseInt(indexes.substring(0, sep)));
                indexes = indexes.substring(sep + 1, indexes.length);
            }

            $this.val(array).trigger('chosen:updated');
            $this.siblings('.chosen-container').css('width', '100%');
        });
        //  }, 500);
    };

    var _updateEditForm = function ($form, data) {
        var i = 1; //exclude first cell with id
        $form.find('.form-group').each(function () {
            var $this = $(this);
            $this.find('input, textarea, select').each(function () {
                var $this = $(this);
                if ($this.is('input')) {
                    $this.val(data[i]);
                } else if ($this.is('textarea')) {
                    $(this).text(data[i]);
                } else if ($this.is('select')) {
                    $this.val(data[i]);
                    $this.trigger("chosen:updated");
                }
            });
            i++;
        });
    };

    return {
        create: function (params) {
            var ajaxActions = params.actions;
            var tableContainerId = params.table.id;
            var tableParams = params.table.params;
            var paramToJAVA = params.toJAVA;
            var editParams = params.edit || {};
            var dataTableParams = [];
            $.ajax({
                type: 'POST',
                url: ajaxActions.get,
                data: {dataFrom: '{index:' + paramToJAVA + '}'},
                success: function (data) {
                    var aaData = eval(data).data;
                    if (aaData != undefined && aaData.length == 0) {
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
                            var columnNoInfo = dataTableParams.infoColumn;

                            dataTableParams.aoColumnDefs.push(
                                {
                                    aTargets: [columnNoInfo - 1],
                                    mData: null,
                                    mRender: function (data, type, full) { //TODO: replace alert with other method (modal)?
                                        return '<a href="#" onclick="alert(\'' + full[0] + ' ' + full[1] + '\');"><i class="fa fa-info"></i></a>';
                                    }
                                }
                            );
                            dataTableParams.infoColumn = null;
                        }

                        if (dataTableParams.editColumn != undefined && ajaxActions.edit != undefined) {
                            var columnNoEdit = dataTableParams.editColumn;
                            var editUrl = editParams.url || "";//"./404.html";
                            var editTitle = editParams.title || "Edit";
                            dataTableParams.aoColumnDefs.push(
                                {
                                    aTargets: [columnNoEdit - 1],
                                    mData: null,
                                    mRender: function (data, type, full) {
                                        return '<a href="#" onclick="App.Components.Table.editRow(this, \'' + ajaxActions.edit + '\', \'' + ajaxActions.update + '\', \'' + editUrl + '\', \'' + editTitle + '\');"><i class="fa fa-edit"></i></a>';
                                    }
                                }
                            );
                            dataTableParams.editColumn = null;
                        }

                        if (dataTableParams.deleteColumn != undefined && ajaxActions.delete != undefined) {
                            var columnNoDelete = dataTableParams.deleteColumn;

                            dataTableParams.aoColumnDefs.push(
                                {
                                    aTargets: [columnNoDelete - 1],
                                    mData: null,
                                    mRender: function (data, type, full) {
                                        return '<a href="#" onclick="App.Components.Table.deleteRow(this, \'' + ajaxActions.delete + '\');"><i class="fa fa-trash-o"></i></a>';
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


        },
        deleteRow: function (that, action) {

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
                                $thisRow.trigger(_default.events.deleted);
                                $thisRow.removeClass('row-selected');
                                table.fnDeleteRow($thisRow[0]);
                                Alert.showSuccess($resultContainer, _default.alert.messages.success.delete)
                            } else if (msg.data[0][0] == "HAS_RESERVATIONS") {
                                Alert.showWarning($resultContainer, _default.alert.messages.warning.hasReservations, 6000);
                            } else if (msg.data[0][0] == "HAS_ROOMS") {
                                Alert.showWarning($resultContainer, _default.alert.messages.warning.hasRooms, 6000);
                            } else {
                                Alert.showError($resultContainer, _default.alert.messages.error.delete);
                            }
                        } catch (error) {
                            Alert.showError($resultContainer, _default.alert.messages.error.js + error);
                        }
                    },
                    error: function (msg) {
                        Alert.showError($resultContainer, _default.alert.messages.error.delete);
                    }
                });
            }
        },
        editRow: function (that, actionEdit, actionUpdate, editUrl, editTitle) {

            var $this = $(that);
            var $thisRow = $this.parents('tr');
            var index = $thisRow.find('td:first').text();
            var table = $this.parents('table').dataTable();
            var resultContainerId = 'server-messages';
            var $resultContainer = jQuery('#' + resultContainerId);

            var modalId = 'edit-modal';

            $.ajax({
                type: 'POST',
                url: actionEdit,
                data: {dataFrom: "{'index':'" + index + "'}"},
                success: function (msg) {
                    try {
                        if (msg.data[0][0] == "success") {

                            Modal.generate(modalId, editTitle, "", "Update", '#', false, true);
                            if (editUrl != "") {
                                Includer.load(editUrl, modalId + '-body');
                                Modal.show(modalId);

                                var $btn = Modal.getBtn(modalId);
                                setTimeout(function () {
                                    var formId = $('#' + modalId).find('form').attr('id');
                                    _updateChosenSelect(formId);
                                }, 1000);

                                $btn.on('click', function () {
                                    var formId = $('#' + modalId).find('form').attr('id');
                                    Submitter.update(formId, actionUpdate, index, resultContainerId, modalId);
                                });
                            }
                        } else {
                            Alert.showError($resultContainer, _default.alert.messages.error.edit);
                        }
                    } catch (error) {
                        Alert.showError($resultContainer, _default.alert.messages.error.js + error);
                    }
                },
                error: function (msg) {
                    Alert.showError($resultContainer, _default.alert.messages.error.edit);
                }
            });
        },
        onRowSelected: function (tableId, fn, fnArgs) {
            $('body').on(_default.events.selected, function (e, table_id, index, label) {
                if (index) {
                    if (tableId == table_id) {
                        fnArgs["index"] = index;
                        fnArgs["label"] = label;
                        fn.call(this, fnArgs);
                    }
                }
            });
        }

    }

})(jQuery, App.Components.Generator.Alert, App.Components.Includer, App.Components.Generator.Modal, App.Components.Form.Submitter);