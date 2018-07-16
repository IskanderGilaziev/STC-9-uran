<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>Добавление группы</h1>
<p><b><a href="/group/addGroup">Добавить новую учебную группу</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название группы</th>
            <th>Специальность</th>
            <th>Курс</th>
            <th>Число студентов</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="group" items="${groupList}" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="/group/group?id=${group.id}">${group.nameGroup}</a></td>

                <td>
                    <c:if test="${group.speciality != null}">
                        <a href="/speciality/speciality?id=${group.speciality.id}">${group.speciality.name}</a>
                    </c:if>
                    <c:if test="${group.speciality == null}">
                        специальность не задана
                    </c:if>
                </td>
                <td>
                        ${yCurrent-group.yStart+1}
                </td>
                <td>
                    <c:if test="${group.personSet != null}">
                        ${fn:length(group.personSet)}
                    </c:if>
                </td>
                <td><a href="/group/updateGroup?id=${group.id}">редактировать</a></td>
                <td><a href="/group/deleteGroup?id=${group.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>