<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Список специальностей</h1>
<p><b><a href="/speciality/addSpeciality">Добавить новую специальность</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название специальности</th>
            <th>Срок обучения</th>
            <th>Кол-во дисциплин</th>
            <th>Кол-во групп</th>
            <th>Архивная?</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="speciality" items="${specialityList}" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td><a href="/speciality/speciality?id=${speciality.id}">${speciality.name}</a></td>
            <td>${speciality.yTotal}</td>
            <td>${fn:length(speciality.subjectSet)}</td>
            <td>${fn:length(speciality.teamSet)}</td>
            <td>
                <c:choose>
                    <c:when test="${speciality.isActive eq 0}">
                        действующая
                    </c:when>
                    <c:otherwise>
                        архивная
                    </c:otherwise>
                </c:choose>
            </td>
            <td><a href="/speciality/updateSpeciality?id=${speciality.id}">редактировать</a></td>
            <td><a href="/speciality/deleteSpeciality?id=${speciality.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>