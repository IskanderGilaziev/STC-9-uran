<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>Список предметов</h1>
<p><b><a href="/subject/addOrUpdateSubject">Добавить новый предмет</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название предмета</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="subject" items="${subjectList}" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>
                <a href="/subject/subject?id=${subject.id}">${subject.name}</a>
            </td>
            <td><a href="/subject/updateSubject?id=${subject.id}">редактировать</a></td>
            <td><a href="/subject/deleteSubject?id=${subject.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>