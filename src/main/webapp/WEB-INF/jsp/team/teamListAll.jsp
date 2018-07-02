<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<h1>Список групп</h1>
<p><b><a href="/team/getNewTeam">Добавить новую учебную группу</a></b></p>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Полное название группы</th>
            <th>Курс обучения</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="team" items="${teams}" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="/team/getTeam?id=${team.id}">${team.fullName}</a></td>
                <td>${team.yearCurrent-team.yearStart+1}</td>
                <td><a href="/team/editOnlyTeam?id=${team.id}">редактировать данные группы</a></td>
                <td><a href="/team/deleteWholeTeam?id=${team.id}">удалить всю группу</a></td>
                <td><a href="/team/editTeamInner?id=${team.id}">управление группой</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>