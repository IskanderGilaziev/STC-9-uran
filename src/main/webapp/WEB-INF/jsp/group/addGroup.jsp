<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ include file="/WEB-INF/jsp/aside.jsp" %>

<c:choose>
    <c:when test="${group eq null}">
        <h1>Добавление новой группы </h1>
    </c:when>
    <c:otherwise>
        <h1>Редактирование группы </h1>
    </c:otherwise>
</c:choose>

<form class="editForm" method="post" name="editForm">
    <%--<form class="editForm" action="/addGroup" method="post" name="editForm">--%>
    <ul>
        <li>
            <label for="nameGroup">Название группы</label>
            <input id="nameGroup" type="text" placeholder="Название группы" required name="nameGroup"
            <c:if test="${group ne null}">
                   value="${group.nameGroup}"
            </c:if>
            >
        </li>
        <li>
            <label for="yStart">Год начала обучения</label>
            <input id="yStart" type="number" required name="yStart"
            <c:choose>
            <c:when test="${group ne null}">
                   value="${group.yStart}"
            </c:when>
            <c:when test="${yCurrent ne null}">
                   value="${yCurrent}"
            </c:when>
            </c:choose>
            >
        </li>
        <li>
            <label for="specialityId">Программа:</label>
            <select id="specialityId" name="specialityId">
                <c:choose>
                    <c:when test="${group ne null}">
                        <option value="${group.speciality.id}" selected>
                                ${group.speciality.name}
                        </option>
                    </c:when>
                    <c:otherwise>
                        <option value="0">Выберите из списка</option>
                        <c:forEach var="spec" items="${specialityList}">
                            <option value="${spec.id}">${spec.name}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
        </li>
        <li>
            <c:choose>
                <c:when test="${group eq null}">
                    <button class="submit" type="submit" formaction="addGroup" method="post">Создать</button>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="groupId" id="groupId" value="${group.id}">
                    <button class="submit" type="submit" formaction="updateGroup" method="post">Сохранить</button>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</form>
<%@ include file="/footer.jsp" %>