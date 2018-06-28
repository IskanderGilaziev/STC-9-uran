<%@ page import="ru.innopolis.stc9.pojo.realisationJDBC.Group" %>
<%@ page import="ru.innopolis.stc9.pojo.realisationJDBC.Schedule" %>
<%@ page import="ru.innopolis.stc9.pojo.realisationJDBC.TeacherSubject" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<%--**********************************************************************--%>

<div class="table-responsive">
    <table class="table table-striped">
        <tbody>
        <tr>
            <c:forEach var="link" items="${links}">
                <td><a href="${pageContext.request.contextPath}${link.key}">${link.value}</a></td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    <%--</div>--%>

    <c:forEach var="i" items="${deskop.list}">
        <c:out value="${i}"/>
    </c:forEach>

    <h2>Расписание</h2>
    <% Schedule mainDesk = (Schedule) request.getAttribute("desktop");%>
    <% List<Group> groups = (List) request.getAttribute("groups");%>

    <c:set var="day" value="0"/>
    <c:set var="dayOfWeek" value="Понедельник"/>
    <c:set var="columns" value="${fn:length(groups)+1}"/>


    <%--<div class="table-responsive">--%>
    <table class="table table-striped">
        <tr>
            <th>№ пары \ Группа</th>
            <%for (Group group : groups) {%>
            <th><%= group.generateGroupName()%>
            </th>
            <%}%>
            <%--<c:forEach var="gr" items="${groups}">--%>
            <%--<th><c:out value="id группы ${gr.id}"/></th>--%>
            <%--</c:forEach>--%>
        </tr>
        <%for (String day : mainDesk.getWeekDay()) {%>

        <tr>
            <td colspan="${columns}"><label><%=day%>
            </label></td>
        </tr>

        <%--<%for (String lessonNumber : mainDesk.getLessonNumber()) {%>--%>
        <%
            for (int i = 0; i < mainDesk.getLessonNumber().size(); i++) {
                String lessonNumber = mainDesk.getLessonNumber().get(i);
        %>
        <%--Заголовок столбца с номером пары--%>
        <tr>
            <td rowspan="3"><%=lessonNumber%>
            </td>
            <%--row with subject's names--%>
            <%
                for (Group group : groups) {
                    TeacherSubject info = mainDesk.getSubjectInDraft(group, day, i + 1);
                    String sub = info != null ? info.getSubjectName() : "-";
            %>
            <td><%=sub%>
            </td>
            <%}%>
        </tr>

        <%--row with room--%>
        <tr>
            <%
                for (Group group : groups) {
                    int info = mainDesk.getRoomInDraft(group, day, i + 1);
                    String sub = info > 0 ? ("Ауд." + info) : "-";
            %>
            <td><%=sub%>
            </td>
            <%}%>
        </tr>

        <%--row with teacher's names--%>
        <tr>
            <%
                for (Group group : groups) {
                    TeacherSubject info = mainDesk.getSubjectInDraft(group, day, i + 1);
                    String sub = info != null ? info.getTeacherName() : "-";
            %>
            <td><%=sub%>
            </td>
            <%}%>
        </tr>

        <%
                }
            }
        %>
    </table>
</div>
<a href="${pageContext.request.contextPath}seeScheduleGroupsInProgress">Назад</a>
<%@ include file="../../../footer.jsp" %>