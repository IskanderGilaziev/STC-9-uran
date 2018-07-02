<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<h1>${team.fullName}</h1>
<p><b>Сокращенное наименование:</b>
    <c:if test="${team.shortName ne null}">
        ${team.shortName}-${team.yearCurrent-team.yearStart+1}
    </c:if>
    <c:if test="${team.shortName eq null}">
        Не указано
    </c:if>
</p>

<p><b>Год начала обучения:</b>
    ${team.yearStart}
</p>
<p><b>Текущий курс:</b>
    ${team.yearCurrent-team.yearStart+1}
</p>
<p><b>Планируемый год окончания:</b>
    ${team.yearTotal}
</p>

<p><b>Список студентов в группе:</b>
<c:if test="${fn:length(team.studentSet) gt 0}">
    <br>
    <ul>
    <c:forEach var="student" items="${team.studentSet}">
        <li>
        <p>${student.person.name}
            <b><a href="/team/remStFromTeam?id=${team.id}&pers=${student.person.id}">удалить уз группы</a> </b>
        </p></li>
    </c:forEach>
    </ul>
</c:if>
<c:if test="${fn:length(team.studentSet) eq 0}">
    В группе нет ни одного студента
</c:if>
<br>
<a href="/team/addStudent?id=${team.id}">Добавить студента в группу</a>
</p>

<p><b>Список дисциплин, которые должны изучить студенты группы:</b>
<c:if test="${fn:length(team.subjectSet) gt 0}">
    <br>
    <ul>
    <c:forEach var="subject" items="${team.subjectSet}">
        <li>
        <p>${subject.name}</p></li>
    </c:forEach>
    </ul>
</c:if>
<c:if test="${fn:length(team.subjectSet) eq 0}">
    Еще ни одного предмета не указано
</c:if>
<br>
<a href="/team/addSubject?id=${team.id}">Добавить дисциплину в учебный процесс группы</a>
</p>
<%@ include file="../../../footer.jsp" %>