<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>Список учебных программ</h1>
<p><b><a href="/program/addOrUpdateProgram">Добавить новую учебную программу</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Специальность</th>
            <th>кол-во семестров</th>
            <th>Предмет</th>
            <th>коп-во часов</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="program" items="${programList}">
            <tr>
                <td><a href="/program/program?id=${program.id}">${program.specialty.name.toString()}</a></td>
                <td><a href="/program/program?id=${program.id}">${program.semester}</a></td>
                <td><a href="/program/program?id=${program.id}">${program.subject.name.toString()}</a></td>
                <td><a href="/program/program?id=${program.id}">${program.hours}</a></td>

                <td><a href="/program/updateProgram?id=${program.id}">редактировать</a></td>
                <td><a href="/program/deleteProgram?id=${program.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
<%@ include file="../../../footer.jsp" %>