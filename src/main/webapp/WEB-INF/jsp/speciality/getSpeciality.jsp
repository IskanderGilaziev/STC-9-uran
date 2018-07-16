<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>${speciality.name}</h1>

<p><b>Срок обучения (в годах):</b>
    ${speciality.yTotal}
</p>

<p>

    <b>Актуальность:</b>
    <c:if test="${speciality.isActive eq 0}">
        действующая
        <c:set var="buttonName" value="поместить в архив"/>
    </c:if>
    <c:if test="${speciality.isActive ne 0}">
        архивная
        <c:set var="buttonName" value="сделать активной"/>
    </c:if>
</p>
<form class="editForm">
    <input type="hidden" name="specialityId" value="${speciality.id}">
    <button class="submit" type="submit" formaction="changeActivity" method="post">${buttonName}
    </button>
</form>

<h3>Список учебных дисциплин:</h3>
<c:if test="${fn:length(speciality.subjectSet) eq 0}">
    Нет ни одной дисциплины
</c:if>
<c:if test="${fn:length(speciality.subjectSet) gt 0}">
    <div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Дисциплина</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="subj" items="${speciality.subjectSet}" varStatus="counter">
            <tr>
                <td> ${counter.count} </td>
                <td> ${subj.name} </td>
                <td>
                    <c:if test="${speciality.isActive eq 0}">
                        <a href="/speciality/delSubject?specialityId=${speciality.id}&selectedSubject=${subj.id}">Удалить</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${fn:length(subjectList) eq 0}">
    <p>В системе нет подходящих учебных дисциплин для добавления.
        <c:if test="${speciality.isActive ne 0}">
            Нельзя добавить новую учебную дисциплину к архивной специальности
        </c:if>
        <c:if test="${speciality.isActive eq 0}">
            Рекомендуем перейти в раздел
            <a href="/subject/addOrUpdateSubject">Создать</a>
            новый предмет.
        </c:if>
    </p>
</c:if>

<c:if test="${fn:length(subjectList) gt 0}">
    <form class="editForm">
        <h5>Добавить предмет</h5>
        <input type="hidden" name="specialityId" value="${speciality.id}">
        <p><select size="1" name="selectedSubject" required>
            <c:forEach var="subject" items="${subjectList}">
                <option value="${subject.id}">${subject.name}</option>
            </c:forEach>
        </select></p>
        <p>
            <button class="submit" type="submit" formaction="addSubject" method="post">Добавить
            </button>
        </p>
    </form>
</c:if>

<h3>Список учебных групп:</h3>
<c:if test="${fn:length(speciality.teamSet) eq 0}">
    <p>Нет ни одной учебной группы, обучающейся по данной специльности</p>
</c:if>

<c:if test="${fn:length(speciality.teamSet) gt 0}">
    <div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название группы</th>
            <th>Год начала обучения</th>
            <th>Число студентов</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="team" items="${speciality.teamSet}" varStatus="counter">
            <tr>
                <td> ${counter.count} </td>
                <td> ${team.nameGroup} </td>
                <td> ${team.yStart} </td>
                <td> ${fn:length(team.personSet)} </td>
                <td>
                    <c:if test="${speciality.isActive eq 0}">
                        <a href="/speciality/delTeam?specialityId=${speciality.id}&selectedTeam=${team.id}">Удалить</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${fn:length(teamList) eq 0}">
    <p>В системе нет подходящих групп для добавления.
        <c:if test="${speciality.isActive ne 0}">
            Нельзя добавить новую учебную группу к архивной специальности
        </c:if>
        <c:if test="${speciality.isActive eq 0}">
            Рекомендуем перейти в раздел
            <a href="/group/addOrUpdateGroup">Создать</a>
            новую группу.
        </c:if>
    </p>
</c:if>
<c:if test="${fn:length(teamList) gt 0}">
    <form class="editForm">
        <h5>Добавить группу</h5>
        <input type="hidden" name="specialityId" value="${speciality.id}">
        <p><select size="1" name="selectedTeam" required>
            <c:forEach var="t" items="${teamList}">
                <option value="${t.id}">${t.nameGroup}</option>
            </c:forEach>
        </select></p>
        <p>
            <button class="submit" type="submit" formaction="addTeam" method="post">Добавить
            </button>
        </p>
    </form>
</c:if>

<%@ include file="../../../footer.jsp" %>