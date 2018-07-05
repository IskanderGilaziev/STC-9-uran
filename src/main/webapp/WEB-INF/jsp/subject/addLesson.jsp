<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<h1>Добавление нового урока</h1>

<p><b>Предмет:</b> ${subject.name}</p>

<form class="editForm" action="/subject/addLesson" method="post" name="editForm">
    <input type="hidden" name="subjectId" value="${subject.id}">
    <ul>
        <li><label for="teacherItem">Выберите преподавателя: </label>
            <select id="teacherItem" name="teacherItem">
                <c:forEach var="teacher" items="${teacherList}">
                    <option value="${teacher.id}">${teacher.name}</option>
                </c:forEach>
            </select></li>
        <li><label for="date">Дата проведения:</label>
            <input id="date" type="text" placeholder="2018-01-01" name="date"/></li>
        <li><label for="theme">Тема: </label>
            <input id="theme" type="text" placeholder="Тема" name="theme"><BR>
        <li><label for="homework">Домашнее задание: </label>
            <input id="homework" type="text" placeholder="Домашнее задание" name="homework"></li>
        <li>
            <button class="submit" type="submit">OK</button>
        </li>
    </ul>
</form>
<%@ include file="../../../footer.jsp" %>