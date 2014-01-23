App.Components.Table = (function ($, Alert) {
    var _default = {

    };
    return {
        create: function (params) {
            var ajaxActions = params.actions;
            var tableContainerId = params.table.id;
            var tableParams = params.table.params;
            var paramToJAVA = params.toJAVA;
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
                                $thisRow.trigger("table:rowDeleted");
                                $thisRow.removeClass('row-selected');
                                table.fnDeleteRow($thisRow[0]);
                                Alert.showSuccess($resultContainer, "Akcja usunięcia przebiegła pomyślnie.")
                            } else if (msg.data[0][0] == "HAS_RESERVATIONS") {
                                Alert.showWarning($resultContainer, "Nie można usunąć pokoju, istnieją przypisane do niego rezerwacje!", 6000);
                            } else if (msg.data[0][0] == "HAS_ROOMS") {
                                Alert.showWarning($resultContainer, "Nie można usunąć dodatku - istnieją przypisane do niego pokoje!", 6000);
                            } else {
                                Alert.showError($resultContainer, "Error occured during deleting action!");
                            }
                        } catch (error) {
                            Alert.showError($resultContainer, "JavaScript ERROR: " + error);
                        }
                    },
                    error: function (msg) {
                        Alert.showError($resultContainer, "Wystąpił bład podczas zapisu do bazy danych!");
                    }
                });
            }
        },
        onRowSelected: function (tableId, fn, fnArgs) {
            $('body').on('table:rowSelected', function (e, table_id, index, label) {
                if (index) {
                    if (tableId == table_id) {
                        fnArgs["index"] = index;
                        fnArgs["label"] = label;
                        fn.call(this, fnArgs);
                    }
                }
            });
        },
        onRowSelected_old: function (tableId, action, resultContainerId, hotelnameContainerId) {
            $('body').on('table:rowSelected', function (e, table_id, index, label) {
                if (index) {
                    if (tableId == table_id) {
                        Hotel.load(action, resultContainerId, index, hotelnameContainerId, label);
                    }
                }
            });
        },

    }

})(jQuery, App.Components.Generator.Alert);