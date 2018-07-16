<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ include file="/WEB-INF/jsp/aside.jsp" %>

<h1>Группа ${group.nameGroup}</h1>
<p><b>Специальность обучения:</b> ${group.speciality.name}</p>
<p><b>Год начала обучения:</b> ${group.yStart}</p>

<br>
<h4>Одногруппники:</h4>
<c:if test="${fn:length(group.personSet) gt 0}">
    <div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Имя</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="st" items="${group.personSet}" varStatus="counter">
            <tr>
                <td> ${counter.count} </td>
                <td> ${st.name} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<%@ include file="/footer.jsp" %>