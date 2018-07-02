<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<form class="editForm" action="/subject/addTeacher" method="post" name="editForm">
    <h1>${subject.name}</h1>
    <p><b>Название предмета:</b> ${subject.name}</p>
    <c:if test="${teacherCount gt 0}">
        <p><b>Данный предмет ведут следующие преподаватели:</b></p>
        <ul>
            <c:forEach var="exitingTeacher" items="${subject.teacherSet}">
                <ol>${exitingTeacher.person.name}</ol>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${teacherCount eq 0}">
        <p><b>На данный момент ни один преподаватель не ведет данный предмет</b></p>
    </c:if>
    <p><a href="/subject/addTeacher?id=${subject.id}">назначить нового преподавателя преподавателя</a></p>
</form>
<%@ include file="../../../footer.jsp" %>