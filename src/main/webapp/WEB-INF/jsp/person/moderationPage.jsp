<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<form class="editForm" action="/person/moderation" method="post" name="editForm">
    <h1>Пользователь с данными:</h1>
    <h3>${unmoderatedPerson.name}</h3>

    <p><b>День рождения:</b>
        <c:if test="${unmoderatedPerson.birthday ne null}">
            ${unmoderatedPerson.birthday}
        </c:if>
        <c:if test="${unmoderatedPerson.birthday eq null}">
            Не указано
        </c:if>
    </p>

    <p><b>Адрес:</b>
        <c:if test="${unmoderatedPerson.email ne null}">
            ${unmoderatedPerson.email}
        </c:if>
        <c:if test="${unmoderatedPerson.email eq null}">
            Не указано
        </c:if>
    </p>

    <p><b>Права:</b> ${unmoderatedPerson.status}</p>
    <c:if test="${unmoderatedPerson.user != null}">
        <p><b>Логин: </b>${unmoderatedPerson.user.login}</p>
        <p><b>Права в системе: </b>${unmoderatedPerson.user.role}</p>
        <p><b>Доступ в систему:</b>
            <c:if test="${unmoderatedPerson.user.enabled eq 1}">
                Разрешен
            </c:if>
            <c:if test="${unmoderatedPerson.user.enabled ne 1}">
                Запрещен
            </c:if>
        </p>
    </c:if>
    <p>хочет получить доступ в систему.</p>

    <p>Есть ли человек с указанными данными в системе?</p>
    <%--************************************************--%>
    <p><input type="hidden" name="newPerson" value="${unmoderatedPerson.id}"></p>
    <table class="table table-striped">
        <c:forEach var="person" items="${allegedPerson}">
            <tr>
                <td><input type="radio" name="oldPerson" value="${person.id}"></td>
                <td>
                    <c:if test="${person.name ne null}">
                        ${person.name}
                    </c:if>
                    <c:if test="${person.name eq null}">
                        -
                    </c:if>
                </td>

                <td>
                    <c:if test="${person.birthday ne null}">
                        ${person.birthday}
                    </c:if>
                    <c:if test="${person.birthday eq null}">
                        -
                    </c:if>
                </td>

                <td>
                    <c:if test="${person.email ne null}">
                        ${person.email}
                    </c:if>
                    <c:if test="${person.email eq null}">
                        -
                    </c:if>
                </td>

                <td>${person.status}</td>
            </tr>

        </c:forEach>
    </table>
    <button class="submit" type="submit">Назначить</button>

</form>
<%@ include file="../../../footer.jsp" %>