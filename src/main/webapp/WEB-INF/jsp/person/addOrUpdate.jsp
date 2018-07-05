<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<form class="editForm" action="/person/addOrUpdate" method="post" name="editForm">
    <c:if test="${action=='update'}">
        <input type="hidden" name="id" value="${person.id}">
    </c:if>
    <c:if test="${action=='add'}">
        <input type="hidden" name="id" value="0">
    </c:if>
    <input type="hidden" name="action" value="${action}">

    <c:if test="${action=='update'}">
        <h2>Редактирование</h2>
    </c:if>
    <c:if test="${action=='add'}">
        <h2>Добавление нового персонажа</h2>
    </c:if>
    <ul>
        <li>
            <label for="name">Имя:</label>
            <input id="name" type="text" placeholder="Иван Иванов" required
                   value="<c:if test="${action=='update'}">${person.name}</c:if>" name="name"/>
        </li>

        <li>
            <label for="birthday">День рождения:</label>
            <input id="birthday" type="text" placeholder="1970-01-01" required
                   value="<c:if test="${action=='update'}">${person.birthday}</c:if>" name="birthday"/>
        </li>

        <li>
            <label for="email">Email:</label>
            <input id="email" type="email" placeholder="ivan@example.ru" required
                   value="<c:if test="${action=='update'}">${person.email}</c:if>" name="email"/>
            <span class="form_hint">Proper format "name@something.com"</span>
        </li>
        <li>
            <label for="status">Роль:</label>
            <select id="status" name="status">
                <c:forEach var="rol" items="${statusList}">
                    <%--<option value="${rol.ordinal()}">${rol.name()}</option>--%>
                    <option value="${rol.ordinal()}"
                            <c:if test="${person.status eq rol}">selected</c:if>>${rol.name()}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <button class="submit" type="submit">OK</button>
        </li>
    </ul>
</form>
<%@ include file="../../../footer.jsp" %>