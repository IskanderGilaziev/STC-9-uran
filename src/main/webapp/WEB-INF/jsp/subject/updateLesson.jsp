<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Редактирование</h1>
<form class="editForm" action="/subject/updateLesson" method="post" name="editForm">
    <input type="hidden" name="subjectId" value="${subject.id}">
    <ul>
        <li><label for="teacherItem">Выберите преподавателя: </label>
            <select id="teacherItem" name="teacherItem">
                <c:forEach var="teacher" items="${teacherList}">
                    <option value="${teacher.id}"
                            <c:if test="${lesson.teacherItem eq teacher.id}">selected</c:if>>${teacher.name}</option>
                </c:forEach>
            </select></li>
        <li><label for="date">Дата проведения:</label>
            <input id="date" type="text" required
                   value="${lesson.date}" name="date"/></li>
        <li><label for="theme">Тема: </label>
            <input id="theme" type="text" required
                   value="${lesson.theme}" name="theme"><BR>
        <li><label for="homework">Домашнее задание: </label>
            <input id="homework" type="text" required
                   value="${lesson.homework}" name="homework"></li>
        <li>
            <button class="submit" type="submit">OK</button>
        </li>
    </ul>
</form>
<%@ include file="../../../footer.jsp" %>