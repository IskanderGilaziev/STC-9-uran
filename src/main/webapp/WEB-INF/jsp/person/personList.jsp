<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<h1>Список персон</h1>
<p><b><a href="/person/addOrUpdate">Добавить новую персону</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Личные данные</th>
            <th></th>
            <th></th>
            <th>Данные о регистрации</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="person" items="${personList}" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="/person/person?id=${person.id}">${person.name}</a></td>
                <td><a href="/person/updatePerson?id=${person.id}">редактировать</a></td>
                <td><a href="/person/deletePerson?id=${person.id}">удалить</a></td>

                <td>
                    <c:if test="${person.status eq unknownStatus}">
                        <a href="/person/moderation?id=${person.id}">требуется модерация пользователя</a>
                    </c:if>

                    <c:if test="${person.status ne unknownStatus}">
                        <label><c:out value="${person.status.name()}"/></label>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>