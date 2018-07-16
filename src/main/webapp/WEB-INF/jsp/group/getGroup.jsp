<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ include file="/WEB-INF/jsp/aside.jsp" %>

<h1>${group.nameGroup}</h1>
<c:if test="${group.speciality ne null} ">
    <p><b>Специальность обучения:</b> ${group.speciality.name}</p>
</c:if>

<p><b>Специальность обучения:</b>
    <c:choose>
        <c:when test="${group.speciality eq null}">
            Рекомендуется назначить группе
            <a href="/speciality/specialityAll">специальность</a>
            обучения
        </c:when>
        <c:otherwise>
            ${group.speciality.name}
            <c:if test="${group.speciality.isActive ne 0}">
                (архивная)
            </c:if>
        </c:otherwise>
    </c:choose>
</p>
<p><b>Год начала обучения:</b> ${group.yStart}</p>


<h3>Список студентов:</h3>
<c:if test="${fn:length(group.personSet) eq 0}">
    <p>Нет ни одного обучающегося</p>
</c:if>
<c:if test="${fn:length(group.personSet) gt 0}">
    <div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Имя</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="st" items="${group.personSet}" varStatus="counter">
            <tr>
                <td> ${counter.count} </td>
                <td> ${st.name} </td>
                <td>
                    <a href="/group/delStudent?groupId=${group.id}&selectedStudent=${st.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${fn:length(students) gt 0}">
    <form class="editForm">
        <h5>Добавить нового студента</h5>
        <input type="hidden" name="groupId" value="${group.id}">
        <p><select size="1" name="selectedStudent" required>
            <c:forEach var="student" items="${students}">
                <option value="${student.id}">${student.name}</option>
            </c:forEach>
        </select></p>
        <p>
            <button class="submit" type="submit" formaction="addStudent" method="post">Добавить
            </button>
        </p>
    </form>
</c:if>

<c:if test="${fn:length(students) eq 0}">
    <p>В системе нет ни одного подходящего студента. Рекомендуется
        <a href="/person/addOrUpdate">добавить</a> нового студента или назначить группе
        <a href="/speciality/specialityAll">специальность</a>
        обучения.
    </p>
</c:if>

<%@ include file="/footer.jsp" %>