<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<%--<fmt:requestEncoding value="utf-8"/>--%>

<h1>Список дисциплин</h1>
<form class="editForm" name="editForm">
    <c:if test="${fn:length(subjectList) gt 0}">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>
                        №
                    </th>
                    <th>Название предмета</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="subject" items="${subjectList}" varStatus="counter">
                <tr>
                    <td>
                            ${counter.count}
                    </td>
                    <td>
                        <c:if test="${subject.id eq theSubject.id}">
                            <input type="text" value=${subject.name} name="newName" required/>
                        </c:if>
                        <c:if test="${subject.id ne theSubject.id}">
                            ${subject.name}
                        </c:if>
                    </td>
                    <td>
                        <button class="submit" type="submit" name="subjectId" value="${subject.id}"
                                formaction="infoSubject" method="post">Подробнее
                        </button>
                    </td>
                    <td>
                        <button class="submit" type="submit" name="subjectId" value="${subject.id}"
                                formaction="removeSubject" method="post">Удалить
                        </button>
                    </td>
                    <td>
                        <c:if test="${subject.id eq theSubject.id}">
                            <button class="submit" type="submit" name="subjectId" value="${subject.id}"
                                    formaction="editSubject" method="post">Принять
                            </button>
                        </c:if>
                        <c:if test="${subject.id ne theSubject.id}">
                            <button class="submit" type="submit" name="subjectId" value="${subject.id}"
                                    formaction="updateSubject" method="post">Редактировать
                            </button>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <br>
    </c:if>
</form>
<form class="editForm" name="editForm">
    <p>Добавить новый предмет:</p>
    <p><input type="text" placeholder="Предмет" <fmt:requestEncoding value="utf-8"/> name="name"/>
        <button class="submit" type="submit" formaction="addSubject" method="post">Добавить новый предмет</button>
    </p>
</form>
<%@ include file="../../../footer.jsp" %>