<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<form class="editForm" action="/scheduleItem/showListOfGroups" method="post" name="editForm">
    <h3>Выбирите статус групп, для которых нужно отобразить рассписание:</h3>
    <p><input name="status" type="radio" value="ARCHIVE">Группы, из архива<Br>
        <input name="status" type="radio" value="IN_PROGRESS" checked>Группы в процессе обучения<Br>
        <input name="status" type="radio" value="IN_PLAN">Группы, готовящиеся к началу обучения</p>
    <p><input type="submit"></p>
</form>
<%@ include file="../../../footer.jsp" %>