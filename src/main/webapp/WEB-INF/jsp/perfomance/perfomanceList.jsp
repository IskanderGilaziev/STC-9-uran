<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Преподаватель - Предмет</h1>
<p><b><a href="/teacherSubject/addOrUpdateTL">Добавить</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th></th>
            <th>Преподаватель</th>
            <th>Предмет</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="teacherSubject" items="${teacherSubjectList}">
            <tr>
                <td></td>
                <td>${teacherSubject.subjectName}</td>
                <td>${teacherSubject.teacherName}</td>
                <td><a href="/teacherSubject/updateTeacherSubject?id=${teacherSubject.id}">редактировать</a></td>
                <td><a href="/teacherSubject/deleteTeacherSubject?id=${teacherSubject.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>