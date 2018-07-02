<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Список студентов</h1>
<p><b><a href="/lesson/addLesson">Добавить новую лекцию</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название лекции</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="person" items="${personList}">
        <tr>
            <td>${person.id}</td>
            <td><a href="/lesson/lesson?id=${lesson.id}">${person.name}</a></td>
            <td><a href="/lesson/deleteLesson?id=${lesson.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>