<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>${subject.name}</h1>
<p><b>Название предмета:</b> ${subject.name}</p>
<p>Занятия: <a href="/subject/addLesson?subjId=${subject.id}">Добавить</a></p>
<ul>
    <c:forEach var="lesson" items="${lessonList}">
    <li>${lesson.theme} (<a href="/subject/updateLesson?id=${lesson.id}&subjId=${subject.id}">редактировать</a> /
        <a href="/subject/deleteLesson?id=${lesson.id}&subjId=${subject.id}">удалить</a> /
        <c:if test="${lesson.performances.size()==0}">
            <a href="/perfomance/addOrUpdatePerform?lessonId=${lesson.id}">поставить оценку</a>
        </c:if><c:if test="${lesson.performances.size()!=0}">
            <a href="/perfomance/getPerformance?lessonId=${lesson.id}">просмотреть ведомость</a>
        </c:if>)
        </li>
    </c:forEach>
</ul>
<%@ include file="../../../footer.jsp" %>