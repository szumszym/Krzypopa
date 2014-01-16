<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head></head>
<body>
<h1>Admin Dashboard - Settings</h1>

<%--TODO: submit odsyla na zla strone, przerobic na ajaxowy submit..--%>
<s:form action="dashboard">
    <s:textfield name="login" value="%{#session['login']}" label="Change Username"/>
    <s:password name="oldPassword" value="" label="Current Password"/>
    <s:password name="newPassword" value="" label="New Password"/>
    <s:submit/>
</s:form>

</body>
</html>
