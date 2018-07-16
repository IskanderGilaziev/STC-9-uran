<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>Добавление группы</h1>
<p><b><a href="/groupStructure/addGroupStructure">Добавить новую группу</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№ ID</th>
            <th>Группа</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="groupStructure" items="${groupStructureList}">
            <tr>
                <td>${groupStructure.id}</td>
                <td><a href="/groupStructure/updateGroupStructure?id=${groupStructure.id}">редактировать</a></td>
                <td><a href="/groupStructure/deleteGroupStructure?id=${groupStructure.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>