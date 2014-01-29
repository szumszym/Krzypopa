<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="step-second" class="container">
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-body">
                <strong>Select Rooms</strong>
            </div>
            <div class="panel-footer">
                <div id="sortableTable-12" class="body collapse in">
                    <table id="step-second-room-table"
                           class="table table-bordered table-condensed sortableTable responsive-table table-striped"
                           style="background-color: white;">
                    </table>
                </div>
            </div>
        </div>
        <div class="container col-lg-8" style=" margin: 0 auto; float: none;">
            <div class="panel panel-default">
                <div class="panel-body">
                    <strong>Reservation data</strong>
                </div>
                <div class="panel-footer">

                    <form id="step-second-form" action="secondStep" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-lg-4">Name</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input type="text" name="name" placeholder="Reservation" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-4">Date from</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input id="date_from" type="date" name="date_from" class="form-control"
                                           value='<s:property value="%{#session.dateFrom}" />' disabled>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-4">Date to</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input type="date" name="date_to" class="form-control"
                                           value='<s:property value="%{#session.dateTo}" />' disabled>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-4">Count of person</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input type="text" name="person_count" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-4">Rooms</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <select id="step-second-room-select" data-placeholder="Choose rooms..."
                                            name="room_ids" class="form-control" tabindex="2">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-4">Cost</label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input type="text" name="price" class="form-control" disabled>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-4"></label>

                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input id="back-second" type="button" class="btn btn-lg btn-warning"
                                           data-original-title="" title="" value="Back"
                                           style="margin-left: -9px; margin-right: 11px;">
                                    <input type="submit" name="submit" class="btn btn-lg btn-success"
                                           data-original-title="" title="" value="Continue"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="./assets/js/app/views/guest/step-second.js"></script>
<script>
    $(".chzn-select").chosen();
</script>