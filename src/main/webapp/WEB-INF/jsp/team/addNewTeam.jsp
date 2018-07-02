<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<form class="editForm" action="/team/getNewTeam" method="post" name="editForm">
    <h2>Создание новой учебной группы</h2>

    <ul>
        <li>
            <label for="fullName">Полное название группы:</label>
            <input id="fullName" type="text" placeholder="Полное название" required
                   name="fullName"/>
        </li>

        <li>
            <label for="shortName">Шифр:</label>
            <input id="shortName" type="text" placeholder="Краткое название" required
                   name="shortName"/>
        </li>

        <li>
            <label for="yStart">Год начала обучения:</label>
            <input id="yStart" type="text" placeholder="2015" required
                   name="yStart"/>
        </li>

        <li>
            <label for="yCurrent">Текущий год:</label>
            <input id="yCurrent" type="text" placeholder="2015" required
                   name="yCurrent"/>
        </li>

        <li>
            <label for="yLast">Год окончания обучения:</label>
            <input id="yLast" type="text" placeholder="2021" required
                   name="yLast"/>
        </li>
    </ul>

    <button class="submit" type="submit">OK</button>
</form>
<%@ include file="../../../footer.jsp" %>