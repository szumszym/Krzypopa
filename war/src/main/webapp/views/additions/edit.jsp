<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form class="form-horizontal" id="additions-edit">
    <div class="form-group">
        <label class="control-label col-lg-4">Name</label>

        <div class="col-lg-8">
            <input type="text" id="aa-name" name="name" placeholder="Name"
                   class="form-control" value='<s:property value="%{#session.edit.name}" />'>
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
        <label class="control-label col-lg-4">Price</label>

        <div class="col-lg-8">
            <input name="price" type="text" class="form-control" value='<s:property value="%{#session.edit.price}" />'>
        </div>
    </div>


    <div class="form-group">
        <label class="control-label col-lg-4"></label>

        <div class="col-lg-8">
            <div class="checkbox">
                <label>
                    <input name="published" class="uniform" type="checkbox"
                           value='<s:property value="%{#session.edit.published}" />'>Published
                </label>
            </div>
        </div>
    </div>
</form>
<script src="./assets/js/app/views/addition/edit.js"></script>