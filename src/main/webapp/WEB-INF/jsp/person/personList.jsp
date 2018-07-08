<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<h1>Список персон</h1>
<p><b><a href="/teacher/addOrUpdate">Добавить новую персону</a></b></p>
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
        <c:forEach var="teacher" items="${personList}" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="/teacher/teacher?id=${teacher.id}">${teacher.name}</a></td>
                <td><a href="/teacher/updatePerson?id=${teacher.id}">редактировать</a></td>
                <td><a href="/teacher/deletePerson?id=${teacher.id}">удалить</a></td>

                <td>
                    <c:if test="${teacher.status eq unknownStatus}">
                        <a href="/teacher/moderation?id=${teacher.id}">требуется модерация пользователя</a>
                    </c:if>

                    <c:if test="${teacher.status ne unknownStatus}">
                        <c:if test="${teacher.user eq null}">
                            не зарегистрирован
                        </c:if>
                        <c:if test="${teacher.user ne null}">
                            <c:if test="${teacher.user.enabled eq 1}">
                                Зарегистрирован, вход разрешен
                            </c:if>
                            <c:if test="${teacher.user.enabled ne 1}">
                                Зарегистрирован, вход запрещен
                            </c:if>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>