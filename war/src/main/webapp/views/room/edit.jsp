<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="room-edit" class="form-horizontal">

<div class="form-group">
        <label class="control-label col-lg-4">Name</label>

        <div class="col-lg-8">
            <input type="text" name="room_name" placeholder="Room"
                   class="form-control" value='<s:property value="%{#session.editRoom.name}" />'>

        </div>
    </div>
    <!-- /.form-group -->

    <div class="form-group">
        <label class="control-label col-lg-4">Room No.</label>

        <div class="col-lg-8">
            <input type="text" name="roomno"
                   class="form-control" value='<s:property value="%{#session.editRoom.no_room}" />'>
        </div>
    </div>
    <!-- /.form-group -->

    <div class="form-group">
        <label class="control-label col-lg-4">Description</label>

        <div class="col-lg-8">
            <textarea name="description" placeholder="Description" class="form-control"><s:property
                    value="%{#session.editRoom.description}"/></textarea>
        </div>
    </div>
    <!-- /.row -->

    <div class="form-group">
        <label class="control-label col-lg-4">Count of beds</label>

        <div class="col-lg-4">
            <input type="text" name="bed_count" class="form-control"
                   value='<s:property value="%{#session.editRoom_bedCount}" />'>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-lg-4">Bed Type</label>

        <div class="col-lg-4">
            <input type="text" name="bed_type" class="form-control"
                   value='<s:property value="%{#session.editRoom_bedType}" />'>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-lg-4">Capacity</label>

        <div class="col-lg-4">
            <input disabled type="text" name="capacity" id="roa-capacity" class="form-control"
                   value='<s:property value="%{#session.editRoom.capacity}" />'>
        </div>
    </div>
    <!-- /.form-group -->

    <div class="form-group">
        <label class="control-label col-lg-4">Price</label>

        <div class="col-lg-8">
            <input name="price" type="text" class="form-control"
                   value='<s:property value="%{#session.editRoom.price}" />'>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-lg-4">Select Additions</label>

        <div class="col-lg-8">
            <select id="room-addition-select" name="addition" class="form-control" multiple
                    data-indexes='<s:property value="%{#session.editRoom_additions}" />'>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-lg-4"></label>

        <div class="col-lg-8">
            <div class="checkbox">
                <label>
                    <input name="published" class="uniform" type="checkbox"
                           value='<s:property value="%{#session.editRoom.published}" />'>Published
                </label>
            </div>
        </div>
    </div>
</form>
<script src="./assets/js/app/views/room/edit.js"></script>