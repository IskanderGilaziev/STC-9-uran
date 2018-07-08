<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>${teacher.name}</h1>

<p><b>День рождения:</b>
    <c:if test="${teacher.birthday ne null}">
        ${teacher.birthday}
    </c:if>
    <c:if test="${teacher.birthday eq null}">
        Не указано
    </c:if>
</p>

<p><b>Электронная почта:</b>
    <c:if test="${teacher.email ne null}">
        ${teacher.email}
    </c:if>
    <c:if test="${teacher.email eq null}">
        Не указано
    </c:if>
</p>

<p><b>Роль в учебном процессе:</b> ${teacher.status}</p>
<c:if test="${teacher.user != null}">
    <p><b>Логин: </b>${teacher.user.login}</p>
    <p><b>Права в системе: </b>${teacher.user.role}</p>
    <p><b>Доступ в систему:</b>
        <c:if test="${teacher.user.enabled eq 1}">
            Разрешен
        </c:if>
        <c:if test="${teacher.user.enabled ne 1}">
            Запрещен
        </c:if>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/teacher/ban?id=${teacher.id}">Изменить доступ в систему</a>
    </p>

</c:if>
<c:if test="${teacher.user eq null}">
    В системе не зарегистрирован
</c:if>
<%--<p>:</b> ${teacher.status}</p>--%>
<%@ include file="../../../footer.jsp" %>