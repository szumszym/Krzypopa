<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="reservation-edit" class="form-horizontal">

    <div class="form-group">
        <label class="control-label col-lg-4">Name</label>

        <div class="col-lg-8">
            <input type="text" name="name" placeholder="Reservation"
                   class="form-control" value='<s:property value="%{#session.edit.name}" />'>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-lg-4">Status</label>

        <div class="col-lg-8">
            <select id="reservation-status-select" name="status_id" class="form-control"
                    value='<s:property value="%{#session.edit.status.id}" />'>
            </select>
        </div>
    </div>
</form>
<script src="./assets/js/app/views/reservation/edit.js"></script>