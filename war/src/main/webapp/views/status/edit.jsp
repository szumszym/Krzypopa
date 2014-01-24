<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="status-edit" class="form-horizontal">

    <div class="form-group">
        <label class="control-label col-lg-4">Name</label>

        <div class="col-lg-8">
            <input type="text" id="aa-name" name="type" placeholder="Name"
                   class="form-control" value='<s:property value="%{#session.edit.type}" />'>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-lg-4">Description</label>

        <div class="col-lg-8">
            <textarea name="description" placeholder="Description"
                      class="form-control"><s:property value="%{#session.edit.description}"/></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-lg-4">Select Color</label>

        <div class="col-lg-3">
            <input type="color" name="color" class="form-control" value='<s:property value="%{#session.edit.color}" />'>
        </div>
    </div>
</form>

<script src="./assets/js/app/views/status/edit.js"></script>