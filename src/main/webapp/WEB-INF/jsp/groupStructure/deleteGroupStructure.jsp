<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ include file="/WEB-INF/jsp/aside.jsp" %>

<h1>Удаление группы </h1>
<form action="/deleteGroupStructure/groupStructure" method="post">
    <label>Введите ID-группы для удаления: </label><input type="text" value="id" name="id"><BR>

    <input type="submit" value="Delete">
</form>
<%@ include file="/footer.jsp" %>