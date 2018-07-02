<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<label>Add or update</label>
<form class="editForm" action="/subject/addOrUpdateSubject" method="post" name="editForm">
    <c:if test="${action eq 'update'}">
        <h2>Редактирование существующего предмета</h2>
    </c:if>

    <c:if test="${action eq 'add'}">
        <h2>Добавление нового предмета</h2>
    </c:if>

    <c:if test="${action=='update'}">
        <input type="hidden" name="id" value="${subject.id}">
    </c:if>
    <c:if test="${action=='add'}">
        <input type="hidden" name="id" value="0">
    </c:if>

    <label>Название дисциплины:</label>
    <p><input type="text" placeholder="Предмет" required
              value="<c:if test="${action=='update'}">${subject.name}</c:if>" name="name"/></p>
    <p>Укажите преподавателя:</p>
    <label for="teacherItem">Преподаватель:</label>
    <select id="teacherItem" name="teacher_item">
        <c:forEach var="teacher" items="${prospectiveTeachers}">
            <c:set var="teacher_item" value="${prospectiveTeachers.id}"></c:set>
            <option value="${teacher_item}"
                    <c:if test="${teacherprospectiveTeachers.teacherItem eq teacher_item}">selected</c:if>>${teacher.name}</option>
        </c:forEach>
    </select>


    <%--<input type="hidden" name="action" value="${action}">--%>

    <%--<c:if test="${action=='update'}">--%>
    <%--<h2>Редактирование</h2>--%>
    <%--</c:if>--%>
    <%--<c:if test="${action=='add'}">--%>
    <%--<h2>Добавление нового предмета</h2>--%>
    <%--</c:if>--%>
    <%--<ul>--%>
    <%--<li>--%>
    <%--<label for="name">Название:</label>--%>
    <%--<input type="text" placeholder="Предмет" required--%>
    <%--value="<c:if test="${action=='update'}">${subjectWithTeacher.name}</c:if>" name="name"/>--%>
    <%--</li>--%>
    <%----%>
                    <button class="submit" type="submit">OK</button>
    <%--</li>--%>
    <%--</ul>--%>
        </form>
<%@ include file="../../../footer.jsp" %>