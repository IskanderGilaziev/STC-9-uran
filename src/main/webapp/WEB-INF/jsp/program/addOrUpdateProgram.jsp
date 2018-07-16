<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="color" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../aside.jsp" %>

<form class="editForm" action="/program/addOrUpdateProgram" method="post" name="editForm">

            <c:if test="${action=='update'}">
                <input type="hidden" name="id" value="${program.id}">
            </c:if>

            <c:if test="${action=='add'}">
                <input type="hidden" name="id" value="0">
            </c:if>

            <input type="hidden" name="action" value="${action}">

                    <c:if test="${action=='update'}">
                        <h2>Редактирование</h2>
                    </c:if>

                    <c:if test="${action=='add'}">
                        <h2>Добавление новой программы</h2>
                    </c:if>

            <ul style= "width: 15cm">
                <li>
                    <label for="specialty">Специальность:</label>
                    <select id="specialty" name="specialty">
                        <c:forEach var="spec" items="${specList}">
                            <option value="${spec.id}" <c:if test="${program.specialty.id eq spec.id}">selected</c:if>>${spec.name}</option>
                        </c:forEach>
                    </select>
                </li>

                <li>
                    <label for="semester">Семестры:</label>
                    <input id="semester" type="text" value="" name="semester" style="height: inherit">
                    <script>
                        $("input[name='semester']").TouchSpin({
                            min: 1,
                            max: 100,
                            step: 1,
                            decimals: 0,
                            boostat: 5,
                            maxboostedstep: 10,
                            initval: "<c:if test="${action=='update'}">${program.semester}</c:if>"
                        });

                    </script>
                </li>

                <li>
                    <label for="subject">предмет:</label>
                    <select id="subject" name="subject">
                        <c:forEach var="subj" items="${subjList}">
                            <option value="${subj.id}"
                                    <c:if test="${program.subject.id eq subj.id}">selected</c:if>>${subj.name}</option>
                        </c:forEach>
                    </select>
                </li>

                <li>
                    <label for="hours">Часы:</label>
                    <input id="hours" type="text" value="" name="hours" style= "height: inherit" color:color:#c7ddef >
                    <script>
                        $("input[name='hours']").TouchSpin({
                            min: 1,
                            max: 100,
                            step: 1,
                            decimals: 0,
                            boostat: 5,
                            maxboostedstep: 10,
                            initval: "<c:if test="${action=='update'}">${program.hours}</c:if>"

                        });

                    </script>
                </li>


                <li>
                    <button class="submit" type="submit">OK</button>
                </li>
            </ul>
        </form>
<%@ include file="../../../footer.jsp" %>