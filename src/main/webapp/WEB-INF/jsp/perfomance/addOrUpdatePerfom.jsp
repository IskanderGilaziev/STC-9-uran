<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<form class="editForm" action="/perfomance/addOrUpdatePerform" method="post" name="editForm">
    <c:if test="${action=='update'}">
        <input type="hidden" name="lessonId" value="${lesson.id}">
    </c:if>
    <c:if test="${action=='add'}">
        <input type="hidden" name="lessonId" value="${lesson.id}">
        <input type="hidden" name="id" value="0">
    </c:if>
    <input type="hidden" name="action" value="${action}">

    <c:if test="${action=='update'}">
        <h2>Редактирование</h2>
    </c:if>
    <c:if test="${action=='add'}">
        <h2>${lesson.date} ${lesson.theme}</h2>
    </c:if>
    <table class="table">
        <col width="15px">
        <col width="250px">
        <col width="30px">
        <col width="30px">
        <tr>
            <th>№</th>
            <th>Имя студента</th>
            <th>Присутствие</th>
            <th>Оценка</th>
        </tr>
        <c:forEach var="student" items="${studentList}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>
                    <input type="hidden" name="studentsId" value="${student.id}">
                    <select id="attendances" name="attendances">
                        <option value="1">Присутствовал</option>
                        <option value="0">Отсутствовал</option>
                    </select>
                </td>
                <td>
                    <select id="marks" name="marks">
                        <option value="0">-</option>
                        <option value="5">Отлично</option>
                        <option value="4">Хорошо</option>
                        <option value="3">Удовлетворительно</option>
                        <option value="2">Неудовлетворительно</option>
                    </select>
                </td>
            </tr>
        </c:forEach>
    </table>
    <li>
        <button class="submit" type="submit">OK</button>
    </li>
    </ul>
</form>
<%@ include file="../../../footer.jsp" %>