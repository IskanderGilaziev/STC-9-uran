<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>${person.name}</h1>

<p><b>День рождения:</b>
    <c:if test="${person.birthday ne null}">
        ${person.birthday}
    </c:if>
    <c:if test="${person.birthday eq null}">
        Не указано
    </c:if>
</p>

<p><b>Электронная почта:</b>
    <c:if test="${person.email ne null}">
        ${person.email}
    </c:if>
    <c:if test="${person.email eq null}">
        Не указано
    </c:if>
</p>

<p><b>Роль в учебном процессе:</b>
    <c:choose>
        <c:when test="${person.status eq 'admin'}">
            Администратор системы
        </c:when>
        <c:when test="${person.status eq 'manager'}">
            Сотрудник учебного отдела
        </c:when>
        <c:when test="${person.status eq 'teacher'}">
            Преподаватель
        </c:when>
        <c:when test="${person.status eq 'student'}">
            студент
            <c:if test="${person.team ne null}">
                группы ${person.team.nameGroup}, ${yCurrent - person.team.yStart+1}-й курс
            </c:if>
            <c:if test="${person.team eq null}">
                . Рекомендуется <a href="/group/groupAll">зачислить</a> в учебную группу.
            </c:if>
        </c:when>
        <c:otherwise>
            требуется уточнение
        </c:otherwise>
    </c:choose>
</p>

<c:if test="${person.user != null}">
    <p><b>Логин: </b>${person.user.login}</p>
    <p><b>Права в системе: </b>${person.user.role}</p>
    <p><b>Доступ в систему:</b>
        <c:if test="${person.user.enabled eq 1}">
            Разрешен
        </c:if>
        <c:if test="${person.user.enabled ne 1}">
            Запрещен
        </c:if>
    </p>
    <p>
        <a href="/person/person/ban?id=${person.id}">Изменить доступ в систему</a>
    </p>

</c:if>
<c:if test="${person.user eq null}">
    В системе не зарегистрирован
</c:if>
<%@ include file="../../../footer.jsp" %>