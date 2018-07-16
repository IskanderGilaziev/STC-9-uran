<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>Список студентов</h1>
<p><b><a href="/person/addPerson">Добавить нового студента</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Имя студента</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="person" items="${personList}">
        <tr>
            <td>${person.id}</td>
            <td><a href="/person/person?id=${person.id}">${person.name}</a></td>
            <td><a href="/person/deletePerson?id=${person.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>