<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ include file="/aside.jsp" %>

<h1>Добавление новой группы </h1>
<form action="/addGroup" method="post">
    <ul>
        <li>
            <label for="cur_semester_education">Cеместр:</label>
            <input id="cur_semester_education" type="text" placeholder="semester education" required
                   value="${group.program}" name="cur_semester_education"/>
        </li>

        <li>
            <label for="program">Программа семестра:</label>
            <input id="program" type="text" placeholder="program" required
                   value="${group.program}" name="program"/>
        </li>

        <li>
            <label for="programs">Программа:</label>
            <select id="programs" name="programs">
                <c:forEach var="programs" items="${listProgram}">
                    <option value="${program.id}"
                            <c:if test="${group.id eq programs.id}">selected</c:if>>${programs.semester}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <button class="submit" type="submit">Good</button>
        </li>
    </ul>
</form>
<%@ include file="/footer.jsp" %>