<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<fmt:requestEncoding value="utf-8"/>
<h1>Список специальностей</h1>
<form class="editForm" name="editForm">
    <c:if test="${fn:length(specialityList) gt 0}">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>
                        №
                    </th>
                    <th>Название специальности</th>
                    <th>Срок обучения</th>
                        <%--<th>Актуальность</th>--%>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="speciality" items="${specialityList}" varStatus="counter">
                <tr>
                    <td>
                            ${counter.count}
                    </td>
                    <td>
                        <c:if test="${speciality.id eq theSpeciality.id}">
                            <input type="text" value=${speciality.name} name="newName" required/>
                        </c:if>
                        <c:if test="${speciality.id ne theSpeciality.id}">
                            ${speciality.name}
                        </c:if>
                    </td>

                    <td>
                        <c:if test="${speciality.id eq theSpeciality.id}">
                            <input type="number" value=${speciality.yTotal} name="newYTotal" required/>
                        </c:if>
                        <c:if test="${speciality.id ne theSpeciality.id}">
                            ${speciality.yTotal}
                        </c:if>
                    </td>

                        <%--<td>--%>
                        <%--<c:if test="${speciality.id eq theSpeciality.id}">--%>
                        <%--<input type="text" value=${speciality.isActive} name="newIsActive" required/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${speciality.id ne theSpeciality.id}">--%>
                        <%--<c:if test="${speciality.isActive eq 'true'}">--%>
                        <%--Действующая--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${speciality.isActive eq 'false'}">--%>
                        <%--Архивная--%>
                        <%--</c:if>--%>
                        <%--</c:if>--%>
                        <%--</td>--%>


                    <td>
                        <button class="submit" type="submit" name="specialityId" value="${speciality.id}"
                                formaction="infoSpeciality" method="post">Подробнее
                        </button>
                    </td>
                    <td>
                        <button class="submit" type="submit" name="specialityId" value="${speciality.id}"
                                formaction="removeSpeciality" method="post">Удалить
                        </button>
                    </td>
                    <td>
                        <c:if test="${speciality.id eq theSpeciality.id}">
                            <button class="submit" type="submit" name="specialityId" value="${speciality.id}"
                                    formaction="editSpeciality" method="post">Принять
                            </button>
                        </c:if>
                        <c:if test="${speciality.id ne theSpeciality.id}">
                            <button class="submit" type="submit" name="specialityId" value="${speciality.id}"
                                    formaction="updateSpeciality" method="post">Редактировать
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
    <p>Добавить новую учебную специальность:</p>
    <p><input type="text" placeholder="название специальности" name="name"/></p>
    <p><input type="number" placeholder="срок обучения в годах" name="yTotal"/></p>
    <p><input type="hidden" name="isActive" value="true"/></p>
    <c:set var="subjects" scope="request" property="java.util.List"/>
    <%--<p><select multiple size="${fn:length(subjectList)}" name="selectedSubjects[]" required>--%>
    <p><select size="${fn:length(subjectList)}" name="selectedSubject" required>
        <c:forEach var="subject" items="${subjectList}">
            <option value="${subject.id}">${subject.name}</option>
        </c:forEach>
    </select></p>
    <p>
        <button class="submit" type="submit" formaction="addSpeciality" method="post">Добавить новую специальность
        </button>
    </p>
</form>
<%@ include file="../../../footer.jsp" %>