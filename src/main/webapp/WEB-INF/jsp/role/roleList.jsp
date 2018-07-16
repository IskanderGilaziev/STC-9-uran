<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>Список типов пользователей</h1>
<p><b><a href="/status/addOrUpdateRole">Добавить тип пользователя</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Тип пользователя</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="status" items="${roleList}">
            <tr>
                <td>${status.id}</td>
                <td><a href="/status/status?id=${status.id}">${status.name}</a></td>
                <td><a href="/status/updateRole?id=${status.id}">редактировать</a></td>
                <td><a href="/status/deleteRole?id=${status.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- .container-->

<%@ include file="../../../footer.jsp" %>