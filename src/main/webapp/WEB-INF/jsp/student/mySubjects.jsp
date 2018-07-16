<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>
<h1>${student.name}</h1>
<h2>Академическая успеваемость:</h2>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Предмет</th>
            <th>Кол-во уроков</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="subject" items="${subjectList}" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="/perfomance/myMarks?subject=${subject.id}">${subject.name}</a></td>
                <td>${fn:length(subject.lessonList)}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>