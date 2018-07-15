<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<h1>${subject.name}</h1>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Дата</th>
            <th>Тема</th>
            <th>Посещение</th>
            <th>Оценка</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="perf" items="${performanceList}" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${perf.lesson.date}</td>
                <td>${perf.lesson.theme}</td>
                <td>
                    <c:choose>
                        <c:when test="${perf.attendance}">
                            +
                        </c:when>
                        <c:otherwise>
                            H
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <c:if test="${perf.mark>0}">
                        ${perf.mark}
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../../../footer.jsp" %>