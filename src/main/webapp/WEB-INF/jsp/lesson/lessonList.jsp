<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Список студентов</h1>
<p><b><a href="/teacher/addPerson">Добавить нового студента</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Имя студента</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="teacher" items="${personList}">
        <tr>
            <td>${teacher.id}</td>
            <td><a href="/teacher/teacher?id=${teacher.id}">${teacher.name}</a></td>
            <td><a href="/teacher/deletePerson?id=${teacher.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>